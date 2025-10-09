
---

# 1) Sécurité « par construction » (secure-by-design) — principes et techniques Java

Principes clés à appliquer dès la conception :

* **Principe du moindre privilège** : ne donner accès qu’aux ressources strictement nécessaires.
* **Encapsulation / visibilité minimale** : champs `private`, méthodes `private`/`package-private` pour réduire la surface d’attaque ; classes `final` si immutables.
* **Immutabilité et defensive copying** pour objets sensibles (clé, secrets, paramètres).
* **Valider et assainir toute entrée** (OWASP : validation, évitement d’injection). 
* **Éviter les secrets en clair** : utiliser `SecretKey` et `javax.crypto` (ne pas loguer), stocker secrets dans keystore matériel (PKCS#11/HSM) si possible.
* **Fail-safe defaults** : échec sécurisé, pas de valeurs par défaut vulnérables.
* **Instrumentation & observabilité** : logs d’audit (attention à ne jamais loguer de secrets), monitoring des anomalies.
* **Threat modelling et revue de code**/SAST dès le design (OWASP Secure-by-Design).

Exemple simple de « defensive copy » pour un tableau de bytes (secret) :

```java
public final class SecretHolder {
    private final byte[] secret;
    public SecretHolder(byte[] secret) {
        this.secret = secret.clone(); // defensive copy
    }
    public byte[] getSecret() {
        return secret.clone(); // éviter fuite
    }
}
```

---

# 2) Framework JCA (Java Cryptographic Architecture) — rôle, utilisation, évolutions récentes

Quoi : JCA/JCE définissent les API (KeyFactory, KeyPairGenerator, Cipher, Mac, Signature, KeyStore, Provider) et la *Provider architecture* (Sun, SunJCE, SunJSSE, BouncyCastle, etc.). On doit *demander des algos* plutôt que des providers spécifiques pour rester portable (`Cipher.getInstance("AES/GCM/NoPadding")` plutôt que `..., "SunJCE"`).

Évolutions notables :

* **EdDSA (Ed25519 / Ed448)** : support natif ajouté (JDK 15 via JEP 339) — API `Signature` et `KeyPairGenerator` acceptent désormais `Ed25519`/`Ed448`. C’est important car EdDSA est performant et résistant à certaines attaques par canal auxiliaire. 
* Remises à jour régulières des algorithmes par défaut et des providers pour renforcer les algos faibles et désactiver MD5/MD2/algos RSA trop petits, etc. (voir `java.security` et la liste `jdk.certpath.disabledAlgorithms` / `jdk.jar.disabledAlgorithms`).

Exemple JCA : générer paire Ed25519 et signer/ vérifier

```java
KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
KeyPair kp = kpg.generateKeyPair();

Signature sig = Signature.getInstance("Ed25519");
sig.initSign(kp.getPrivate());
sig.update(messageBytes);
byte[] signature = sig.sign();

// verification
Signature vs = Signature.getInstance("Ed25519");
vs.initVerify(kp.getPublic());
vs.update(messageBytes);
boolean ok = vs.verify(signature);
```

---

# 3) `java.security` et sécurisation des communications (JSSE/TLS) — points importants & évolutions

Aspects importants :

* **JSSE (Java Secure Socket Extension)** gère TLS/SSL ; configuration via `SSLContext`, `TrustManager`, `KeyManager`. Préférer TLS 1.2/1.3.
* Depuis Java 8+ et versions ultérieures, **TLS 1.2** est activé par défaut ; Java évolue pour désactiver/retirer les protocoles et algos faibles. On peut configurer les protocoles/cipher suites explicitement côté serveur/client. 
* **SecureRandom** : `SecureRandom.getInstanceStrong()` (JDK8+) pour générer RNG de haute qualité. 
* **Security Manager** : dépréciation et plan de retrait (JEP 411) — ne compter plus sur SecurityManager pour confinement à long terme ; préférer sandboxing OS/containers, modules, ou contrôles d’ACL. 
* **Filtres de désérialisation** : API pour filtrer le flux d’objets lors de la désérialisation (introduced JDK9) — très utile contre des attaques par désérialisation.

Extrait : forcer TLS 1.2+ sur un `SSLServerSocket` :

```java
SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(8443);
s.setEnabledProtocols(new String[] {"TLSv1.3", "TLSv1.2"});
s.setEnabledCipherSuites(/* choisir suites sécurisées */);
```

---

# 4) API PKI en Java (java.security.cert, KeyStore, CertificateFactory) — usage & évolutions

Composants :

* **KeyStore** (JKS, PKCS12, PKCS11) : stockage clefs privées / chaînes certs ; utiliser PKCS12 ou un keystore matériel (PKCS#11) pour meilleure sécurité.
* **CertificateFactory** / `java.security.cert` : parsing X.509, création de `CertPath`, CRL handling. 
* **CertPath / validation** : `CertPathValidator` + policy via `java.security` properties (`jdk.certpath.disabledAlgorithms`) ; JDK évolue pour durcir règles par défaut (débloquer algos faibles, min key sizes configurable).

Bonnes pratiques PKI :

* Révoquer / vérifier CRL / OCSP lors de validations critiques.
* Ne pas stocker clés privées côté application en clair ; préférer HSM / PKCS#11.
* Utiliser des certificats et algos récents (p. ex. ECDSA P-256, Ed25519 si supporté).
* Maintenir `cacerts` à jour pour confiance système (les distributions JDK ont amélioré la gestion du fichier `cacerts` dans les versions récentes). 
Exemple lecture d’un certificat X.509 :

```java
CertificateFactory cf = CertificateFactory.getInstance("X.509");
try (InputStream in = Files.newInputStream(Paths.get("cert.pem"))) {
    X509Certificate cert = (X509Certificate) cf.generateCertificate(in);
    System.out.println(cert.getSubjectDN());
}
```

---

# 5) Signature des applications Java (JAR signing / code signing) — état et bonnes pratiques

Points essentiels :

* Java fournit **`jarsigner`** et l’API JAR signing (signer jars et vérifier signatures) ; on signe les JARs avec une clé privée contenue dans un keystore (JKS/PKCS12 ou token PKCS#11). 
* Timestamping est important : si la clé expire mais que la signature a un timestamp fiable, la signature reste valable. Utiliser l’option `-tsa` de `jarsigner`. 
* Depuis les évolutions de sécurité, **les algorithmes faibles sont interdits** par défaut pour les signatures JAR (p.ex. `jdk.jar.disabledAlgorithms`) — attention aux exigences d’algorithme et taille de clé lors de la signature. 

Commande typique :

```bash
# signer un jar avec un keystore PKCS12 et un timestamp TSA
jarsigner -keystore mykeystore.p12 -storetype PKCS12 -tsa http://timestamp.example.com \
    myapp.jar myalias
```

Vérification de la signature :

```bash
jarsigner -verify -verbose -certs myapp.jar
```

Pour les apps mobiles (Android) : le mécanisme de signature est différent (`apksigner`/`v2/v3` schemes) — ne pas confondre avec signature JAR standard.

---

# 6) Checklist pratique et recommandations concrètes (rapide)

* **Toujours** forcer TLS 1.2+ (préférer TLS 1.3) et choisir cipher suites modernes. 
* **Utiliser** `SecureRandom.getInstanceStrong()` pour génération de clés cruciales. 
* **Mise à jour JDK** : restez sur une LTS moderne (17, 21) et appliquez les patches BPR ; les JDK récents apportent corrections/algos nouveaux (EdDSA…). 
* **Utiliser keystore matériel** (PKCS#11/HSM) pour clés de signature/production.
* **Signer et timestamp** les JARs, vérifier `jdk.jar.disabledAlgorithms`.
* **Ne pas compter sur SecurityManager** pour nouveau code (déprécié) ; migrer vers sandboxing OS/containers ou contrôles d’accès explicitement codés. 

---


# Qu’est‑ce que OWASP ?

OWASP (Open Worldwide Application Security Project) est une fondation à but non lucratif, portée par des volontaires, qui produit des outils, guides et standards open‑source pour améliorer la sécurité des applications. Les ressources sont gratuites et largement utilisées par les équipes dev/secops.

# Les projets et ressources clés

* **OWASP Top 10** — liste de sensibilisation des 10 risques web critiques (édition 2021 pour les web apps). C’est un bon point d’entrée pour les développeurs et manager sécurité. 
* **OWASP Top 10 – API Security (2023)** — version dédiée aux APIs, avec des catégories spécifiques (p.ex. Broken Object Level Authorization). Si tu développes ou consommes des APIs, c’est essentiel. 
* **ASVS (Application Security Verification Standard)** — standard technique plus complet et vérifiable pour tester la sécurité applicative (utile pour spécifier exigences de sécurité). 
* **WSTG (Web Security Testing Guide)** — guide de tests de sécurité pour pentests et QA. 
* **SAMM (Software Assurance Maturity Model)** — modèle de maturité pour organiser un programme AppSec. ([owaspsamm.org][6])
* **ZAP, Dependency‑Check, et autres outils** — ZAP (proxy/scan gratuit), Dependency‑Check (vulnérabilités OSS), etc. Ces outils sont souvent intégrés dans CI/CD.

# Le Top 10 Web (édition 2021) — résumé rapide et mitigations

OWASP a restructuré la liste pour se concentrer sur les *causes* plutôt que les symptômes. Voici les catégories (très court + mitigation principale) : 

1. **A01 – Broken Access Control**
   Mauvaise enforcement des autorisations (ex: accès à données/actions d’un autre user).
   *Mitigation* : contrôle d’accès centralisé, tests d’autorisation, principe du moindre privilège.

2. **A02 – Cryptographic Failures**
   Mauvaise utilisation/absence de chiffrement (ex: stockage de secrets en clair).
   *Mitigation* : utiliser TLS 1.2+/1.3, chiffrement moderne (AES‑GCM, ECDSA/Ed25519…), gestion des clés (HSM/keystore).

3. **A03 – Injection**
   SQL, OS, LDAP, XSS — entrée non filtrée interprétée comme code.
   *Mitigation* : requêtes paramétrées/prepared statements, validation whitelisting, encodage contextuel.

4. **A04 – Insecure Design**
   Failles au niveau de l’architecture / threats non pris en compte.
   *Mitigation* : threat modeling, ASVS, security by design, revues d’architecture.

5. **A05 – Security Misconfiguration**
   Configs par défaut, erreurs dans serveurs/headers, exposés.
   *Mitigation* : baselines sécurisés, scans de config, CI checks.

6. **A06 – Vulnerable and Outdated Components** (ou similaire dans la 2021 wording)
   Bibliothèques ou composants avec vulnérabilités connues.
   *Mitigation* : SCA (Software Composition Analysis), mises à jour régulières, policies de patch.

7. **A07 – Identification and Authentication Failures**
   Mécanismes d’authent non robustes (sessions, MFA absente).
   *Mitigation* : use MFA, gestion sûre des sessions, rotation des tokens.

8. **A08 – Software and Data Integrity Failures**
   Attaques sur la supply chain ou absence de vérification d’intégrité.
   *Mitigation* : signing, reproducible builds, vérification d’intégrité, CI/CD hardening.

9. **A09 – Security Logging and Monitoring Failures**
   Manque de logs/audit => détection tardive d’incidents.
   *Mitigation* : logs d’audit (sans secrets), alerting, playbooks IR.

10. **A10 – Server‑Side Request Forgery (SSRF) / ou autre catégorie selon mapping**
    Applications qui permettent de requêter des ressources internes via l’app.
    *Mitigation* : validation des URLs, whitelist, egress filtering.

> Nota : la formulation exacte et la numérotation viennent de l’édition 2021; pour les APIs consulte l’édition API Top 10 (2023) qui contient des items adaptés aux architectures API. 
# Comment utiliser OWASP dans ton workflow (pratique)

* **Pour les devs** : intégrer ASVS/Top10 en définition d’exigences ; utiliser WSTG pour tests unitaires & pentests. 
* **Pour CI/CD** : ajouter SCA (Dependabot/Dependecy‑Check), SAST, DAST (ZAP) et checks contre les règles ASVS/Top10.
* **Pour l’architecture** : faire du threat modeling (ex : STRIDE), exiger revues de sécurité avant merge.
* **Pour la conformité / audits** : utiliser ASVS comme référentiel de vérification mesurable.

# Quick wins (actions à faire maintenant)

1. Scanner tous les projets avec un SCA + corriger dépendances critiques.
2. Activer TLS 1.3 / désactiver protocoles et suites faibles.
3. Ajouter des tests d’autorisation automatisés (coverage pour Broken Access Control).
4. Introduire MFA pour accès sensibles et rotation des clés/secrets via vault.
5. Mettre en place logging d’audit + alerting (sans logs de secrets).



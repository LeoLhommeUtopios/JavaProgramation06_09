package org.example;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

public class keyStoreDemo {
    public static void main(String[] args) {
        Path ksPath = Path.of("keystore.p12");
        char[] password = "Password".toCharArray();
        String alias = "alias2";


        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");

            if(Files.exists(ksPath)){
                try (FileInputStream fis =  new FileInputStream(ksPath.toFile())){
                    ks.load(fis,password);
                }catch (IOException | CertificateException | NoSuchAlgorithmException  e ) {
                    throw new RuntimeException(e);
                }
                System.out.println("Keystore existant chargé : "  + ksPath);
            }
            else{
                ks.load(null,null);


                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(2048);
                KeyPair keyPair = kpg.generateKeyPair();

                ks.setKeyEntry(alias,keyPair.getPrivate(),password,new Certificate[]{});

                try (FileOutputStream fos = new FileOutputStream(ksPath.toFile())){
                    ks.store(fos,password);
                }

                System.out.println("KeyStore crée et clé inserer "+ ksPath);
            }

            Key key = ks.getKey(alias,password);
            Certificate cert = ks.getCertificate(alias);
            System.out.println("CLé recuperer : "+ key.getFormat());
            System.out.println("Certificat "+cert.getType());

            String message = "message with key";
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign((PrivateKey) key);
            signature.update(message.getBytes());
            byte[] sigByte = signature.sign();
            System.out.println("Signature (base64) : "+ Base64.getEncoder().encodeToString(sigByte));

            System.out.println("\nMessage signé : " + message);
            System.out.println("Signature Base64 : " + Base64.getEncoder().encodeToString(sigByte));


        } catch (KeyStoreException | IOException | NoSuchAlgorithmException |UnrecoverableKeyException  | CertificateException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }


    }
}

package org.example;


import java.security.*;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        // Generer une paire de clés ed25519
        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
            KeyPair keyPair = kpg.generateKeyPair();


            String message = "Bonjour,  c'est un message securisé !";
            byte[] messageByte = message.getBytes();

        //signer le message
            Signature signature = Signature.getInstance("Ed25519");
            signature.initSign(keyPair.getPrivate());
            signature.update(messageByte);
            byte[] sigByte = signature.sign();
            System.out.println("Signature (base64) : "+ Base64.getEncoder().encodeToString(sigByte));


        //verifier la signature
            Signature verify = Signature.getInstance("Ed25519");
            verify.initVerify(keyPair.getPublic());
            verify.update(messageByte);
            boolean valid = verify.verify(sigByte);
            System.out.println("signature valide "+valid);

            if(valid){
                String decodeMessage = new String(messageByte);
                System.out.println("message decodé : "+ decodeMessage );
            }else{
                System.out.println("Signature invalide");
            }


        }catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException  e){
            System.out.println(e.getMessage());
        }

    }
}
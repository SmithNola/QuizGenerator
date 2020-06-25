package org.quizgen.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;


/*
    TO-DO
        -  Needs to be tested!
 */


public class HashPassword {

    private static final String secretKeyAlgo = "PBKDF2WithHmacSHA1";
    public static String salt;

    static{
        int saltLength = 16;
        byte[] saltAsBytes = new byte[saltLength];
        new SecureRandom().nextBytes(saltAsBytes); // fills saltAsBytes array with random byte values
        salt = Base64.getEncoder().encodeToString(saltAsBytes); //encodes saltAsByte array into a String value
    }

    // Creates hashed password
    public static Optional<String> getHashedPassword(String userPassword, String salt){
        PBEKeySpec passwordKeySpec = getPasswordKeySpec(userPassword, salt);
        try{
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(secretKeyAlgo);
            byte[] hashedPassword = secretKeyFactory.generateSecret(passwordKeySpec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(hashedPassword));
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException e){
            System.err.println("Exception encountered in getHashPassword()\n" + e.getMessage());
            return Optional.empty();
        }
        finally{
            passwordKeySpec.clearPassword();
        }
    }

    private static PBEKeySpec getPasswordKeySpec(String userPassword, String salt){
        char[] passwordCharArray = userPassword.toCharArray();
        byte[] saltByteArray = salt.getBytes();
        int iterations = 65536;
        int keyLength = 128;
        return new PBEKeySpec(passwordCharArray, saltByteArray, iterations, keyLength);
    }

    public static boolean verifyPassword(String userPassword, String key, String salt){
        Optional<String> hashedPassword = getHashedPassword(userPassword, salt);
        return hashedPassword.isPresent() && hashedPassword.get().equals(key);
    }
}

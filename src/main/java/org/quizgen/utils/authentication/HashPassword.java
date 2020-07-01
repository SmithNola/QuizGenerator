package org.quizgen.utils.authentication;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;


/*
    Code adapted from: https://dev.to/awwsmm/how-to-encrypt-a-password-in-java-42dh
 */


public class HashPassword {

    private static final String SECRETKEY_ALGO = "PBKDF2WithHmacSHA1";
    private static final int SALT_LENGTH = 16;
    private static final int KEY_LENGTH = 128;
    private static final int ITERATIONS = 10_000;

    public static String getSalt(){
        byte[] saltAsBytes = new byte[SALT_LENGTH];

        // fills saltAsBytes array with random byte values
        new SecureRandom().nextBytes(saltAsBytes);

        //encodes saltAsByte array into a String value
        return Base64.getEncoder().encodeToString(saltAsBytes);
    }

    // Creates hashed password
    public static Optional<String> getHashedPassword(String userPassword, String salt){
        PBEKeySpec passwordKeySpec = getPasswordKeySpec(userPassword, salt);
        try{
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRETKEY_ALGO);
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
        return new PBEKeySpec(passwordCharArray, saltByteArray, ITERATIONS, KEY_LENGTH);
    }

    public static boolean verifyPassword(String userPassword, String key, String salt){
        Optional<String> hashedPassword = getHashedPassword(userPassword, salt);
        return hashedPassword.isPresent() && hashedPassword.get().equals(key);
    }
}

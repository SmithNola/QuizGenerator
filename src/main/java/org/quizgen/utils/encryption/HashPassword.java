package org.quizgen.utils.encryption;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class HashPassword{
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RAND = new SecureRandom();
    public HashPassword() {
        try {
            tester();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HashPassword hashPassword = new HashPassword();
        System.out.println(hashPassword);
    }
    private static void tester() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String salt = HashPassword.generateSalt(512).get();
        String password = getPassword();
        String key = HashPassword.hashPassword(password, salt).get();
        System.out.println(HashPassword.verifyPassword(password, key, salt));//check does the password , key, and salt match
        System.out.println(HashPassword.verifyPassword("By-Tor! And the Snow Dog!", key, salt)); // this is false
    }
    private static String getPassword() throws InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Constructor<HashPassword> constructor = HashPassword.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        HashPassword hashPassword = constructor.newInstance();
        return hashPassword.toString();
        /* }catch (Exception e){e.printStackTrace();}*/
    }
    /* generates a salt of custom length*/
    private static Optional<String> generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }
    /* Producing the hash and the salt*/
    private static Optional<String> hashPassword (String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }/* checking if the password match*/
    private static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        return optEncrypted.map(s -> s.equals(key)).orElse(false);
    }
}
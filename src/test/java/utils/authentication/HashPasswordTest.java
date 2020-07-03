package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.quizgen.utils.authentication.HashPassword;

import static org.junit.jupiter.api.Assertions.*;
import static org.quizgen.utils.authentication.HashPassword.*;


public class HashPasswordTest {

    @Test
    @DisplayName("Test `getHashedPassword()` hashes user-entered password")
    void testIfHashPasswordProducesHashPassword(){
        String salt = getSalt();
        String userEnteredPassword = "nsadjfhkjasdh";
        String key = getHashedPassword(userEnteredPassword, salt).get();
        System.out.println(key); //hashed password
        System.out.println(salt);
        // check if "key" is successfully hashed (pasword != key)
        assertNotEquals(userEnteredPassword, key);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "adfASDFvbc", "", "#$%df@FBSw", "af235HSG!@$"})
    @DisplayName("Test `verifyPassword()` successfully verifies correct user password")
    void testIfVerifyPasswordMethodWorks(String password){
        String salt = getSalt();
        String key = getHashedPassword(password, salt).get();

        assertTrue(verifyPassword(password, key, salt));
    }

    @Test
    @DisplayName("Test `verifyPassword()` successfully verifies incorrect user password")
    void testIfVerifyPasswordMethodFails(){
        String salt = getSalt();
        String unhashedPassword = "";
        String key = getHashedPassword("123", salt).get();
        assertFalse(verifyPassword(unhashedPassword, key, salt));
    }

    @RepeatedTest(3)
    @DisplayName("Test `getSalt() produces random Salt value")
    void testGetSalt(){
        String salt1 = HashPassword.getSalt();
        String salt2 = HashPassword.getSalt();
        System.out.println(String.format("Salt 1 = %s \nSalt 2 = %s", salt1, salt2));
        assertNotEquals(salt1, salt2);
    }

}

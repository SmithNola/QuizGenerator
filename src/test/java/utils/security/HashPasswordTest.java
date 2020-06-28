package utils.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.quizgen.utils.security.HashPassword.*;


public class HashPasswordTest {

    @Test
    void testIfHashPasswordProducesHashPassword(){
        String salt = getSalt();
        String unhashedPassword = "nsadjfhkjasdh";
        String key = getHashedPassword(unhashedPassword, salt).get();
        System.out.println(key);
        System.out.println(salt);
        assertNotEquals(unhashedPassword, key);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "adfASDFvbc", "", "#$%df@FBSw", "af235HSG!@$"})
    void testIfVerifyPasswordMethodPasses(String password){
        String salt = getSalt();
        String key = getHashedPassword(password, salt).get();

        assertTrue(verifyPassword(password, key, salt));
    }

    @Test
    void testIfVerifyPasswordMethodFails(){
        String salt = getSalt();
        String unhashedPassword = "";
        String key = getHashedPassword("123", salt).get();
        assertFalse(verifyPassword(unhashedPassword, key, salt));
    }
}

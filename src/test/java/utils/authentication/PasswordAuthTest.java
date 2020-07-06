package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.domain.authentication.PasswordAuth;

import static org.junit.jupiter.api.Assertions.*;

class PasswordAuthTest {

    @ParameterizedTest
    @CsvSource({"Abc123!e", "Coolkid123@"})
    @DisplayName("Tests `PasswordPolicy.passwordIsValid()` is true when pw meets password requirements")
    void checkPasswordIsValid(String password){
            boolean actual = PasswordAuth.passwordIsValid(password);
            assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"a", "supernatural", "abcdef12!", "12345678", "!!!!!!!!!!"})
    @DisplayName("Tests `PasswordPolicy.passwordIsValid()` is false when pw meets password requirements")
    void checkPasswordIsNotValid(String password){
        boolean actual = PasswordAuth.passwordIsValid(password);
        assertFalse(actual);
    }
}
package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.utils.authentication.PasswordChecker;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    @ParameterizedTest
    @CsvSource({"Abc123!e", "Coolkid123@"})
    @DisplayName("Tests `PasswordPolicy.passwordIsValid()` is true when pw meets password requirements")
    void checkPasswordIsValid(String password){
            boolean actual = PasswordChecker.passwordIsValid(password);
            assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"a", "supernatural", "abcdef12!", "12345678", "!!!!!!!!!!"})
    @DisplayName("Tests `PasswordPolicy.passwordIsValid()` is false when pw meets password requirements")
    void checkPasswordIsNotValid(String password){
        boolean actual = PasswordChecker.passwordIsValid(password);
        assertFalse(actual);
    }
}
package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.authentication.LoginValidation;
import org.quizgen.utils.authentication.PasswordPolicy;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest{

    @ParameterizedTest
    @CsvSource({"Abc123!e", "Coolkid123@"})
    @DisplayName("Check if method correctly checks if password is valid")
    void checkPasswordIsValid(String password){
            boolean actual = PasswordPolicy.passwordIsValid(password);
            assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"a", "supernatural", "abcdef12!", "12345678", "!!!!!!!!!!"})
    @DisplayName("Check if method correctly checks if password is valid")
    void checkPasswordIsNotValid(String password){
        boolean actual = PasswordPolicy.passwordIsValid(password);
        assertFalse(actual);
    }
}
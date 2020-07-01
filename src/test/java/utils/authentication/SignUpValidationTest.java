package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.utils.authentication.SignupAuth;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    TO-DO:
        - Create tests for "usernameAlreadyExists()"
        - Create tests for "passwordIsValid()"
 */
public class SignUpValidationTest {

    @ParameterizedTest
    @CsvSource({"'', 123, 123",
                "abc, '', 123",
                "abc, 123,''"})
    @DisplayName("Check if signupValidity validates for blank fields")
    void checkIfSignupValidityForBlankFields(String username, String password, String rePassword){
        String expected = "All fields must be filled out!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"'abc ', 123, 123",
                "abc, ' 123', 123",
                "abc, 123,'   123  '"})
    @DisplayName("Check if signupValidity validates for whitespace in fields")
    void checkIfSignupValidityForWhiteSpaceInFields(String username, String password, String rePassword){
        String expected = "No whitespace allowed!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"abc, 123, 12",
                "abc, xyz, XYZ"})
    @DisplayName("Check if signupValidatity validates for non-matching passwords")
    void checkIfSignupValidityForNonMatchingPasswords(String username, String password, String rePassword){
        String expected = "Passwords do not match!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }
}

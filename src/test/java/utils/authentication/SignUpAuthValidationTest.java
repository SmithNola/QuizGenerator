package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.authentication.SignupAuth;

import static org.junit.jupiter.api.Assertions.*;

/*
    TO-DO:
        - Mock Database for testing
 */
public class SignUpAuthValidationTest {

    @ParameterizedTest
    @CsvSource({"'', 123, 123",
                "abc, '', 123",
                "abc, 123,''"})
    @DisplayName("Test`SignUpAuth.signupValidity()` validates for blank fields")
    void testSignupValidityMethodForBlankFields(String username, String password, String rePassword){
        String expected = "All fields must be filled out!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"'abc ', 123, 123",
                "abc, ' 123', 123",
                "abc, 123,'   123  '"})
    @DisplayName("Test `SignUpAuth.signupValidity()` validates for whitespace in fields")
    void testSignupValidityMethodForWhiteSpaceInFields(String username, String password, String rePassword){
        String expected = "No whitespace allowed!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"abc, 123, 12",
                "abc, xyz, XYZ"})
    @DisplayName("Test `SignUpAuth.signupValidity()` validates for non-matching passwords")
    void testSignupValidityMEthodForNonMatchingPasswords(String username, String password, String rePassword){
        String expected = "Passwords do not match!";
        String actual = SignupAuth.signupValidity(username, password, rePassword);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"Batman", "bAtMan"})
    @DisplayName("Test `SignUpAuth.signupValidity()` for already existing usernames")
    void testSignupValidityMethodForExistingUsernames(String username){
        if(DatabaseConnection.isConnected()){
            boolean actual = SignupAuth.usernameAlreadyExists(username);
            assertTrue(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }

    @ParameterizedTest
    @CsvSource({"Barbie", "Catman"})
    @DisplayName("Test `SignupAuth.usernameAlreadyExists()` for non-existing usernames")
    void checkUserameAlreadyDoesNotExist(String username){
        if(DatabaseConnection.isConnected()){
            boolean actual = SignupAuth.usernameAlreadyExists(username);
            assertFalse(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }
}

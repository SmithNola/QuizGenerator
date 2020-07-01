package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.authentication.LoginValidation;

import static org.junit.jupiter.api.Assertions.*;

public class LoginValidationTest {

    @ParameterizedTest
    @CsvSource(value = {"'':''",
                        "'   ':'  '",
                        "'\t':' '",
                        "' ':'\n'",
                        "asdf:''",
                        "'':asdfasdf"},
                        delimiter=':')
    @DisplayName("Check if blank fields triggers error")
    void checkForBlankErrorMessage(String username, String password){
        String expected = "All fields must be filled out!";
        String actual = LoginValidation.getLoginErrorMessage(username, password);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"'Batman':'Robin'"},
            delimiter=':')
    @DisplayName("Check if method correctly checks if login exists")
    void checkLoginDoesNotExist(String username, String password){
        if(DatabaseConnection.isConnected()){
            boolean actual = LoginValidation.loginDoesNotExist(username, password);
            assertFalse(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"'Robin':'Batman'"},
            delimiter=':')
    @DisplayName("Check if method correctly checks if login does not exist")
    void checkLoginExists(String username, String password){
        if(DatabaseConnection.isConnected()){
            boolean actual = LoginValidation.loginDoesNotExist(username, password);
            assertTrue(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }
}
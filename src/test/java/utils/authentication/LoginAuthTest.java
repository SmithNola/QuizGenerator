package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.authentication.LoginAuth;

import static org.junit.jupiter.api.Assertions.*;

/*
    TO-DO:
        - Mock Database for testing
 */
public class LoginAuthTest {

    @ParameterizedTest
    @CsvSource(value = {"'':''",        /* blank value : blank value */
                        "'   ':'  '",   /* blank space : blank space */
                        "'\t':' '",     /* tab space : blank space */
                        "' ':'\n'",     /* blank space : newline */
                        "asdf:''",      /* asdf : blank value  */
                        "'':asdfasdf"}, /* blank value : asdfasdf */
                        delimiter=':')
    @DisplayName("Tests `getLoginError()` for FIELD_IS_BLANK error when args are blank")
    void testMethodForFieldBlankError(String username, String password){
        LoginAuth.checkLoginIsValid(username, password); // this also sets the error message
        String expected = "All fields must be filled out!";
        String actual = LoginAuth.getLoginError();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"'Batman':'Test123*'"},
            delimiter=':')
    @DisplayName("Tests `loginDoesNotExist()` for verifying login existence DB, when login DOES exist")
    void testMethodWhenLoginExists(String username, String password){
        if(DatabaseConnection.isConnected()){
            boolean actual = LoginAuth.checkLoginIsValid(username, password);
            assertTrue(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"'Test123*':'Batman'"},
            delimiter=':')
    @DisplayName("Tests `loginDoesNotExist()` for verifying login existence in DB, when login DOES NOT exist")
    void testMethodWhenLoginNotExists(String username, String password){
        if(DatabaseConnection.isConnected()){
            boolean actual = LoginAuth.checkLoginIsValid(username, password);
            assertFalse(actual);
        }else{
            System.out.println("Not connected to database");
        }
    }
}
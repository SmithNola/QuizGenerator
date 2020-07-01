package utils.authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.quizgen.utils.authentication.LoginValidation;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    TO-DO:
     - create tests for `loginDoesNotExist` logic
 */
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
}

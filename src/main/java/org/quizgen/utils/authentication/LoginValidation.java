package org.quizgen.utils.authentication;

import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;

import java.sql.SQLException;

public class LoginValidation {

    public static String getLoginErrorMessage(String username, String password){

        if(username.isBlank() || password.isBlank()){
            return AccountError.FIELD_IS_BLANK.toString();
        }
        else if(loginDoesNotExist(username, password)){
            return AccountError.INVALID_CREDENTIALS.toString();
        }
        else{
            return "";
        }
    }

    public static boolean loginDoesNotExist(String username, String password){
        username = DatabaseConnection.checkLogin(username, password);
        User.setUsername(username);
        return username == null;
    }
}

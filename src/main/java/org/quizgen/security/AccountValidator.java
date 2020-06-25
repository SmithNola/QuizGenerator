package org.quizgen.security;

import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;
import org.quizgen.utils.signup.UsernameValidator;

import java.sql.SQLException;

public class AccountValidator {

    private String fieldsBlankError = "All fields must be filled out!";
    private String passwordMatchesError = "Passwords do not match!";
    private String wrongLogin = "Wrong username or password";
    private String containsWhiteSpaceError = "No whitespace allowed!";

    public String getSignUpErrorMessage(String username, String password, String rePassword){
        UsernameValidator usernameValidator = new UsernameValidator(username);
        PasswordValidator passwordValidator = new PasswordValidator(password);

        if(username.isBlank() || password.isBlank() || rePassword.isBlank()){
            return fieldsBlankError;
        }
        else if(containsWhiteSpace(username, password, rePassword)){
            return containsWhiteSpaceError;
        }
        else if(!passwordsMatches(password, rePassword)){
            return passwordMatchesError;
        }
        else if(usernameValidator.usernameIsTaken(username)){
            return usernameValidator.usernameIsTakenError();
        }
        else if(!passwordValidator.passwordRegisterSuccessful()){
            return passwordValidator.passwordRegisterError();
        }
        else {
            return "";
        }
    }

    public String getLoginErrorMessage(String username, String password) throws SQLException {
        if(username.isBlank() || password.isBlank()){
            return fieldsBlankError;
        }
        else if(loginDoesNotExist(username, password)){
            return wrongLogin;
        }
        else{
            return "";
        }
    }

    private boolean loginDoesNotExist(String username, String password) throws SQLException {
        username = DatabaseConnection.checkLogin(username, password);
        User.setUsername(username);
        return username == null;
    }

    private boolean passwordsMatches(String password, String reenterPassword){
        return password.equals(reenterPassword);
    }

    private boolean containsWhiteSpace(String ...args){
        for(String field: args){
            if(field.contains(" ")){
                return true;
            }
        }
        return false;
    }
}

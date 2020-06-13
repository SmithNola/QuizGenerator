package org.quizgen.utils.login;

import org.quizgen.data.DatabaseConnection;

import java.sql.SQLException;

public class AccountValidator {

    private String fieldsBlankError = "All fields must be filled out!";
    private String passwordMatchesError = "Passwords do not match!";
    private String wrongLogin = "Wrong username or password";

    public String getSignUpErrorMessage(String username, String password, String rePassword){
        UsernameValidator usernameValidator = new UsernameValidator(username);
        PasswordValidator passwordValidator = new PasswordValidator(password);

        if(username.isBlank() || password.isBlank() || rePassword.isBlank()){
            return fieldsBlankError;
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
        return DatabaseConnection.checkLogin(username, password) == null;
    }

    private boolean passwordsMatches(String password, String reenterPassword){
        return password.equals(reenterPassword);
    }

}

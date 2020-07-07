package org.quizgen.domain.authentication;

import org.quizgen.data.DatabaseConnection;

import java.util.HashMap;

public class ResetAuth {
    private HashMap<String, String> oldLogin;

    public ResetAuth(){
        setOldLogin();
    }

    private void setOldLogin(){
        if (DatabaseConnection.isConnected()) {
            oldLogin = DatabaseConnection.getOldLogins();
        }
        else{
            System.out.println("error with databasE!");
        }
    }

    public String resetError(String username, String oldPassword, String newPassword){
        if(username.isBlank() || oldPassword.isBlank() || newPassword.isBlank()){
            return AuthError.FIELD_IS_BLANK.toString();
        }
        else if(containsWhiteSpace(username, oldPassword, newPassword)){
            return AuthError.WHITESPACE_NOT_ALLOWED.toString();
        }
        else if(!matchesSavedLogin(username, oldPassword)){
            return AuthError.RESET_PW_ERROR.toString();
        }
        else if(oldPasswordMatchesNewPassword(oldPassword, newPassword)){
            return AuthError.PASSWORD_SHOULD_NOT_MATCH.toString();
        }
        else if(!PasswordAuth.passwordIsValid(newPassword)){
            return PasswordAuth.passwordErrorMessage(newPassword);
        }
        else{
            return AuthError.NO_ERROR.toString();
        }
    }

    private boolean matchesSavedLogin(String username, String password){
        return oldLogin.containsKey(username) && oldLogin.get(username).equals(password);
    }

    private boolean oldPasswordMatchesNewPassword(String password, String reenterPassword){
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

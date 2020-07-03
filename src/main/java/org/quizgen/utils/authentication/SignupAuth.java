package org.quizgen.utils.authentication;

import org.quizgen.data.DatabaseConnection;

public class SignupAuth {
    
    public static String signupValidity(String username, String password, String rePassword){
        
        // if any conditions are met, then create error message; else return NO_ERROR
        if(username.isBlank() || password.isBlank() || rePassword.isBlank()){
            return AccountError.FIELD_IS_BLANK.toString();
        }
        else if(containsWhiteSpace(username, password, rePassword)){
            return AccountError.WHITESPACE_NOT_ALLOWED.toString();
        }
        else if(!passwordsMatches(password, rePassword)){
            return AccountError.PASSWORD_NOT_MATCH.toString();
        }
        else if(usernameAlreadyExists(username)){
            return AccountError.USERNAME_EXISTS.toString();
        }
        else if(!PasswordPolicy.passwordIsValid(password)){
            return PasswordPolicy.passwordErrorMessage(password);
        }
        else{
            return AccountError.NO_ERROR.toString();
        }
    }

    private static boolean usernameAlreadyExists(String username){
        return DatabaseConnection.checkUsername(username);
    }

    private static boolean passwordsMatches(String password, String reenterPassword){
        return password.equals(reenterPassword);
    }

    private static boolean containsWhiteSpace(String ...args){
        for(String field: args){
            if(field.contains(" ")){
                return true;
            }
        }
        return false;
    }
}

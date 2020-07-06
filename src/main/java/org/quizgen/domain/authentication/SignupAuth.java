package org.quizgen.domain.authentication;

import org.quizgen.data.DatabaseConnection;

public class SignupAuth {
    
    public static String signupValidity(String username, String password, String rePassword){
        
        // if any error conditions are met, then create error message; else return NO_ERROR
        if(username.isBlank() || password.isBlank() || rePassword.isBlank()){
            return AuthError.FIELD_IS_BLANK.toString();
        }
        else if(containsWhiteSpace(username, password, rePassword)){
            return AuthError.WHITESPACE_NOT_ALLOWED.toString();
        }
        else if(!passwordsMatches(password, rePassword)){
            return AuthError.PASSWORD_NOT_MATCH.toString();
        }
        else if(usernameAlreadyExists(username)){
            return AuthError.USERNAME_EXISTS.toString();
        }
        else if(!PasswordAuth.passwordIsValid(password)){
            return PasswordAuth.passwordErrorMessage(password);
        }
        else{
            return AuthError.NO_ERROR.toString();
        }
    }

    public static String signupValidity(String[] args){
        String username = args[0];
        String password = args[1];
        String repassword = args[2];
        return signupValidity(username, password, repassword);
    }

    public static boolean usernameAlreadyExists(String username){
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

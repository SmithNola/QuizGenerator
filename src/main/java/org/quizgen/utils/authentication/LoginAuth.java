package org.quizgen.utils.authentication;

import org.quizgen.data.DatabaseConnection;

public class LoginAuth {

    private static String error = "";

    public static boolean checkLoginIsValid(String username, String password){
        if(username.isBlank() || password.isBlank()){
            error = AuthError.FIELD_IS_BLANK.toString();
            return false;
        }
        else if(loginDoesNotExist(username)){
            error = AuthError.INVALID_CREDENTIALS.toString();
            System.out.println("username does not exist!");
            return false;
        }
        else if(passwordIsNotCorrect(username, password)){
            error = AuthError.INVALID_CREDENTIALS.toString();
            System.out.println("password is not correct");
            return false;
        }
        else{
            return true;
        }
    }

    public static String getLoginError(){
        return error;
    }

    private static boolean loginDoesNotExist(String username){
        username = DatabaseConnection.getUsername(username);
        return  username == null; //login does not exist
    }

    private static boolean passwordIsNotCorrect(String username, String password){
        String[] keyAndSalt = DatabaseConnection.getKeySalt(username);
        String key = keyAndSalt[0];
        String salt = keyAndSalt[1];
        if(key==null || salt == null){
            return true;
        }
        return !PasswordHasher.passwordIsAuthentic(password, key, salt);
    }
}

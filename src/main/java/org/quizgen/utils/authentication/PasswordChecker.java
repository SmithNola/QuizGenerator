package org.quizgen.utils.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordChecker {

    static final int MIN_LENGTH_PASSWORD = 8;

    // CHECK IF PASSWORD MEETS PASSWORD POLICY REQUIREMENTS
    public static boolean passwordIsValid(String password){
        return  isAlphaNumeric(password) &&
                isContainsSpecialCharacter(password) &&
                isUpperAndLowerCase(password) &&
                isAtleastMinLength(password);
    }

    // default/ package-private since its only used with SignupAuth class
    static String passwordErrorMessage(String password){
        if(!isAlphaNumeric(password)){
            return AuthError.PW_MUST_BE_ALPHANUMERIC.toString();
        }
        else if(!isContainsSpecialCharacter(password)){
            return AuthError.PW_MUST_HAVE_SPECIAL_CHARACTER.toString();
        }
        else if(!isUpperAndLowerCase(password)){
            return AuthError.PW_MUST_BE_UPPERLOWER_CASED.toString();
        }
        else if(!isAtleastMinLength(password)){
            return AuthError.PW_MUST_BE_MIN_LENGTH.toString();
        } else {
            return AuthError.NO_ERROR.toString();
        }
    }

    // PASSWORD REQUIREMENTS
    private static boolean isAlphaNumeric(String password){
        boolean isAlphabet = false;
        boolean isNumerical = false;
        for(char letter: password.toCharArray()){
            if(Character.isAlphabetic(letter)){
                isAlphabet = true;
            }
            if(Character.isDigit(letter)){
                isNumerical = true;
            }
        }
        return isAlphabet && isNumerical;
    }

    private static boolean isContainsSpecialCharacter(String password){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }

    private static boolean isUpperAndLowerCase(String password){
        boolean lowerCase = false;
        boolean upperCase = false;
        for(char letter: password.toCharArray()){
            if(Character.isLowerCase(letter)){
                lowerCase = true;
            }
            if(Character.isUpperCase(letter)){
                upperCase = true;
            }
        }
        return lowerCase && upperCase;
    }

    private static boolean isAtleastMinLength(String password){
        return password.length() >= MIN_LENGTH_PASSWORD;
    }
}

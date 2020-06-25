package org.quizgen.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private final String password;
    private final int MIN_LENGTH_PASSWORD = 8;

    public PasswordValidator(String password){
        this.password = password;
    }

    public String passwordRegisterError(){
        if(!isAlphaNumeric()){
            return "Password must contain at least 1 letter or number";
        }
        else if(!isContainsSpecialCharacter()){
            return "Password must contain a special character";
        }
        else if(!isUpperAndLowerCase()){
            return "Password must contain upper and lowercase letters";
        }
        else if(!isAtleastMinLength()){
            return "Password must be at least 8 characters long";
        } else {
            return "";
        }
    }

    public boolean passwordRegisterSuccessful(){
        return isAlphaNumeric()
                && isContainsSpecialCharacter()
                && isUpperAndLowerCase()
                && isAtleastMinLength();
    }

    private boolean isAlphaNumeric(){
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

    private boolean isContainsSpecialCharacter(){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            return true;
        }
        return false;
    }

    private boolean isUpperAndLowerCase(){
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

    private boolean isAtleastMinLength(){
        return password.length() >= MIN_LENGTH_PASSWORD;
    }
}

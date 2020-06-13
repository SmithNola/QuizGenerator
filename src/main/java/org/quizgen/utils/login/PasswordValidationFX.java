package org.quizgen.utils.login;

import javafx.scene.control.TextField;

public class PasswordValidationFX {
    private TextField passwordField;
    private String password;
    private final String[] SPECIAL_CHAR = {"*","&","^", "%"};
    private final int MIN_LENGTH_PASSWORD = 8;

    public PasswordValidationFX(TextField passwordField){
        this.passwordField = passwordField;
        this.password = passwordField.getText();
    }

    public String passwordRegisterError(){
        if(!isAlphaNumeric()){
            return "Password must contain at least 1 letter or number";
        }
        else if(!isContainsSpecialCharacter()){
            return "Password must contain a special character: *, &, ^, or %";
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
            if(isAlphabet && isNumerical){
                return true;
            }
        }
        return isAlphabet && isNumerical;
    }

    private boolean isContainsSpecialCharacter(){
        for(String specialCharacter: SPECIAL_CHAR){
            if(password.contains(specialCharacter)){
                return true;
            }
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
            if(lowerCase && upperCase){
                return true;
            }
        }
        return lowerCase && upperCase;
    }

    private boolean isAtleastMinLength(){
        return password.length() >= MIN_LENGTH_PASSWORD;
    }
}

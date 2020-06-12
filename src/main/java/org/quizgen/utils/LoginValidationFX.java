package org.quizgen.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.quizgen.data.DatabaseConnection;

import java.sql.SQLException;

public class LoginValidationFX {
    private Label errorMessage;
    private final String[] SPECIAL_CHAR = {"*","&","^", "%"};
    private final int MIN_LENGTH_PASSWORD = 8;

    public LoginValidationFX(Label errorMessage){
        this.errorMessage = errorMessage;
    }

    private boolean userRegisterSuccessful(TextField username){
        return !fieldIsBlank(username) && !usernameIsTaken(username);
    }

    public void usernameRegisterError(TextField username){
        if(fieldIsBlank(username)){
            fieldIsBlankError();
        }
        else if(usernameIsTaken(username)){
            usernameIsTakenError();
        }
    }

    private boolean passwordRegisterSuccessful(TextField passwordField){
        String password = passwordField.getText();
        return isAlphaNumeric(password)
                && isContainsSpecialCharacter(password)
                && isUpperAndLowerCase(password)
                && isAtleastMinLength(password);
    }

    private void passwordRegisterError(TextField passwordField){
        String password = passwordField.getText();
        if(!isAlphaNumeric(password)){
            errorMessage.setText("Password must contain at least 1 letter or number");
        }
        else if(!isContainsSpecialCharacter(password)){
            errorMessage.setText("Password must contain a special character: *, &, ^, or %");
        }
    }

    private boolean fieldIsBlank(TextField field){
        return field.getText().isBlank();
    }

    private void fieldIsBlankError(){
        errorMessage.setText("All fields must be filled out!");
    }

    private boolean usernameIsTaken(TextField username){
        try{
            return DatabaseConnection.checkUsername(username.getText());
        } catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    private void usernameIsTakenError(){
        errorMessage.setText("Username already taken!");
    }

    private boolean passwordsMatches(TextField password, TextField reenterPassword){
        return password.getText().equals(reenterPassword.getText());
    }

    private void passwordMatchesError(){
        errorMessage.setText("Passwords do not match!");
    }

    private boolean isAlphaNumeric(String password){
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

    private boolean isContainsSpecialCharacter(String password){
        for(String specialCharacter: SPECIAL_CHAR){
            if(password.contains(specialCharacter)){
                return true;
            }
        }
        return false;
    }

    private boolean isUpperAndLowerCase(String password){
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

    private boolean isAtleastMinLength(String password){
        return password.length() >= MIN_LENGTH_PASSWORD;
    }


}

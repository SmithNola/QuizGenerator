package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;
import org.quizgen.domain.scenehandling.SceneLoader;
import org.quizgen.domain.scenehandling.SceneTransition;
import org.quizgen.domain.authentication.AuthError;
import org.quizgen.domain.authentication.PasswordHash;
import org.quizgen.domain.authentication.SignupAuth;
import org.quizgen.domain.scenehandling.Views;

import java.io.IOException;

public class SignUpController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1;
    private final int ERRORMESSAGE_LENGTH_LIMIT = 30;
    private final int USERNAME = 0;
    private final int PASSWORD = 1;
    private final int REPASSWORD = 2;

    public SignUpController(){
        this.sceneTransition = new SceneTransition(DELAY_DURATION);
    }

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField rePassword;
    @FXML
    private Label errorMessage;
    @FXML
    private Label longErrorMessage;

    @FXML
    private void switchToStartPage() throws IOException {
        SceneLoader.setRoot(Views.START);
    }

    @FXML
    private void switchToHomePage(){
        String[] signupInfo = signupFields();
        String errorMessage = SignupAuth.signupValidity(signupInfo);
        boolean signUpInfoIsValid = errorMessage.equals(AuthError.NO_ERROR.toString());

        if(signUpInfoIsValid){
            registerUser(signupInfo);
            sceneTransition.startSceneSwitchDelay(Views.HOME);
        }
        else {
            displayErrorMessage(errorMessage);
        }
    }

    private String[] signupFields(){
        return new String[]{username.getText(), password.getText(), rePassword.getText()};
    }

    private void registerUser(String[] args){
        String user = args[USERNAME];
        String salt = PasswordHash.getSalt();
        String key =  PasswordHash.getHashedPassword(args[PASSWORD], salt).get();
        DatabaseConnection.addUser(user, key, salt);
        // Save username to static field to use throughout the application
        User.setUsername(username.getText());
        displaySignupSuccessText();
    }

    private void displaySignupSuccessText(){
        errorMessage.setStyle("-fx-text-fill: green");
        errorMessage.setText("Signup Successful!");
    }

    private void displayErrorMessage(String error){
        if(error.length() > ERRORMESSAGE_LENGTH_LIMIT){
            longErrorMessage.setText(error);
        }
        else{
            errorMessage.setText(error);
        }
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent ae){
        if(ae.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}
package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.CustomGUI;
import org.quizgen.domain.authentication.AuthError;
import org.quizgen.domain.authentication.PasswordHash;
import org.quizgen.domain.authentication.SignupAuth;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.model.User;

public class SignUpController {

    private final int USERNAME = 0;
    private final int PASSWORD = 1;

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField rePassword;


    @FXML
    private void switchToStartPage(){
        SceneHandler.setRoot(Views.START);
    }

    @FXML
    private void switchToHomePage(){
        String[] signupInfo = signupFields();
        String errorMessage = SignupAuth.signupError(signupInfo);
        boolean signUpInfoIsValid = errorMessage.equals(AuthError.NO_ERROR.toString());

        if(signUpInfoIsValid){
            registerUser(signupInfo);
            SceneHandler.setRoot(Views.HOME);
        }
        else {
            CustomGUI.createAlert(errorMessage, SceneHandler.getStage());
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
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent ae){
        if(ae.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}
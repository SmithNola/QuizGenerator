package org.quizgen.controller.authentication;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.authentication.LoginAuth;
import org.quizgen.model.User;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.authentication.AuthError;
import org.quizgen.domain.authentication.PasswordHash;
import org.quizgen.domain.authentication.SignupAuth;
import org.quizgen.domain.scenehandling.Views;

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
        String errorMessage = SignupAuth.signupValidity(signupInfo);
        boolean signUpInfoIsValid = errorMessage.equals(AuthError.NO_ERROR.toString());

        if(signUpInfoIsValid){
            registerUser(signupInfo);
            SceneHandler.setRoot(Views.HOME);
        }
        else {
            createLoginAlert(errorMessage);
        }
    }

    public void createLoginAlert(String errorMessage){
        Label label = new Label();
        label.setFont(new Font("Monospaced Bold", 16));
        label.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
        label.setTextFill(Color.WHITE);
        label.setText(errorMessage);
        JFXAlert<Void> loginErrorAlert = new JFXAlert<>(SceneHandler.getStage());
        loginErrorAlert.setOverlayClose(true);
        loginErrorAlert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        loginErrorAlert.setContent(label);
        loginErrorAlert.initModality(Modality.NONE);
        loginErrorAlert.show();
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
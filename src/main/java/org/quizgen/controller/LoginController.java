package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.SceneTransition;
import org.quizgen.utils.authentication.LoginValidation;
import org.quizgen.utils.authentication.SignupAuth;
import org.quizgen.view.Views;

import java.io.IOException;

public class LoginController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1.5;
    private SignupAuth loginValidator;


    public LoginController(){
        this.sceneTransition = new SceneTransition(DELAY_DURATION);
        this.loginValidator = new SignupAuth();
    }

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label errorMessage;


    @FXML
    private void switchToStartPage() throws IOException{
        SceneLoader.switchScene(Views.START);
    }

    @FXML
    private void switchToHomePage() {
        String error = LoginValidation.getLoginErrorMessage(username.getText(), password.getText());
        if(error.isBlank()){
            displaySigninSuccessText();
            sceneTransition.startSceneSwitchDelay(Views.HOME);
        }
        else{
            errorMessage.setText(error);
        }
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }

    private void displaySigninSuccessText(){
        errorMessage.setStyle("-fx-text-fill: green");
        errorMessage.setText("Signin Successful!");
    }
}

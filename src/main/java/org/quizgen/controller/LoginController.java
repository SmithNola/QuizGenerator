package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.SceneTransition;
import org.quizgen.utils.login.AccountValidator;
import org.quizgen.view.Views;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1.5;
    private static String loginName;
    private AccountValidator loginValidator;


    public LoginController(){
        this.sceneTransition = new SceneTransition(DELAY_DURATION);
        this.loginValidator = new AccountValidator();
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
    private void switchToHomePage() throws IOException, SQLException {
        String error = loginValidator.getLoginErrorMessage(username.getText(), password.getText());
        if(error.isBlank()){
            displaySigninSuccessText();
            sceneTransition.startSceneSwitchDelay(Views.HOME);
        }
        else{
            errorMessage.setText(error);
        }
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent ae) throws IOException, SQLException {
        if(ae.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }

    private void displaySigninSuccessText(){
        errorMessage.setStyle("-fx-text-fill: green");
        errorMessage.setText("Signin Successful!");
    }

    public static String getUsername(){
        return loginName;
    }
}

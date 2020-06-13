package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.SceneTransition;
import org.quizgen.utils.login.AccountValidator;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1.5;
    private AccountValidator signUp;
    private final int ERRORMESSAGE_LENGTH_LIMIT = 30;

    public SignUpController(){
        this.sceneTransition = new SceneTransition(DELAY_DURATION);
        this.signUp = new AccountValidator();
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
        SceneLoader.switchScene(Views.START);
    }

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        errorMessage.setText("");
        longErrorMessage.setText("");
        String signUpError = signUp.getSignUpErrorMessage(username.getText(), password.getText(), rePassword.getText());

        if(signUpError.equals("")){
            registerUser();
            sceneTransition.startSceneSwitchDelay(Views.HOME);
        }
        else {
            displayErrorMessage(signUpError);
        }
    }

    private void registerUser() throws SQLException {
        DatabaseConnection.addUser(username.getText(),password.getText());
        HomePageController.setUsername(username.getText());
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
    private void handleOnKeyPressed(KeyEvent ae) throws IOException, SQLException {
        if(ae.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}
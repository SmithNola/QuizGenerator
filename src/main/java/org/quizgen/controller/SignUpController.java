package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.SceneTransition;
import org.quizgen.utils.authentication.AccountError;
import org.quizgen.utils.authentication.HashPassword;
import org.quizgen.utils.authentication.SignupAuth;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1;
    private final int ERRORMESSAGE_LENGTH_LIMIT = 30;

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
    public void intiatilize(){
        errorMessage.setText("");
        longErrorMessage.setText("");
    }

    @FXML
    private void switchToStartPage() throws IOException {
        SceneLoader.switchScene(Views.START);
    }

    @FXML
    private void switchToHomePage(){
        String errorMessage = SignupAuth.signupValidity(username.getText(), password.getText(), rePassword.getText());
        boolean signUpInfoIsValid = errorMessage.equals(AccountError.NO_ERROR.toString());

        if(signUpInfoIsValid){
            String user = username.getText();
            String salt = HashPassword.getSalt();
            String key = HashPassword.getHashedPassword(password.getText(), salt).get();
            registerUser(user, salt, key);
            sceneTransition.startSceneSwitchDelay(Views.HOME);
        }
        else {
            displayErrorMessage(errorMessage);
        }
    }

    private void registerUser(String user, String key, String salt){
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
    private void handleOnKeyPressed(KeyEvent ae) throws IOException, SQLException {
        if(ae.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}
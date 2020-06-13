package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.SceneTransition;
import org.quizgen.utils.login.SignUpValidationFX;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    private SceneTransition sceneTransition;
    private final double DELAY_DURATION = 1.5;
    private SignUpValidationFX signUp;
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
    private void switchToStartPage() throws IOException {
        SceneLoader.switchScene(Views.START);
    }

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        initiliazeSignup();

        if(signUp.signUpSuccessful()){
            registerUser();
            sceneTransition.beforeSceneTransitionDelay(Views.HOME);
        }
        else {
            displayErrorMessage(signUp.setErrorMessage());
        }
    }

    private void initiliazeSignup(){
        this.signUp = new SignUpValidationFX(username, password, rePassword);
        errorMessage.setText("");
        longErrorMessage.setText("");
    }

    private void registerUser() throws SQLException {
        DatabaseConnection.addUser(username.getText(),password.getText());
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
        else if(ae.getCode() == KeyCode.BACK_SPACE){
            switchToStartPage();
        }
    }
}
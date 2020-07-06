package org.quizgen.controller.authentication;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.CustomGUI;
import org.quizgen.model.User;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.authentication.LoginAuth;
import org.quizgen.domain.scenehandling.Views;


public class LoginController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Hyperlink resetPassword;

    @FXML
    private void switchToStartPage(){
        SceneHandler.setRoot(Views.START);
    }

    @FXML
    private void switchToHomePage() {
        boolean loginIsValid = LoginAuth.checkLoginIsValid(username.getText(), password.getText());
        if(loginIsValid){
            User.setUsername(DatabaseConnection.getUsername(username.getText())); // Sets the current user of this app
            SceneHandler.setRoot(Views.HOME);
        }
        else{
            CustomGUI.createAlert(LoginAuth.getLoginError(), SceneHandler.getStage());
        }
    }

    @FXML
    private void switchToResetPage(){
        SceneHandler.setRoot(Views.RESET);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}

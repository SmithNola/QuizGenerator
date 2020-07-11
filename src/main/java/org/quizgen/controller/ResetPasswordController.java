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
import org.quizgen.domain.authentication.ResetAuth;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;

public class ResetPasswordController {
    /*
        - check if username is valid
        - check if old password is valid
        - change password
            - check if password meets requirements
            - update old password, with new password
     */

    private final ResetAuth resetAuth = new ResetAuth();

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField rePassword;



    @FXML
    private void switchToLoginPage() {
        String errorMessage = resetAuth.resetError(username.getText(), password.getText(), rePassword.getText());
        boolean resetPWIsValid = errorMessage.equals(AuthError.NO_ERROR.toString());
        if(resetPWIsValid){
            updateUserPassword(username.getText(), rePassword.getText());
            SceneHandler.setRoot(Views.LOGIN);
        }
        else{
            CustomGUI.createAlert(errorMessage, SceneHandler.getStage());
        }
    }

    private void updateUserPassword(String username, String rePassword){
        String salt = PasswordHash.getSalt();
        String key =  PasswordHash.getHashedPassword(rePassword, salt).get();
        DatabaseConnection.updateUserPassword(username, key, salt);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            switchToLoginPage();
        }
    }

    @FXML
    private void switchToStartPage(){
        SceneHandler.setRoot(Views.START);
    }
}

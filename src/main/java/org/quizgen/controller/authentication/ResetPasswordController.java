package org.quizgen.controller.authentication;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.CustomGUI;
import org.quizgen.domain.authentication.LoginAuth;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.model.User;

public class ResetPasswordController {
    /*
        - check if username is valid
        - check if old password is valid
        - change password
            - check if password meets requirements
            - update old password, with new password
     */


    @FXML
    private void switchToLoginPage() {
        // TO-DO: create main password reset logic here
        //        and switch scene to login.fxml
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

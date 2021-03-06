package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;

import static org.quizgen.data.DatabaseConnection.isConnected;

public class StartPageController {


    @FXML
    private Label errorMessage;

    @FXML
    public void switchToLoginPage(){
        if(isConnected()) {
            SceneHandler.setRoot(Views.LOGIN);
        } else {
            errorMessage.setText("Error! could not connect to database");
        }
    }

    @FXML
    public void switchToSignUpPage(){
        if (isConnected()) {
            SceneHandler.setRoot(Views.SIGNUP);
        } else {
            errorMessage.setText("Error! could not connect to database");
        }
    }
}

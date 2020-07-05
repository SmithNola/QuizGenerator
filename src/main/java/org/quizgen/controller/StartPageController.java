package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

import static org.quizgen.data.DatabaseConnection.isConnected;

public class StartPageController {


    @FXML
    private Label errorMessage;

    @FXML
    public void switchToLoginPage() throws IOException {
        if(isConnected()) {
            SceneLoader.setRoot(Views.LOGIN);
        } else {
            errorMessage.setText("Error! could not connect to database"); //replace with error pop-up window
        }
    }

    @FXML
    public void switchToSignUpPage() throws IOException {
        if (isConnected()) {
            SceneLoader.setRoot(Views.SIGNUP);
        } else {
            System.out.println("Error! could not connect to database"); //replace with error pop-up window
            errorMessage.setText("Error! could not connect to database");
        }
    }
}

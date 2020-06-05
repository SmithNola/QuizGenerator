package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

import static org.quizgen.data.DatabaseConnection.isConnected;

public class StartPageController {


    @FXML
    private Text errorMessage;

    @FXML
    public void switchToLoginPage() throws IOException {
        if(isConnected()) {
            SceneLoader.switchScene(Views.LOGIN);
        } else {
            errorMessage.setText("Error! could not connect to database"); //replace with error pop-up window
        }
    }

    @FXML
    public void switchToSignUpPage() throws IOException {
        if (isConnected()) {
            SceneLoader.switchScene(Views.SIGNUP);
        } else {
            System.out.println("Error! could not connect to database"); //replace with error pop-up window
        }
    }
}

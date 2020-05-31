package org.example.help;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class StartPageController {

    @FXML
    private Text errorMessage;

    @FXML
    private void switchToLogin() throws IOException {
        boolean start = DatabaseConnection.connect();
        if(start) {
            App.setRoot("login");
        }
        else{
            errorMessage.setText("Could not connect to database");
        }
    }

    @FXML
    private void switchToSignUp() throws IOException {
        boolean start = DatabaseConnection.connect();
        if(start) {
            App.setRoot("signUp");
        }
        else{
            errorMessage.setText("Could not connect to database");
        }
    }
}

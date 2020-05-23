package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class StartPageController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToSignUp() throws IOException {
        App.setRoot("signUp");
    }
}

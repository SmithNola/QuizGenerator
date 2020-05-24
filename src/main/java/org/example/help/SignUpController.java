package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class SignUpController {

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("homePage");
    }
}
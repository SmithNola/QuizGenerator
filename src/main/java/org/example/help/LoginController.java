package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("homePage");
    }
}

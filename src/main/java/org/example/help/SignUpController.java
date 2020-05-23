package org.example.help;

import java.io.IOException;
import javafx.fxml.FXML;

public class SignUpController {

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("homePage");
    }
}
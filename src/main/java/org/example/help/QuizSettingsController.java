package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class QuizSettingsController {
    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("create");
    }
}

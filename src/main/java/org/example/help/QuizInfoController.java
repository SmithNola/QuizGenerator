package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class QuizInfoController {
    @FXML
    private void switchToPlaying() throws IOException {
        App.setRoot("playing");
    }
}

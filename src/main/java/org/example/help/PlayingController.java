package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class PlayingController {
    @FXML
    private void switchToScore() throws IOException {
        App.setRoot("score");
    }
}

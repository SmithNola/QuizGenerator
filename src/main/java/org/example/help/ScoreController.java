package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class ScoreController {
    @FXML
    private void switchToPlayView() throws IOException {
        App.setRoot("playview");
    }
}

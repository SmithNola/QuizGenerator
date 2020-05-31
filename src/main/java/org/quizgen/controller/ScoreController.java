package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class ScoreController {
    @FXML
    private void switchToPlayView() throws IOException {
        App.setRoot("playview");
    }
}

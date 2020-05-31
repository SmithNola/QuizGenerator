package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class PlayingController {
    @FXML
    private void switchToScore() throws IOException {
        App.setRoot("score");
    }
}

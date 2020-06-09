package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.quizgen.App;

import java.io.IOException;

public class ScoreController {

    @FXML
    private Label score;

    public void initialize(){
        score.setText(PlayingController.getScore() + "%");
    }

    @FXML
    private void switchToPlayView() throws IOException {
        App.setRoot("playview");
    }
}

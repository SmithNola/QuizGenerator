package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class ScoreController {

    @FXML
    private Label score;

    public void initialize(){
        score.setText(PlayingController.getScore() + "%");
    }

    @FXML
    private void switchToPlayView() throws IOException {
        SceneLoader.switchScene(Views.PLAYVIEW);
    }
}

package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class ScoreController {
    @FXML
    private void switchToPlayView() throws IOException {
        SceneLoader.switchScene(Views.PLAYVIEW);
    }
}

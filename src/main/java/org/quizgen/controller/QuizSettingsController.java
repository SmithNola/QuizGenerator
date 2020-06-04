package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class QuizSettingsController {
    @FXML
    private void switchToCreate() throws IOException {
        SceneLoader.switchScene(Views.CREATING);
    }
}

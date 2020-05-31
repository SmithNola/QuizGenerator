package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class QuizSettingsController {
    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("creating");
    }
}

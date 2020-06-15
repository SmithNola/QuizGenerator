package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class HomePageController {
    private static String username;

    @FXML
    private void switchToPlay() throws Exception{
        SceneLoader.switchScene(Views.PLAYVIEW);
    }

    @FXML
    private void switchToCreate() throws IOException {
        SceneLoader.switchScene(Views.CREATEVIEW);
    }
}

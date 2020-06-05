package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class HomePageController {

    @FXML
    private void switchToPlay() throws Exception{
        SceneLoader.switchScene(Views.PLAYVIEW);
    }

    @FXML
    private void switchToCreate() throws IOException {
        SceneLoader.switchScene(Views.CREATEVIEW);
    }
}

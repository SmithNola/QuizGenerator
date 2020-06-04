package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

public class EditingController {
    @FXML
    private void switchToCreateView() throws IOException{
        SceneLoader.switchScene(Views.CREATEVIEW);
    }
}

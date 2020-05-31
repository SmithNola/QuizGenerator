package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class HomePageController {

    @FXML
    private void switchToPlay() throws Exception{
        App.setRoot("playView");
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("createView");
    }
}

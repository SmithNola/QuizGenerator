package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class CreatingController {
    @FXML
    private void switchToCreateView() throws IOException {
        App.setRoot("createView");
    }
}

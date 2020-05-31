package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class CreateViewController {
    @FXML
    private void switchToQuizInfo() throws IOException{
        App.setRoot("quizInfo");
    }
    @FXML
    private void switchToQuizSettings() throws IOException{
        App.setRoot("quizSettings");
    }
}

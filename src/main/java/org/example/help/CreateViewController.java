package org.example.help;

import javafx.fxml.FXML;

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

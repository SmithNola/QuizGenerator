package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class PlayViewController {
    @FXML
    private void switchToQuizInfo() throws IOException {
        App.setRoot("quizInfo");
    }

}

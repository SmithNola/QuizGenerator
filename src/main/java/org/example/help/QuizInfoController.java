package org.example.help;

import javafx.fxml.FXML;

import java.io.IOException;

public class QuizInfoController {
    @FXML
    private void switchToPlaying() throws IOException {
        App.setRoot("playing");
    }
    @FXML
    private void switchToEditing() throws IOException{
        App.setRoot("editing");
    }
}

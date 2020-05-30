package org.example.help;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.io.IOException;

public class QuizInfoController{
    @FXML
    private Text nameText = new Text();
    @FXML
    private Text creatorText = new Text();
    @FXML
    private Text numText = new Text();
    @FXML
    public void initialize(){
        Quiz quiz = PlayViewController.getClickedQuiz();
        nameText.setText(quiz.getName());
        creatorText.setText(quiz.getCreator());
        numText.setText(String.valueOf(quiz.getNumberOfQuestions()));
    }

    @FXML
    private void switchToPlaying() throws IOException {
        App.setRoot("playing");
    }

    @FXML
    private void switchToEditing() throws IOException{
        App.setRoot("editing");
    }
}

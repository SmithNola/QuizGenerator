package org.example.help;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizInfoController{
    @FXML
    private static Text nameText = new Text();
    @FXML
    private static Text creatorText = new Text();
    @FXML
    private static Text numText = new Text();
    @FXML
    public void initialize(){
        Quiz quiz = PlayViewController.getClickedQuiz();
        nameText.setText(quiz.getName());
        creatorText.setText(quiz.getCreator());
        numText.setText(String.valueOf(quiz.getQuestions().size()));
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

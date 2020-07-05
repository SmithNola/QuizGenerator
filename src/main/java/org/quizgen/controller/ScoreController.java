package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.playing.AnswerChecker;
import org.quizgen.view.Views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreController {

    @FXML
    private Label score;
    @FXML
    private VBox results;
    @FXML
    private Label quizName;

    public void initialize(){
        score.setText(AnswerChecker.getScore() + "%");
        Quiz quiz = PlayingController.getQuiz();
        quizName.setText(quiz.getName());
        HashMap<String,Integer> chosenAnswers = PlayingController.getChosenAnswers();
        ArrayList<Question> questions = quiz.getQuestions();
        for(int i = 0; i < questions.size(); i++){
            int CA = chosenAnswers.get(questions.get(i).getQuestionId() + "");
            results.getChildren().add(AnswerChecker.showRightWrong(questions.get(i),CA,i+1));
        }
    }

    @FXML
    private void switchToPlayView() throws IOException {
        SceneLoader.setRoot(Views.DISPLAYQUIZZES);
    }
    @FXML
    private void returnToStart() throws IOException{
        DatabaseConnection.disconnect();
        SceneLoader.setRoot(Views.START);
    }
}
package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.answers.AnswerChecker;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

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
        ArrayList<Question> questions = quiz.getQuestions();
        displayResults(questions);
    }

    private void displayResults(ArrayList<Question> questions){
        HashMap<Integer,String> chosenAnswers = PlayingController.getChosenAnswers();
        AnswerChecker ac = new AnswerChecker();
        for(int i = 0; i < questions.size(); i++){
            String CA = chosenAnswers.get(questions.get(i).getQuestionId());
            results.getChildren().add(ac.showRightWrong(questions.get(i),CA,i+1));
        }
    }

    @FXML
    private void switchToPlayView(){
        SceneHandler.setRoot(Views.DISPLAYQUIZZES);
    }
    @FXML
    private void returnToStart(){
        DatabaseConnection.disconnect();
        SceneHandler.setRoot(Views.START);
    }
}
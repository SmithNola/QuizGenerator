package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;
import org.quizgen.utils.SceneLoader;
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
        score.setText(PlayingController.getScore() + "%");
        System.out.println(PlayingController.getScore());
        Quiz quiz = PlayingController.getQuiz();
        quizName.setText(quiz.getName());
        HashMap<String,Integer> chosenAnswers = PlayingController.getChosenAnswers();
        ArrayList<Question> questions = quiz.getQuestions();
        for(int i = 0; i < questions.size(); i++){
            int CA = chosenAnswers.get(questions.get(i).getQuestionId() + "");
            results.getChildren().add(createVbox(questions.get(i),CA,i+1));
        }
    }

    private VBox createVbox(Question question, int chosenAnswer,int questionNumber){
        VBox questionWithAnswers = new VBox();
        ArrayList<Choice> choices = question.getChoices();
        Label questionName = new Label(questionNumber + ": " + question.getName());
        String CA = choices.get(chosenAnswer - 1).getName();
        Label userChoice = new Label("You Choice: " + CA);
        String answer = question.getChoices().get(question.getAnswer()-1).getName();
        Label correctAnswer = new Label("Correct Answer: " + answer);
        if(CA.equals(answer)){//color to indicate whether right or wrong
            userChoice.setTextFill(Color.GREEN);
        }else{
            userChoice.setTextFill(Color.DARKRED);
        }
        questionWithAnswers.getChildren().addAll(questionName,userChoice,correctAnswer);
        return questionWithAnswers;
    }

    @FXML
    private void switchToPlayView() throws IOException {
        SceneLoader.switchScene(Views.DISPLAYQUIZZES);
    }
}
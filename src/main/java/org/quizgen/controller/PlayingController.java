package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayingController {
    private int questionNum = 1;
    private HashMap<String, Integer> chosenAnswers = new HashMap<>();
    ArrayList<Question> questions = new ArrayList<>();
    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox overall;

    private static int score;

    @FXML
    public void initialize(){
        quiz = PlayViewController.getClickedQuiz();
        quizName.setText(quiz.getName());
        try{
            quiz = DatabaseConnection.retrieveQuestions(quiz);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        questions = quiz.getQuestions();
        for(Question question: questions){
            VBox questionLayout = new VBox(question.getChoices().size()+1);
            overall.getChildren().add(createVbox(question, questionLayout));
        }
        Button saveButton = new Button();
        saveButton.setText("Done");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try{
                    score = calculateScore();
                    if(!DatabaseConnection.checkIfPlayed(HomePageController.getUsername(),quiz.getQuizId())){
                        DatabaseConnection.saveScore(score, HomePageController.getUsername(), quiz.getQuizId());
                    }
                    App.setRoot("score");
                }catch(IOException | SQLException e){
                    e.printStackTrace();
                }
            }
        } );
        overall.getChildren().add(saveButton);
    }

    private VBox createVbox(Question question, VBox questionLayout){
        ArrayList<String> choices = question.getChoices();
        Label questionName = new Label (questionNum + ". " + question.getName());
        questionLayout.getChildren().add(questionName);
        ToggleGroup group = new ToggleGroup();
        for(int i = 0; i < choices.size(); i++){
            RadioButton choice = new RadioButton((i+1) + " " + choices.get(i));
            choice.setId(String.valueOf(question.getQuestionId()));
            choice.setToggleGroup(group);
            choice.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    String[] a = choice.getText().split(" ");
                    int b = Integer.parseInt(a[0]);
                   chosenAnswers.put(choice.getId(),b);
                }
            } );
            questionLayout.getChildren().add(choice);
        }
        questionNum++;

        return questionLayout;
    }

    private int calculateScore(){
        double correctAnswers = 0;
        for(int i = 0; i < quiz.getQuestions().size(); i++){
            if (chosenAnswers.get(questions.get(i).getQuestionId()).equals(questions.get(i).getAnswer())){
                correctAnswers++;
            }
        }
        return (int) ((correctAnswers / questions.size())*100);
    }

    public static int getScore(){
        return score;
    }
}

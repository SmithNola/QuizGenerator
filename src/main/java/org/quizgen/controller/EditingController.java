package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.quizgen.App;

import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditingController {
    private int questionNum = 1;
    private HashMap<String, Integer> chosenAnswers = new HashMap<>();
    ArrayList<Question> questions = new ArrayList<>();
    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox overall;

    @FXML
    public void initialize(){
        quiz = CreateViewController.getClickedQuiz();
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
                    SceneLoader.switchScene(Views.CREATEVIEW);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } );
        overall.getChildren().add(saveButton);
    }

    private VBox createVbox(Question question, VBox questionLayout){
        ArrayList<String> choices = question.getChoices();
        TextField questionName = new TextField (questionNum + ". " + question.getName());
        questionLayout.getChildren().add(questionName);
        ToggleGroup group = new ToggleGroup();
        for(int i = 1; i < choices.size() + 1; i++){
            RadioButton choice = new RadioButton(i + " " + choices.get(i-1));
            choice.setId(String.valueOf(question.getQuestionId()));
            choice.setToggleGroup(group);
            if(i == question.getAnswer()){
                choice.setSelected(true);
            }
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
}

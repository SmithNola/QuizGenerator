package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditingController {
    private int questionNum = 1;
    //private HashMap<String, Integer> chosenAnswers = new HashMap<>();
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
            VBox questionWithChoice = new VBox(question.getChoices().size()+1);
            overall.getChildren().add(createVbox(question, questionWithChoice));//will create the layout for each question and choice
        }
        Button saveButton = new Button();
        saveButton.setText("Done");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try{
                    App.setRoot("createView");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } );
        overall.getChildren().add(saveButton);
    }

    private VBox createVbox(Question question, VBox questionWithChoice){
        HBox questionLayout = new HBox();
        ArrayList<String> choices = question.getChoices();
        Label questionTracker = new Label(questionNum + ". ");
        TextField questionName = new TextField (question.getName());
        questionLayout.getChildren().addAll(questionTracker,questionName);
        questionWithChoice.getChildren().add(questionLayout);
        ToggleGroup group = new ToggleGroup();
        for(int i = 1; i < choices.size() + 1; i++){
            HBox choiceLayout = new HBox();
            Label choiceTracker = new Label(i + ". ");
            RadioButton choice = new RadioButton(choices.get(i-1));
            //choice.setId(String.valueOf(question.getQuestionId()));
            choice.setToggleGroup(group);
            if(i == question.getAnswer()){//will toggle the answer of the question
                choice.setSelected(true);
            }
            choiceLayout.getChildren().addAll(choiceTracker, choice);
            questionWithChoice.getChildren().add(choiceLayout);
        }
        questionNum++;

        return questionWithChoice;
    }
}
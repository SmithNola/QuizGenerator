package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.quizgen.App;
import org.quizgen.model.Quiz;
import java.io.IOException;

public class QuizInfoController{
    @FXML
    private Text nameText = new Text();
    @FXML
    private Text creatorText = new Text();
    @FXML
    private Text numText = new Text();
    @FXML
    private VBox overall;
    @FXML
    public void initialize(){
        //Will check if previous page was PlayView or CreateView
        if(PlayViewController.getClickedQuiz().getQuizId() != 0){
            Quiz quiz = PlayViewController.getClickedQuiz();
            nameText.setText(quiz.getName());
            creatorText.setText(quiz.getCreator());
            numText.setText(String.valueOf(quiz.getNumberOfQuestions()));
            Button play = new Button("Play");
            play.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    try{
                        App.setRoot("playing");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            } );
            overall.getChildren().add(play);
        }else{
            Quiz quiz = CreateViewController.getClickedQuiz();
            nameText.setText(quiz.getName());
            creatorText.setText(quiz.getCreator());
            numText.setText(String.valueOf(quiz.getNumberOfQuestions()));
            Button edit = new Button("Edit");
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    try{
                        App.setRoot("quizSettings");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            } );
            overall.getChildren().add(edit);
        }
    }
}

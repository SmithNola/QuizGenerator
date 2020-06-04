package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.App;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;
import org.quizgen.model.Quiz;


import java.io.IOException;

public class CreatingController {

    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox overall;
    private int count = 1;

    public void initialize(){
        quiz=CreateViewController.getClickedQuiz();
        quizName.setText(quiz.getName());
        newQuestion();
    }

    private void newQuestion(){
        HBox questionTracker = new HBox();
        VBox questionWithChoice = new VBox();
        Label questionNum = new Label(count + ": ");
        TextField question = new TextField();
        Button addNew = new Button("+");
        addNew.setOnAction(new EventHandler<ActionEvent>() {//Will add a new question with choice

            @Override
            public void handle(ActionEvent arg0){
                addNew.setVisible(false);
                newQuestion();
            }
        } );
        Button deleteOld = new Button("-");
        deleteOld.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                overall.getChildren().remove(questionWithChoice);
            }
        });
        questionTracker.getChildren().addAll(questionNum,question,addNew,deleteOld);//creates number + question + button
        questionWithChoice.getChildren().addAll(questionTracker);
        overall.getChildren().add(questionWithChoice);
        newChoice(questionWithChoice);
        count++;
    }

    private void newChoice(VBox questionWithChoice){
        HBox choiceTracker = new HBox();
        TextField choice = new TextField();
        choice.setMaxWidth(80);
        Button addNew = new Button("+");
        addNew.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0){
                addNew.setVisible(false);
                newChoice(questionWithChoice);
            }
        } );
        Button deleteOld = new Button("-");
        deleteOld.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                questionWithChoice.getChildren().remove(choiceTracker);
            }
        });
        choiceTracker.getChildren().addAll(choice,addNew,deleteOld);
        questionWithChoice.getChildren().add(choiceTracker);
    }

    @FXML
    private void switchToCreateView() throws IOException {
        SceneLoader.switchScene(Views.CREATEVIEW);
    }
}
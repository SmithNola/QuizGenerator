package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.quizgen.App;
import org.quizgen.model.Quiz;

import java.io.IOException;

public class QuizSettingsController {

    private Quiz quiz;
    @FXML
    private TextField quizName;
    @FXML
    private TextField quizGenre;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;

    public void initialize(){
        ToggleGroup group = new ToggleGroup();
        yes.setToggleGroup(group);
        no.setToggleGroup(group);
    }

    private void saveProperties(){
        quiz = CreateViewController.getClickedQuiz();
        quiz.setName(quizName.getText());
        quiz.setGenre(quizGenre.getText());
        if(yes.isSelected() == true){
            quiz.setOrdered(1);
        }
        else{
            quiz.setOrdered(0);
        }
    }

    public Quiz getQuiz(){
        return quiz;
    }

    @FXML
    private void switchToCreate() throws IOException {
        saveProperties();
        App.setRoot("creating");
    }
}

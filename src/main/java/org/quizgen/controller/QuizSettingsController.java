package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;
import org.quizgen.model.Quiz;


import java.io.IOException;

public class QuizSettingsController {

    private static Quiz quiz;
    @FXML
    private TextField quizName;
    @FXML
    private TextField quizGenre;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private VBox overall;

    public void initialize(){
        ToggleGroup group = new ToggleGroup();
        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        quiz = CreateViewController.getClickedQuiz();
        quizName.setText(quiz.getName());
        quizGenre.setText(quiz.getGenre());
        if(quiz.getOrdered() == 0){
            yes.setSelected(true);
        }else{
            no.setSelected(true);
        }
        //Will check if a quiz was clicked or create button was clicked
        if(CreateViewController.getClickedQuiz().getQuizId() != 0){
            Button edit = new Button("Edit");
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    try{
                        saveProperties();
                        SceneLoader.switchScene(Views.EDITING);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            } );
            overall.getChildren().add(edit);
        }else{
            Button create = new Button("Create");
            create.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    try{
                        saveProperties();
                        SceneLoader.switchScene(Views.CREATING);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            } );
            overall.getChildren().add(create);
        }
    }

    private void saveProperties(){
        quiz.setName(quizName.getText());
        quiz.setGenre(quizGenre.getText());
        if(yes.isSelected() == true){
            quiz.setOrdered(1);
        }
        else{
            quiz.setOrdered(0);
        }
    }

    public static Quiz getQuiz(){
        return quiz;
    }
}

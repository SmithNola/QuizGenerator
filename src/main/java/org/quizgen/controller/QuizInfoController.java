package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.quizgen.model.Quiz;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

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
    private HBox creatorBox;
    @FXML
    private HBox buttons;
    @FXML
    private Button play;
    @FXML
    private Button edit;
    @FXML
    public void initialize(){
        Quiz quiz = DisplayQuizzesController.getClickedQuiz();
        nameText.setText(quiz.getName());
        numText.setText(String.valueOf(quiz.getNumberOfQuestions()));
        if(HomePageController.getButtonPressed().equals("Play")){//if previous was the PlayView
            creatorText.setText(quiz.getCreator());
            buttons.getChildren().remove(edit);
        }else{//if previous was the CreateView
            overall.getChildren().remove(creatorBox);
            buttons.getChildren().remove(play);
        }
    }

    public void switchToPlaying() throws IOException{
        SceneLoader.switchScene(Views.PLAYING);
    }

    public void switchToQuizSettings() throws IOException{
        SceneLoader.switchScene(Views.QUIZSETTINGS);
    }
}

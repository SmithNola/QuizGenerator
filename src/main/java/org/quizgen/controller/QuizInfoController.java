package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.domain.viewQuizzes.DisplayQuiz;
import org.quizgen.model.Quiz;

public class QuizInfoController{
    @FXML
    private Text nameText = new Text();
    @FXML
    private Text creatorText = new Text();
    @FXML
    private Text numText = new Text();
    @FXML
    private Text creationDateText = new Text();
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
        Quiz quiz = DisplayQuiz.getClickedQuiz();
        nameText.setText(quiz.getName());
        numText.setText(String.valueOf(quiz.getNumberOfQuestions()));
        creationDateText.setText(formatDate(quiz.getCreationTime()));
        if(HomePageController.getButtonPressed().equals("Play")){//if previous was the PlayView
            creatorText.setText(quiz.getCreator());
            buttons.getChildren().remove(edit);
        }else{//if previous was the CreateView
            overall.getChildren().remove(creatorBox);
            buttons.getChildren().remove(play);
        }
    }
    //will get rid of time part of creationTime
    private String formatDate(String date){
        String [] dateSplit= date.split(" ");
        date = dateSplit[0];
        return date;
    }
    @FXML
    private void switchToPlaying(){
        SceneHandler.setRoot(Views.PLAYING);
    }
    @FXML
    private void switchToQuizSettings(){
        SceneHandler.setRoot(Views.QUIZSETTINGS);
    }
}

package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Quiz;
import org.quizgen.model.User;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.viewQuizzes.DisplayQuiz;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayQuizzesController{
    private static String buttonPressed;
    private static ArrayList<Quiz> quizzes = new <Quiz>ArrayList();
    @FXML
    private HBox buttons;
    @FXML
    private VBox overall;
    @FXML
    private VBox showQuizzes;
    @FXML
    private Label creator;
    @FXML
    private Button create;

    @FXML
    public void initialize(){
        buttonPressed = "";
        try{
            if(HomePageController.getButtonPressed().equals("Play")){//for the PlayView
                quizzes = DatabaseConnection.retrieveAllQuizzes();
                buttons.getChildren().remove(create);
            }else{//for the CreateView
                String username = User.getUsername();
                quizzes = DatabaseConnection.retrieveUserQuiz(username);
                overall.getChildren().remove(creator);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        for(Quiz quiz: quizzes){
            HBox quizLayout = new HBox(4);
            showQuizzes.getChildren().add(DisplayQuiz.createQuizVbox(quiz, quizLayout, quizzes));
        }
    }

    @FXML
    private void switchToQuizSettings()throws IOException {
        buttonPressed = create.getText();
        SceneLoader.switchScene(Views.QUIZSETTINGS);
    }

    @FXML
    private void returnToHome() throws IOException {
        SceneLoader.switchScene(Views.HOME);
    }

    @FXML
    private void returnToStart() throws IOException{
        DatabaseConnection.disconnect();
        SceneLoader.switchScene(Views.START);
    }
    public static String getButtonPressed(){
        return buttonPressed;
    }
}
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
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisplayQuizzesController{
    private static int quizId;
    private static ArrayList<Quiz> quizzes = new <Quiz>ArrayList();
    private static Quiz clickedQuiz = new Quiz();
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
            showQuizzes.getChildren().add(createHbox(quiz, quizLayout));
        }
    }

    private static HBox createHbox(Quiz quiz, HBox quizLayout){
        Button quizButton = new Button();
        String quizProperties = quiz.getName() + "\t" + quiz.getGenre() + "\t" + quiz.getCreator() + "\t" + quiz.getCreationTime();
        quizButton.setText(quizProperties);
        quizButton.setId(String.valueOf(quiz.getQuizId()));
        quizButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                quizId = Integer.parseInt(quizButton.getId());
                for(Quiz quiz:quizzes){
                    if(quiz.getQuizId() == quizId){
                        clickedQuiz = quiz;
                        try{
                            SceneLoader.switchScene(Views.QUIZINFO);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } );
        quizLayout.getChildren().addAll(quizButton);
        return quizLayout;
    }

    @FXML
    public void switchToQuizSettings()throws IOException {
            SceneLoader.switchScene(Views.QUIZSETTINGS);
    }

    public static Quiz getClickedQuiz(){
        return clickedQuiz;
    }

    @FXML
    private void returnToHome() throws IOException {
        SceneLoader.switchScene(Views.HOME);
    }

    @FXML
    private void returnToStart() throws IOException{
        SceneLoader.switchScene(Views.START);
    }
}
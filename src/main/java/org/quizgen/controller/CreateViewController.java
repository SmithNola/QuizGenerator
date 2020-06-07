package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateViewController {
    private static int quizId;
    private static ArrayList<Quiz> quizzes = new <Quiz>ArrayList();
    private static Quiz clickedQuiz = new Quiz();
    @FXML
    private VBox overall;
    @FXML
    public void initialize(){
        String username = HomePageController.getUsername();
        try{
            quizzes = DatabaseConnection.retrieveUserQuiz(username);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        for(Quiz quiz: quizzes){
            HBox quizLayout = new HBox(4);
            overall.getChildren().add(createHbox(quiz, quizLayout));
        }
        Button createButton = new Button("Create");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try{
                    App.setRoot("quizSettings");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } );
        overall.getChildren().add(createButton);
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
                            App.setRoot("quizInfo");
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

    public static Quiz getClickedQuiz(){
        return clickedQuiz;
    }
}

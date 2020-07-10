package org.quizgen.domain.viewQuizzes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.model.Quiz;

import java.util.ArrayList;

public class DisplayQuiz{

    private static Quiz clickedQuiz = new Quiz();
    private static int quizId;

    public static HBox createQuizVbox(Quiz quiz, HBox quizLayout, ArrayList<Quiz> quizzes){
        Button quizButton = new Button();
        String quizProperties = quiz.getName() + "\t" + quiz.getGenre() + "\t" + quiz.getCreator() + "\t" + formatDate(quiz.getCreationTime());
        quizButton.setText(quizProperties);
        quizButton.setId(String.valueOf(quiz.getQuizId()));
        quizButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                quizId = Integer.parseInt(quizButton.getId());
                for(Quiz quiz:quizzes){
                    if(quiz.getQuizId() == quizId){
                        DisplayQuiz.setClickedQuiz(quiz);
                        SceneHandler.setRoot(Views.QUIZINFO);
                    }
                }
            }
        } );
        quizLayout.getChildren().addAll(quizButton);
        return quizLayout;
    }


    //will get rid of time part of creationTime
    private static String formatDate(String date){
        String [] dateSplit= date.split(" ");
        date = dateSplit[0];
        return date;
    }

    public static void setClickedQuiz(Quiz newCLickedQuiz){
        clickedQuiz = newCLickedQuiz;
    }

    public static Quiz getClickedQuiz(){
        return clickedQuiz;
    }

}

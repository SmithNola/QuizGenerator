package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.playing.AnswerChecker;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.domain.viewQuizzes.DisplayQuiz;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;
import org.quizgen.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class PlayingController {
    private int questionNum = 1;
    private static HashMap<Integer, String> chosenAnswers = new HashMap<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private static Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox displayQuiz;

    @FXML
    public void initialize(){
        quiz = DisplayQuiz.getClickedQuiz();
        quizName.setText(quiz.getName());
        try{
            quiz = DatabaseConnection.retrieveQuestions(quiz);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        questions = quiz.getQuestions();
        for(Question question: questions){//displays each question
            VBox questionLayout = new VBox(question.getChoices().size()+1);
            displayQuiz.getChildren().add(createQuestionVbox(question, questionLayout));
        }
    }
    //Will create the Vbox contains questions and choices
    private VBox createQuestionVbox(Question question, VBox questionLayout){
        ArrayList<Choice> choices = question.getChoices();
        Label questionName = new Label (questionNum + ". " + question.getName());
        questionLayout.getChildren().add(questionName);
        ToggleGroup group = new ToggleGroup();
        for(int i = 0; i < choices.size(); i++){
            RadioButton choice = new RadioButton((i+1) + " " + choices.get(i).getName());
            choice.setId(String.valueOf(question.getQuestionId()));
            choice.setToggleGroup(group);
            choice.setOnAction(new EventHandler<>(){
                @Override
                public void handle(ActionEvent arg0){
                    String[] a = choice.getText().split(" ");
                    StringBuilder answer = new StringBuilder();
                    if(a.length > 2){
                        for(int i = 1; i < a.length; i++){
                            answer.append(a[i]).append(" ");
                        }
                    }else{
                        answer.append(a[1]);
                    }
                    //int b = Integer.parseInt(a[0]);//retrieves the number from text
                    chosenAnswers.put(Integer.parseInt(choice.getId()), answer.toString().strip());
                }
            } );
            questionLayout.getChildren().add(choice);
        }
        questionNum++;

        return questionLayout;
    }

    public static Quiz getQuiz(){
        return quiz;
    }

    public static HashMap<Integer,String> getChosenAnswers(){
        return chosenAnswers;
    }

    @FXML
    private void finishPlaying() throws SQLException{
        int score;
        if(AnswerChecker.allAnswered(questions, chosenAnswers)){
            score = AnswerChecker.calculateScore(questions, chosenAnswers);
            if(!DatabaseConnection.checkIfPlayed(User.getUsername(), quiz.getQuizId())){
                DatabaseConnection.saveScore(score, User.getUsername(), quiz.getQuizId());
            }
            SceneHandler.setRoot(Views.SCORE);
        }else{
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Answer all questions");
            alert.show();
        }
    }

    @FXML
    private void cancelPlaying(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Your answers will not be not be saved if you cancel.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }
}
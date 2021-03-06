package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.answers.AnswerChecker;
import org.quizgen.domain.errors.AlertMessages;
import org.quizgen.domain.errors.Alerts;
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
                    chosenAnswers.put(Integer.parseInt(choice.getId()), setAnswer(choice.getText()));
                }
            } );
            questionLayout.getChildren().add(choice);
        }
        questionNum++;

        return questionLayout;
    }

    private String setAnswer(String choice){
        String[] a = choice.split(" ");
        StringBuilder answer = new StringBuilder();
        if(a.length > 2){
            for(int i = 1; i < a.length; i++){
                answer.append(a[i]).append(" ");
            }
        }else{
            answer.append(a[1]);
        }
        return answer.toString().strip();
    }

    public static Quiz getQuiz(){
        return quiz;
    }

    public static HashMap<Integer,String> getChosenAnswers(){
        return chosenAnswers;
    }

    @FXML
    private void finishPlaying() throws SQLException{
        AnswerChecker ac = new AnswerChecker();
        int score;
        if(ac.allAnswered(questions, chosenAnswers)){
            score = ac.calculateScore(questions, chosenAnswers);
            if(!DatabaseConnection.checkIfPlayed(User.getUsername(), quiz.getQuizId())){
                DatabaseConnection.saveScore(score, User.getUsername(), quiz.getQuizId());
            }
            SceneHandler.setRoot(Views.SCORE);
        }else{
            Alerts alert = new Alerts(AlertMessages.PLAYINGANSWERS);
            alert.answersAlert();
        }
    }

    @FXML
    private void cancelPlaying(){
        Alerts alert = new Alerts(AlertMessages.CANCELPLAYING);
        Optional<ButtonType> result = alert.cancelAlert();
        if (result.get() == ButtonType.OK){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }
}
package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.domain.answers.AnswerChecker;
import org.quizgen.domain.errors.AlertMessages;
import org.quizgen.domain.errors.Alerts;
import org.quizgen.domain.quizCreation.SaveQuiz;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;
import org.quizgen.model.Quiz;
import org.quizgen.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CreatingController {

    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox questionsBox;
    private int count = 1;
    private final ArrayList<VBox> vboxQuestions = new ArrayList<>();

    public void initialize(){
        quiz = QuizSettingsController.getQuiz();
        quizName.setText(quiz.getName());
        newQuestion();
        newQuestion();
    }

    private void newQuestion(){
        HBox questionTracker = new HBox();
        VBox questionWithChoice = new VBox();
        Label questionNum = new Label(count + ": ");
        TextField question = new TextField();
        Button addNew = new Button("+");
        ToggleGroup group = new ToggleGroup();
        addNew.setOnAction(new EventHandler<ActionEvent>() {//Will add a new question with choice

            @Override
            public void handle(ActionEvent arg0){
                addNew.setVisible(false);
                newQuestion();
            }
        } );
        Button deleteOld = new Button("-");
        deleteOld.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                if(questionsBox.getChildren().size() != 2){//will prevent user from deleting the question if it is the only one
                    vboxQuestions.remove(questionWithChoice);
                    questionsBox.getChildren().remove(questionWithChoice);
                }
            }
        });
        questionTracker.getChildren().addAll(questionNum,question,addNew,deleteOld);//creates number + question + button
        questionWithChoice.getChildren().addAll(questionTracker);
        vboxQuestions.add(questionWithChoice);
        questionsBox.getChildren().add(questionWithChoice);
        newChoice(questionWithChoice,group);
        newChoice(questionWithChoice,group);
        count++;
    }

    private void newChoice(VBox questionWithChoice,ToggleGroup group){
        HBox choiceTracker = new HBox();
        TextField choice = new TextField();
        choice.setMaxWidth(80);
        Button addNew = new Button("+");
        RadioButton answer = new RadioButton();
        answer.setToggleGroup(group);
        addNew.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0){
                addNew.setVisible(false);
                newChoice(questionWithChoice,group);
            }
        } );
        Button deleteOld = new Button("-");
        deleteOld.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                if(questionWithChoice.getChildren().size() != 3){//will prevent user from deleting the choice if it is the only one
                    questionWithChoice.getChildren().remove(choiceTracker);
                }
            }
        });
        choiceTracker.getChildren().addAll(choice,answer,addNew,deleteOld);
        questionWithChoice.getChildren().add(choiceTracker);
    }

    private boolean saveQuiz() throws SQLException{
        AnswerChecker ac = new AnswerChecker();
        SaveQuiz savedQuiz = new SaveQuiz(vboxQuestions);
        quiz.setQuestions(savedQuiz.retrieveNewQuestions());
        if(ac.allAnswersSelected(quiz)){
            quiz.setCreator(User.getUsername());
            DatabaseConnection.saveQuiz(quiz);
            return true;
        }else{
            Alerts alert = new Alerts(AlertMessages.CREATIONANSWERS);
            alert.answersAlert();
            return false;
        }
    }

    @FXML
    private void switchToCreateView() throws SQLException {
        if(saveQuiz()){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }

    @FXML
    private void cancelCreating(){
        Alerts alert = new Alerts(AlertMessages.CANCELQUIZ);
        Optional<ButtonType> result = alert.cancelAlert();
        if (result.get() == ButtonType.OK){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }
}
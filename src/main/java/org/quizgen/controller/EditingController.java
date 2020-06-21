package org.quizgen.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EditingController {
    private int questionNum = 1;
    ArrayList<Question> questions = new ArrayList<>();
    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox overall;

    @FXML
    public void initialize(){
        quiz = QuizSettingsController.getQuiz();
        quizName.setText(quiz.getName());
        try{
            quiz = DatabaseConnection.retrieveQuestions(quiz);
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        questions = quiz.getQuestions();
        for(Question question: questions){
            VBox questionWithChoice = new VBox(question.getChoices().size()+1);
            overall.getChildren().add(createVbox(question, questionWithChoice));//will create the layout for each question and choice
        }
        HBox buttons = new HBox();
        Button saveButton = new Button();
        saveButton.setText("Done");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try{
                    DatabaseConnection.updateQuiz(quiz);
                    SceneLoader.switchScene(Views.DISPLAYQUIZZES);
                }catch(IOException | SQLException e){
                    e.printStackTrace();
                }
            }
        } );
        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try{
                    cancelEditing();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        } );
        buttons.getChildren().addAll(saveButton,cancelButton);
        overall.getChildren().add(buttons);
    }

    private VBox createVbox(Question question, VBox questionWithChoice){
        HBox questionLayout = new HBox();
        ArrayList<Choice> choices = question.getChoices();
        Label questionTracker = new Label(questionNum + ". ");
        TextField questionName = new TextField (question.getName());
        questionLayout.getChildren().addAll(questionTracker,questionName);
        questionWithChoice.getChildren().add(questionLayout);
        ToggleGroup group = new ToggleGroup();
        for(int i = 1; i < choices.size() + 1; i++){
            HBox choiceLayout = new HBox();
            Label choiceTracker = new Label(i + ". ");
            RadioButton choice = new RadioButton(choices.get(i-1).getName());
            //choice.setId(String.valueOf(question.getQuestionId()));
            choice.setToggleGroup(group);
            if(i == question.getAnswer()){//will toggle the answer of the question
                choice.setSelected(true);
            }
            choiceLayout.getChildren().addAll(choiceTracker, choice);
            questionWithChoice.getChildren().add(choiceLayout);
        }
        questionNum++;

        return questionWithChoice;
    }

    private void cancelEditing() throws IOException{
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Your quiz will not be not be saved if you cancel.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneLoader.switchScene(Views.DISPLAYQUIZZES);
        }
    }
}
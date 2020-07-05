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
import org.quizgen.utils.scene.SceneLoader;
import org.quizgen.utils.quizCreation.SaveQuiz;
import org.quizgen.utils.scene.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EditingController {
    private int questionNum = 1;
    private ArrayList<Question> questions = new ArrayList<>();
    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox questionsBox;
    private ArrayList<VBox> vboxQuestions = new ArrayList<>();
    private int count = 1;
    private ArrayList <Question> deletedQuestions = new ArrayList<>();
    private ArrayList <Choice> deletedChoices = new ArrayList<>();

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
            VBox createdVbox = createQuestionVbox(question, questionWithChoice);
            vboxQuestions.add(createdVbox);
            questionsBox.getChildren().add(createdVbox);//will create the layout for each question and choice
        }
    }

    private VBox createQuestionVbox(Question question, VBox questionWithChoice){
        HBox questionLayout = new HBox();
        ArrayList<Choice> choices = question.getChoices();
        Label questionTracker = new Label(questionNum + ". ");
        TextField questionName = new TextField (question.getName());
        questionName.setId(String.valueOf(question.getQuestionId()));
        Button addNewQuestion = new Button("+");
        addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {//Will add a new question with choice

            @Override
            public void handle(ActionEvent arg0){
                addNewQuestion.setVisible(false);
                newQuestion();
            }
        } );
        Button deleteOldQuestion = new Button("-");
        deleteOldQuestion.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                if(questionsBox.getChildren().size() != 2){//will prevent user from deleting the question if it is the only one
                    vboxQuestions.remove(questionWithChoice);
                    questionsBox.getChildren().remove(questionWithChoice);
                    deletedQuestions.add(question);
                }
            }
        });
        questionLayout.getChildren().addAll(questionTracker,questionName,addNewQuestion,deleteOldQuestion);
        questionWithChoice.getChildren().add(questionLayout);
        ToggleGroup group = new ToggleGroup();
        for(int i = 1; i < choices.size() + 1; i++){
            Choice choiceObject = choices.get(i-1);
            HBox choiceLayout = new HBox();
            TextField choice = new TextField();
            choice.setMaxWidth(80);
            choice.setText(choiceObject.getName());
            choice.setId(String.valueOf(choiceObject.getId()));
            Label choiceTracker = new Label(i + ". ");
            RadioButton choiceOption = new RadioButton();
            //choice.setId(String.valueOf(question.getQuestionId()));
            choiceOption.setToggleGroup(group);
            if(i == question.getAnswer()){//will toggle the answer of the question
                choiceOption.setSelected(true);
            }
            Button addNewChoice = new Button("+");
            addNewChoice.setOnAction(new EventHandler<ActionEvent>() {//Will add a new question with choice

                @Override
                public void handle(ActionEvent arg0){
                    addNewChoice.setVisible(false);
                    newChoice(questionWithChoice,group);
                }
            } );
            Button deleteOldChoice = new Button("-");
            deleteOldChoice.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent actionEvent){
                    if(questionWithChoice.getChildren().size() != 3){//will prevent user from deleting the choice if it is the only one
                        questionWithChoice.getChildren().remove(choiceLayout);
                        deletedChoices.add(choiceObject);
                    }
                }
            });
            choiceLayout.getChildren().addAll(choiceTracker,choiceOption, choice,addNewChoice,deleteOldChoice);
            questionWithChoice.getChildren().add(choiceLayout);
        }
        questionNum++;

        return questionWithChoice;
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

    private void  updateQuiz() throws SQLException{
        quiz.setQuestions(SaveQuiz.retrieveEditedQuestions(vboxQuestions));
        if(deletedQuestions.size() != 0){
            DatabaseConnection.deleteQuestions(deletedQuestions);
        }
        if(deletedChoices.size() != 0){
            DatabaseConnection.deleteChoices(deletedChoices);
        }
        DatabaseConnection.updateQuiz(quiz);
    }

    @FXML
    private void switchToCreateView() throws SQLException, IOException{
        updateQuiz();
        SceneLoader.setRoot(Views.DISPLAYQUIZZES);
    }

    @FXML
    private void cancelEditing() throws IOException{
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Your quiz will not be not be saved if you cancel.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneLoader.setRoot(Views.DISPLAYQUIZZES);
        }
    }
}
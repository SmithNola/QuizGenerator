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
import org.quizgen.model.Choice;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

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
    private Quiz holderQuiz = new Quiz();

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
        holderQuiz.setQuizId(quiz.getQuizId());
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
            if(choiceObject.getAnswer() == true){//will toggle the answer of the question
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
        Label choiceNum = new Label(count + ". ");
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
        choiceTracker.getChildren().addAll(choiceNum, answer, choice,addNew,deleteOld);
        questionWithChoice.getChildren().add(choiceTracker);
    }

    private boolean updateQuiz() throws SQLException{
        SaveQuiz savedQuiz = new SaveQuiz(vboxQuestions);
        quiz.setQuestions(savedQuiz.retrieveEditedQuestions());
        if(AnswerChecker.allAnswersSelected(quiz)){
            updateDeletedQuestions();
            updateDeletedChoices();
            updateAddedQuestions(savedQuiz);
            updateAddedChoices(savedQuiz);
            DatabaseConnection.updateQuiz(quiz);
            return true;
        }else{
            Alerts alert = new Alerts(AlertMessages.CREATIONANSWERS);
            alert.answersAlert();
            return false;
        }
    }

    private void updateAddedChoices(SaveQuiz savedQuiz) throws SQLException{
        if(savedQuiz.getAddedChoices().size() != 0){
            for(Question question : savedQuiz.getAddedChoices()){
                DatabaseConnection.saveChoices(question, question.getQuestionId());
            }
        }
    }

    private void updateAddedQuestions(SaveQuiz savedQuiz) throws SQLException{
        if(savedQuiz.getAddedQuestions().size() != 0){
            holderQuiz.setQuestions(savedQuiz.getAddedQuestions());
            DatabaseConnection.saveQuestions(holderQuiz, holderQuiz.getQuizId());
        }
    }

    private void updateDeletedChoices() throws SQLException{
        if(deletedChoices.size() != 0){
            DatabaseConnection.deleteChoices(deletedChoices);
        }
    }

    private void updateDeletedQuestions() throws SQLException{
        if(deletedQuestions.size() != 0){
            DatabaseConnection.deleteQuestions(deletedQuestions);
        }
    }

    @FXML
    private void switchToCreateView() throws SQLException {
        if(updateQuiz()){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }

    @FXML
    private void cancelEditing() throws IOException{
        Alerts alert = new Alerts(AlertMessages.CANCELQUIZ);
        Optional<ButtonType> result = alert.cancelAlert();
        if (result.get() == ButtonType.OK){
            SceneHandler.setRoot(Views.DISPLAYQUIZZES);
        }
    }
}
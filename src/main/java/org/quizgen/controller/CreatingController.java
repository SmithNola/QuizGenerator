package org.quizgen.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreatingController {

    private Quiz quiz;
    @FXML
    private Label quizName;
    @FXML
    private VBox overall;
    private int count = 1;
    private ArrayList<VBox> questions = new ArrayList<>();

    public void initialize(){
        quiz=CreateViewController.getClickedQuiz();
        quizName.setText(quiz.getName());
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
                questions.remove(questionWithChoice);
                overall.getChildren().remove(questionWithChoice);
            }
        });
        questionTracker.getChildren().addAll(questionNum,question,addNew,deleteOld);//creates number + question + button
        questionWithChoice.getChildren().addAll(questionTracker);
        questions.add(questionWithChoice);
        overall.getChildren().add(questionWithChoice);
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
                questionWithChoice.getChildren().remove(choiceTracker);
            }
        });
        choiceTracker.getChildren().addAll(choice,answer,addNew,deleteOld);
        questionWithChoice.getChildren().add(choiceTracker);
    }

    private ArrayList<Question> retrieveQuestions(){
        ArrayList<Question> allQuestions = new ArrayList<>();
        for(int i = 0; i <questions.size(); i++){
            Question savedQuestion = new Question();
            ArrayList<String> choices = new ArrayList<>();
            VBox questionWithChoice = questions.get(i);//gets question with choice
            ObservableList<Node> eachRow = questionWithChoice.getChildren();//saves each HBox
            for(int j = 0; j<eachRow.size();j++){//will go through each Hbox
                HBox row = (HBox) eachRow.get(j);//gets first HBox
                ObservableList<Node> eachElement = row.getChildren();//saves each elements
                if(j == 0){
                    TextField question = (TextField) eachElement.get(1);//saves text field
                    savedQuestion.setName(question.getText());
                }else{
                    TextField choice = (TextField) eachElement.get(0);//saves text field
                    RadioButton answer = (RadioButton) eachElement.get(1);
                    if(answer.isSelected() == true){
                        savedQuestion.setAnswer(j);
                    }
                    choices.add(choice.getText());
                }
            }
            savedQuestion.setChoices(choices);
            allQuestions.add(savedQuestion);
        }
        return allQuestions;
    }

    private void saveQuiz() throws SQLException{
        quiz.setQuestions(retrieveQuestions());
        quiz.setCreator(LoginController.getUsername());

        DatabaseConnection.saveQuiz(quiz);
    }

    @FXML
    private void switchToCreateView() throws IOException, SQLException{
        saveQuiz();
        App.setRoot("createView");
    }
}
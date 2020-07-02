package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Quiz;
import org.quizgen.utils.SceneLoader;
import org.quizgen.utils.viewQuizzes.DisplayQuiz;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class QuizSettingsController {

    private static Quiz quiz = new Quiz();
    @FXML
    private TextField quizName;
    @FXML
    private TextField quizGenre;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private HBox buttons;
    @FXML
    private Button create;
    @FXML
    private Button edit;
    @FXML
    private Label errorMessage;
    private ToggleGroup group = new ToggleGroup();

    public void initialize(){
        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        //Will check if a quiz was clicked or create button was clicked
        if(DisplayQuizzesController.getButtonPressed().equals("Create")){
            buttons.getChildren().remove(edit);
        }else{
            buttons.getChildren().remove(create);
            quiz = DisplayQuiz.getClickedQuiz();
            quizName.setText(quiz.getName());
            quizGenre.setText(quiz.getGenre());
            if(quiz.getOrdered() == 0){
                yes.setSelected(true);
            }else{
                no.setSelected(true);
            }
        }
    }
    //will make sure all fields are filled out
    public boolean nonEmptyFields(){
        if(quizName.getText().isBlank() || quizGenre.getText().isBlank() || (!yes.isSelected() && !no.isSelected())){
            errorMessage.setText("All Fields must be filled out");
            return false;
        }else{
            return true;
        }
    }

    @FXML
    private void switchToEditing() throws IOException{
        if(nonEmptyFields()){
            saveProperties();
            SceneLoader.switchScene(Views.EDITING);
        }
    }
    @FXML
    private void switchToCreating() throws IOException{
        if(nonEmptyFields()){
            saveProperties();
            SceneLoader.switchScene(Views.CREATING);
        }
    }
    @FXML
    private void deleteQuiz() throws IOException, SQLException{
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("You are attempted to delete " + quiz.getName() + " quiz. This will be permanent.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            quiz = DatabaseConnection.retrieveQuestions(quiz);
            DatabaseConnection.deleteEntireQuiz(quiz);
            SceneLoader.switchScene(Views.DISPLAYQUIZZES);
        }
    }

    private void saveProperties(){
        quiz.setName(quizName.getText());
        quiz.setGenre(quizGenre.getText());
        if(yes.isSelected() == true){
            quiz.setOrdered(1);
        }
        else{
            quiz.setOrdered(0);
        }
    }

    public static Quiz getQuiz(){
        return quiz;
    }

    @FXML
    private void cancelCreating() throws IOException{
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Your quiz will not be not be saved if you cancel.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneLoader.switchScene(Views.DISPLAYQUIZZES);
        }
    }
}

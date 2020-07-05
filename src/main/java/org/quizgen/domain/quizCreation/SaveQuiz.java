package org.quizgen.domain.quizCreation;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;

import java.util.ArrayList;

public class SaveQuiz{
    //retrieve's created questions
    public static ArrayList<Question> retrieveNewQuestions(ArrayList<VBox> vboxQuestions){
        ArrayList<Question> allQuestions = new ArrayList<>();
        for(int i = 0; i <vboxQuestions.size(); i++){//will cycle through each vbox
            Question savedQuestion = new Question();
            ArrayList<Choice> choices = new ArrayList<>();
            VBox questionWithChoice = vboxQuestions.get(i);//gets question with choice
            ObservableList<Node> eachRow = questionWithChoice.getChildren();//saves each HBox
            for(int j = 0; j < eachRow.size(); j++){//will go through each Hbox
                HBox row = (HBox) eachRow.get(j);
                ObservableList<Node> eachElement = row.getChildren();//saves each element
                if(j == 0){
                    TextField question = (TextField) eachElement.get(1);//question text field
                    savedQuestion.setName(question.getText());
                }else{
                    TextField choice = (TextField) eachElement.get(0);//choice text field
                    RadioButton answer = (RadioButton) eachElement.get(1);
                    if(answer.isSelected() == true){
                        savedQuestion.setAnswer(j);
                    }
                    Choice choiceObject = new Choice();
                    choiceObject.setName(choice.getText());
                    choices.add(choiceObject);
                }
            }
            savedQuestion.setChoices(choices);
            allQuestions.add(savedQuestion);
        }
        return allQuestions;
    }

    //retrieves edited questions
    public static ArrayList<Question> retrieveEditedQuestions(ArrayList<VBox> vboxQuestions){
        ArrayList<Question> allQuestions = new ArrayList<>();
        for(int i = 0; i <vboxQuestions.size(); i++){//will cycle through each vbox
            Question savedQuestion = new Question();
            ArrayList<Choice> choices = new ArrayList<>();
            VBox questionWithChoice = vboxQuestions.get(i);//gets question with choice
            ObservableList<Node> eachRow = questionWithChoice.getChildren();//saves each HBox
            for(int j = 0; j < eachRow.size(); j++){//will go through each Hbox
                HBox row = (HBox) eachRow.get(j);
                ObservableList<Node> eachElement = row.getChildren();//saves each element
                if(j == 0){
                    TextField question = (TextField) eachElement.get(1);//question text field
                    savedQuestion.setQuestionId(Integer.parseInt(question.getId()));
                    savedQuestion.setName(question.getText());
                }else{
                    TextField choice = (TextField) eachElement.get(2);//choice text field
                    RadioButton answer = (RadioButton) eachElement.get(1);
                    if(answer.isSelected() == true){
                        savedQuestion.setAnswer(j);
                    }
                    Choice choiceObject = new Choice();
                    choiceObject.setId(Integer.parseInt(choice.getId()));
                    choiceObject.setName(choice.getText());
                    choices.add(choiceObject);
                }
            }
            savedQuestion.setChoices(choices);
            allQuestions.add(savedQuestion);
        }
        return allQuestions;
    }
}

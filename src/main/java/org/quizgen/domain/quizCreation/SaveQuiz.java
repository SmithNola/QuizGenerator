package org.quizgen.domain.quizCreation;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.quizgen.controller.EditingController;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;

import java.util.ArrayList;

public class SaveQuiz{
    private static ArrayList <Question> addedQuestions = new ArrayList<>();
    private static ArrayList <Question> addedChoices = new ArrayList<>();
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
            Question savedQuestion = new Question(); //save questions and choices already in quiz
            Question nullQuestion = new Question(); // will save save new choices added to
            Question addedQuestion = new Question();//will hold new question with choices
            ArrayList<Choice> choices = new ArrayList<>();
            ArrayList<Choice> newChoices = new ArrayList<>();
            VBox questionWithChoice = vboxQuestions.get(i);//gets question with choice
            ObservableList<Node> eachRow = questionWithChoice.getChildren();//saves each HBox
            boolean newQuestion = false;
            boolean newChoice = false;
            for(int j = 0; j < eachRow.size(); j++){//will go through each Hbox
                HBox row = (HBox) eachRow.get(j);
                ObservableList<Node> eachElement = row.getChildren();//saves each element
                if(j == 0){//for questions
                    TextField question = (TextField) eachElement.get(1);//question text field
                    if(question.getId() == null){//knows if question is new
                        System.out.println("NewQuestion");
                        newQuestion = true;
                        addedQuestion.setName(question.getText());
                    }else{//for old questions and new choices
                        nullQuestion.setQuestionId(Integer.parseInt(question.getId()));
                        savedQuestion.setQuestionId(Integer.parseInt(question.getId()));
                        savedQuestion.setName(question.getText());
                    }
                }else{//for choices
                    TextField choice = (TextField) eachElement.get(2);//choice text field
                    RadioButton answer = (RadioButton) eachElement.get(1);
                    if(answer.isSelected() == true){
                        savedQuestion.setAnswer(j);
                    }
                    Choice choiceObject = new Choice();
                    choiceObject.setName(choice.getText());
                    if(choice.getId() == null && newQuestion == false){
                        newChoice = true;
                        newChoices.add(choiceObject);
                    }else{
                        if(choice.getId() != null){
                            choiceObject.setId(Integer.parseInt(choice.getId()));
                        }
                        choices.add(choiceObject);
                    }
                }
            }
            if(newQuestion == true){
                System.out.println("NewQuestion");
                addedQuestion.setChoices(choices);
                addedQuestions.add(addedQuestion);
            }else if(newChoice == true){
                System.out.println("NewChoice");
                nullQuestion.setChoices(choices);
                addedChoices.add(nullQuestion);
            }else{
                savedQuestion.setChoices(choices);
                allQuestions.add(savedQuestion);
            }
        }
        return allQuestions;
    }

    public static ArrayList<Question> getAddedChoices(){
        return addedChoices;
    }

    public static ArrayList<Question> getAddedQuestions(){
        return addedQuestions;
    }
}

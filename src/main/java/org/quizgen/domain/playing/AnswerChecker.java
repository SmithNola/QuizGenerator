package org.quizgen.domain.playing;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.quizgen.model.Choice;
import org.quizgen.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class AnswerChecker{

    private static int score;

    public static int calculateScore(ArrayList<Question> questions, HashMap<Integer, String> chosenAnswers){
        double correctAnswers = 0;
        String answer = "";

        for(Question question: questions){
            for(Choice choice: question.getChoices()){
                if(choice.getAnswer()){
                    answer = choice.getName();
                }
            }
            if(chosenAnswers.get(question.getQuestionId()).equals(answer)){
                correctAnswers++;
            }
        }
        score = (int) ((correctAnswers / questions.size())*100);
        return score;
    }
    //Will display which answers are wrong and right
    public static VBox showRightWrong(Question question, String chosenAnswer, int questionNumber){
        VBox questionWithAnswers = new VBox();
        ArrayList<Choice> choices = question.getChoices();
        Label questionName = new Label(questionNumber + ": " + question.getName());
        Label userChoice = new Label("You Choice: " + chosenAnswer);
        String correctAnswer = retrieveCorrectAnswer(choices);
        Label correctAnswerLabel = new Label("Correct Answer: " + correctAnswer);
        if(chosenAnswer.equals(correctAnswer)){//color to indicate whether right or wrong
            userChoice.setTextFill(Color.GREEN);
        }else{
            userChoice.setTextFill(Color.DARKRED);
        }
        questionWithAnswers.getChildren().addAll(questionName,userChoice,correctAnswerLabel);
        return questionWithAnswers;
    }

    //will find the correct answer among the choices
    private static String retrieveCorrectAnswer(ArrayList<Choice> choices){
        String correctAnswer = "";
        for(Choice choice:choices){
            if(choice.getAnswer()){
                correctAnswer = choice.getName();
            }
        }
        return correctAnswer;
    }

    //checks if user has chosen an answer for each question
    public static boolean allAnswered(ArrayList<Question> questions, HashMap<Integer, String> chosenAnswers){
        for(Question question : questions){
            if(!chosenAnswers.containsKey(question.getQuestionId())){
                return false;
            }
        }
        return true;
    }

    public static int getScore(){
        return score;
    }
}

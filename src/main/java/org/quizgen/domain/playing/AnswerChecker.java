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

    public static int calculateScore(ArrayList<Question> questions, HashMap<String, Integer> chosenAnswers){
        double correctAnswers = 0;
        for(int i = 0; i < questions.size(); i++){
            if(chosenAnswers.get(String.valueOf(questions.get(i).getQuestionId())).equals(questions.get(i).getAnswer())){
                correctAnswers++;
            }
        }
        score = (int) ((correctAnswers / questions.size())*100);
        return score;
    }
    //Will display which answers are wrong and right
    public static VBox showRightWrong(Question question, int chosenAnswer, int questionNumber){
        VBox questionWithAnswers = new VBox();
        ArrayList<Choice> choices = question.getChoices();
        Label questionName = new Label(questionNumber + ": " + question.getName());
        String CA = choices.get(chosenAnswer - 1).getName();
        Label userChoice = new Label("You Choice: " + CA);
        String answer = question.getChoices().get(question.getAnswer()-1).getName();
        Label correctAnswer = new Label("Correct Answer: " + answer);
        if(CA.equals(answer)){//color to indicate whether right or wrong
            userChoice.setTextFill(Color.GREEN);
        }else{
            userChoice.setTextFill(Color.DARKRED);
        }
        questionWithAnswers.getChildren().addAll(questionName,userChoice,correctAnswer);
        return questionWithAnswers;
    }
    //checks if user has chosen an answer for each question
    public static boolean allAnswered(ArrayList<Question> questions, HashMap<String, Integer> chosenAnswers){
        for(Question question : questions){
            if(!chosenAnswers.containsKey(String.valueOf(question.getQuestionId()))){
                return false;
            }
        }
        return true;
    }

    public static int getScore(){
        return score;
    }
}

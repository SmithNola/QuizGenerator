package org.quizgen.console;

import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Playing {

    public static void play(Quiz quiz) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        quiz = DatabaseConnection.retrieveQuestions(quiz);
        ArrayList <Question> questions = quiz.getQuestions();
        int [] answers = new int[quiz.getQuestions().size()];
        for(int i = 0; i < quiz.getQuestions().size(); i++){//asks each question and saves answer
            //displayQuestion(quiz.getQuestions().get(i));
            System.out.print("Answer: ");
            answers[i] = keyboard.nextInt();
        }
        int score = calculateScore(answers, questions);
        System.out.println("Your Score is: " + score);
    }

    /*private static void displayQuestion(Question question){
        System.out.println(question.getName());
        for(String choice: question.getChoices()){
            System.out.println(choice);
        }
    }*/

    private static int calculateScore(int [] answers, ArrayList<Question> questions){
        double correctAnswers = 0;
        for(int i = 0; i < questions.size(); i++){
            if (answers[i] == questions.get(i).getAnswer()){
                correctAnswers++;
            }
        }
        return (int) ((correctAnswers / questions.size())*100);
    }
}
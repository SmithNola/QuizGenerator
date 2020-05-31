package org.example.help;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateView {

    private static void howTo(){
        System.out.println("You will be prompted for the question. Then press enter after you finish with the question.\n"
                + "Then you will be prompted for the answers. Press enter after each. When done with choices hit enter after entering the last answer.\n"
                + "Then you will be prompted for the position of the answer. For example, enter 3 if it is the choice.\n"
                + "This process will be continuous until you are finished with the quiz.\n"
                + "When finished with the entire quiz type finished when prompted for the next question.");
    }

    //will display all the quizzes the user has made
    private static void displayQuizzes(String username) throws SQLException {
        System.out.println("These are the Quizzes You have created.\n");
        ArrayList<Quiz> quizzes = DatabaseConnection.retrieveUserQuiz(username);//user's quizzes
        for (Quiz quiz : quizzes) {
            System.out.println(quiz);
        }
        System.out.println("If you would like to edit a quiz type their number.\n" +
                "If you would like begin creating type create.");
    }

    public static void beginCreate(String username) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        String choice;
        displayQuizzes(username);
        choice = keyboard.nextLine();
        if(choice.equalsIgnoreCase("create")) {
            howTo();
            Creating.create(username);
        }
    }
}
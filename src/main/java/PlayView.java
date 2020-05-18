import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayView {

    public static ArrayList<Quiz> displayQuizzes() throws SQLException {
        ArrayList<Quiz> quizzes;
        System.out.println("These are the available quizzes.");
        quizzes = DatabaseConnection.retrieveAllQuizzes();
        for(Quiz quiz: quizzes){
            System.out.println(quiz.toString());
        }

        System.out.println("If you would like to play a quiz type their number.");
        return quizzes;
    }

    public static void displayQuizInfo(Quiz quiz){
        System.out.println("Name:" + quiz.getName());
        System.out.println("Genre:" + quiz.getGenre());
        System.out.println("Creator:" + quiz.getCreator());
        System.out.println("Created:" + quiz.getCreationTime());
        System.out.println("Number of Questions:" + quiz.getNumberOfQuestions());
    }

    public static void beginPlay() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        String play;
        ArrayList<Quiz> quizzes;
        do {
            quizzes = displayQuizzes();
            choice = keyboard.nextInt();
            displayQuizInfo(quizzes.get(choice - 1));
            System.out.print("Play? Yes or No ");
            keyboard.nextLine();
            play = keyboard.nextLine();
        }while(play.equalsIgnoreCase("No"));
    }
}

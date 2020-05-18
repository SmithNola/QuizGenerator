import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayView {

    public static ArrayList<Integer> displayQuizzes() throws SQLException {
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        ArrayList <Integer> quizIds = new ArrayList<Integer>();
        System.out.println("These are the available quizzes.");
        quizzes = DatabaseConnection.retrieveAllQuizzes();
        for(Quiz quiz: quizzes){
            System.out.println(quiz.toString());
            quizIds.add(quiz.getQuizId());
        }

        System.out.println("If you would like to play a quiz type their number.");
        return quizIds;
    }

    public static void displayQuizInfo(){

    }

    public void beginPlay() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        Quiz quiz = new Quiz();
        ArrayList<Integer> quizId = new ArrayList<Integer>();
        quizId = displayQuizzes();
        choice = keyboard.nextInt();
        //quiz = DatabaseConnection.specificQuiz();
        //Playing.play(quiz);
    }
}

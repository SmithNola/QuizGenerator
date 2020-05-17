import java.sql.SQLException;
import java.util.ArrayList;

public class PlayView {

    public static void displayQuizzes() throws SQLException {
        ArrayList<String> quizzes = new ArrayList<String>();
        System.out.println("These are the available quizzes.");
        quizzes = DatabaseConnection.retrieveAllQuizzes();
        for(String quiz: quizzes){
            System.out.println(quiz);
        }
        System.out.println("If you would like to play a quiz type their number.");
    }

    private void displayResults() {

    }

    public Boolean beginPlay(){
        return false;
    }

}

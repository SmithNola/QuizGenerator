import java.sql.SQLException;

public class Playing {

    public static void play(Quiz quiz) throws SQLException {
        quiz = DatabaseConnection.retrieveQuestions(quiz);
        for(Question question: quiz.getQuestions()){
            displayQuestion(question);
        }
    }

    private static void displayQuestion(Question question){
        System.out.println(question.getName());
        for(String choice: question.getChoices()){
            System.out.println(choice);
        }
    }

    /*private int askQuestion(){
        int answer = 1;
        return answer;
    }*/

}

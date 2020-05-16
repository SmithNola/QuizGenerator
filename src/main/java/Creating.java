import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Creating {
    private static Scanner keyboard = new Scanner(System.in);
    private static Quiz quiz = new Quiz();
    private static int position = 1;

    public static void create(String username) throws SQLException {
        Boolean done;
        quizProperties();
        //asks user for question and choices
        do {
           done = createQuestion();
        }while(done == true);
        quiz.setCreator(username);
        DatabaseConnection.saveQuiz(quiz);
    }

    private static Boolean createQuestion(){
        Question question = new Question();
        Boolean done = askQuestion(question);
        if(done == false){
            return false;
        }
        else{
            question.setPosition(position);
            position++;
            askChoices(question);
            quiz.setQuestion(question);
            return true;
        }
    }

    private static Boolean askQuestion(Question question){
        String questionName;
        System.out.println("Enter the question.");
        questionName = keyboard.nextLine();
        if(!questionName.equalsIgnoreCase("finished")) {
            question.setName(questionName);
            return true;
        }
        else{
            return false;
        }
    }

    private static void askChoices(Question question){
        ArrayList <String> choices = new ArrayList<String>();
        String choice;
        int answer;
        System.out.println("Enter the choices.");
        do{
            choice = keyboard.nextLine();
            if(!choice.equalsIgnoreCase("done")) {
                choices.add(choice);
            }
        }while(!choice.equals("done"));
        System.out.println("Which one is the answer?");
        choice = keyboard.nextLine();
        answer = Integer.parseInt(choice);
        question.setAnswer(answer);
        question.setChoices(choices);
    }

    private static void quizProperties(){
        String orderedInput;
        String quizName;
        int ordered;
        String genre;
        System.out.println("What is the name of the quiz?");
        quizName = keyboard.nextLine();
        quiz.setName(quizName);
        do {
            System.out.println("Is the quiz ordered?");
            orderedInput = keyboard.nextLine();
            if(!orderedInput.equalsIgnoreCase("yes") && !orderedInput.equalsIgnoreCase("no")){
                System.out.println("Answer yes or no");
            }
        }while(!orderedInput.equalsIgnoreCase("yes") && !orderedInput.equalsIgnoreCase("no"));
        if(orderedInput.equalsIgnoreCase("yes")){
            ordered = 1;//represents true
        }
        else{
            ordered = 0;//represents false
        }
        quiz.setOrdered(ordered);
        System.out.println("What genre is this quiz?");
        genre = keyboard.nextLine();
        quiz.setGenre(genre);
    }
}

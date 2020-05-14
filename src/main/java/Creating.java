import java.util.HashMap;
import java.util.Scanner;

public class Creating {
    private static Scanner keyboard = new Scanner(System.in);
    private static Quiz quiz = new Quiz();
    private static Question question = new Question();

    public static void create(){

    }

    private static void askQuestion(){
        String questionName;
        System.out.println("Enter the question.");
        questionName = keyboard.nextLine();
        question.setName(questionName);
    }

    private static void askChoices(){
        HashMap<Integer, String> choices = new HashMap<>();
        String choice;
        int choiceOrder = 1;
        System.out.println("Enter the choices.");
        do{
            choice = keyboard.nextLine();
            choices.put(choiceOrder,choice);
            choiceOrder++;
        }while(!choice.equals("\n"));
    }

    private static void properties(){
        String orderedInput;
        String quizName;
        Boolean ordered;
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
        ordered = Boolean.parseBoolean(orderedInput);
        quiz.setOrdered(ordered);
    }

    private void edit(){

    }
}

import java.sql.SQLException;
import java.util.Scanner;

public class QuizApp {
    public static DatabaseConnection con = new DatabaseConnection();
    public static void main(String[] args) throws SQLException {
        con.connect();
        signUp();
        con.disconnect();
    }

    public static void signUp() throws SQLException {
        boolean taken;
        String username;
        System.out.println("Enter desired username.");
        Scanner keyboard = new Scanner(System.in);
        username = keyboard.nextLine();
        taken = con.checkUsername(username);
        while(taken == true){//checks if username is taken
            System.out.println("Username is taken. Enter a different one.");
            username = keyboard.nextLine();
            taken = con.checkUsername(username);
        };
        System.out.println("Username is available");
    }
}

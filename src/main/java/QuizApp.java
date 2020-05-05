import java.sql.SQLException;
import java.util.Scanner;

public class QuizApp {
    public static DatabaseConnection con = new DatabaseConnection();
    public static void main(String[] args) throws SQLException {
        boolean start = con.connect();
        if(start == true) {
            signUp();
            con.disconnect();
        }
    }

    public static void signUp() throws SQLException {
        boolean taken;
        String username;
        String password;
        String password2;
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

        //Will make sure user is entering password they are sure of
        System.out.println("Enter your password.");
        do {
            password = keyboard.nextLine();
            if (password.equals("")) {
                System.out.println("Password can not be empty");
            }
        }while(password.equals(""));
        System.out.println("Re-type the same password.");
        do{
            password2 = keyboard.nextLine();
            if(!password2.equals(password)){
                System.out.println("Passwords do not match. Re-type the same password.");
            }
        }while(!password2.equals(password));

        //inserts user into database
        con.addUser(username,password);
        System.out.println("Your account has been created");
    }
}
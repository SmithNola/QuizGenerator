import java.sql.SQLException;
import java.util.Scanner;

public class UserInteraction {
    private static Scanner keyboard = new Scanner(System.in);
    private static String choice;
    //decision to play or create
    public static String homePage(String username){

        System.out.println("Welcome " + username + " to the Quiz Generator.");
        do {
            System.out.println("Type 1 to begin playing or type 2 to begin creating");
            choice = keyboard.nextLine();
            if(!choice.equals("1") && !choice.equals("2")){
                System.out.println("That was not a choice");
            }
        }while(!choice.equals("1") && !choice.equals("2"));

        if(choice.equals("1")){
            return "play";
        }
        else{
            return "create";
        }
    }

    //The first page the user sees
    public static String startPage(DatabaseConnection con) throws SQLException {
        String username;
        System.out.println("Welcome to Quiz Generator");

        do {
            System.out.println("Type 1 to sign up or 2 to login");
            choice = keyboard.nextLine();
            if(!choice.equals("1") && !choice.equals("2")){
                System.out.println("That was not a choice");
            }
        }while(!choice.equals("1") && !choice.equals("2"));

        if(choice.equals("1")){
            username = signUp(con);
        }
        else{
            username = login(con);
        }
        return username;
    }

    //gets user sign up info
    private static String signUp(DatabaseConnection con) throws SQLException {
        boolean taken;
        String username;
        String password;
        String password2;
        System.out.println("Enter desired username.");

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
        return username;
    }
    //Gets user login info
    private static String login(DatabaseConnection con) throws SQLException {
        String username;
        String password;
        Boolean login;

        do{
            System.out.println("Enter username");
            username = keyboard.nextLine();
            System.out.println("Enter Password");
            password = keyboard.nextLine();
            login = con.checkLogin(username,password);
            if(login == false){
                System.out.println("Wrong username/password combination");
            }
        }while(login == false);
        System.out.println("You have been signed in");
        return username;
    }

    public static void createView(){
        choice = keyboard.nextLine();
        if(choice.equalsIgnoreCase("create")){
           Creating.properties();
        }
    }
}
package org.quizgen;

import org.quizgen.console.CreateView;
import org.quizgen.console.PlayView;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class QuizApp {
    private static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        String choice;
        boolean start = DatabaseConnection.isConnected();
        User currentUser;
        if(start) {
            currentUser = new User(startPage());//Will return the user's username
            choice = homePage(currentUser.getUsername());//user decides to play or create
            if(choice.equals("play")){
                    PlayView.beginPlay();
           }
            else{
                    CreateView.beginCreate(currentUser.getUsername());//Will take user to create page
            }
            DatabaseConnection.disconnect();
        }
    }

    //org.help.User decides to play or create
    public static String homePage(String username){
        String choice;
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

    //The first page the user sees, user decides to login or sign up
    public static String startPage() throws SQLException {
        String choice;
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
            username = signUp();
        }
        else{
            username = login();
        }
        return username;
    }

    //gets user sign up info
    private static String signUp() throws SQLException {
        boolean taken;
        String username;
        String password;
        String password2;
        System.out.println("Enter desired username.");

        username = keyboard.nextLine();
        taken = DatabaseConnection.checkUsername(username);
        while(taken){//checks if username is taken
            System.out.println("Username is taken. Enter a different one.");
            username = keyboard.nextLine();
            taken = DatabaseConnection.checkUsername(username);
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
        DatabaseConnection.addUser(username,password);
        System.out.println("Your account has been created");
        return username;
    }

    private static String login() throws SQLException {
        String username;
        String password;
        String loginName;

        do{
            System.out.println("Enter username");
            username = keyboard.nextLine();
            System.out.println("Enter Password");
            password = keyboard.nextLine();
            loginName = DatabaseConnection.checkLogin(username,password);
            if(loginName == null){
                System.out.println("Wrong username/password combination");
            }
        }while(loginName == null);
        System.out.println("You have been signed in");
        return username;
    }
}
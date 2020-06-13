package org.quizgen.utils.login;

import javafx.scene.control.TextField;
import org.quizgen.data.DatabaseConnection;

import java.sql.SQLException;

public class UsernameValidator {

    private String username;

    public UsernameValidator(String username){
        this.username = username;
    }

    public boolean usernameIsTaken(String username){
        boolean usernameTaken = false;
        try{
            usernameTaken = DatabaseConnection.checkUsername(username);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usernameTaken;
    }

    public String usernameIsTakenError(){
        return "Username already taken!";
    }
}

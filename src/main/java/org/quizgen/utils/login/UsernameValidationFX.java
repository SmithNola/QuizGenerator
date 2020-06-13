package org.quizgen.utils.login;

import javafx.scene.control.TextField;
import org.quizgen.data.DatabaseConnection;

import java.sql.SQLException;

public class UsernameValidationFX {
    private TextField usernameField;
    private String username;

    public UsernameValidationFX(TextField usernameField) {
        this.usernameField = usernameField;
        this.username = usernameField.getText();
    }

    public boolean usernameRegisterSuccessful(){
        return !usernameIsTaken();
    }

    private boolean usernameIsTaken(){
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

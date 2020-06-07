package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField rePassword;
    @FXML
    private Text errorMessage;

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        boolean taken;
        taken = DatabaseConnection.checkUsername(username.getText());
        if(username.getText().equals("")||password.getText().equals("")||rePassword.getText().equals("")){
           errorMessage.setText("All fields must be filled out all fields");
        }else {
            taken = DatabaseConnection.checkUsername(username.getText());
            if (taken) {
                errorMessage.setText("Username is taken. Enter a different one.");
            } else if (password.getText().equalsIgnoreCase("")) {
                errorMessage.setText("Password can not be empty");
            } else if ((!rePassword.getText().equals(password.getText()))) {
                errorMessage.setText("Passwords do not match.");
            } else {
                DatabaseConnection.addUser(username.getText(),password.getText());
                HomePageController.setUsername(String.valueOf(username));
                App.setRoot("homePage");
            }
        }
    }
}
package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text errorMessage;
    private static String loginName;

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        loginName = DatabaseConnection.checkLogin(username.getText(),password.getText());
        if(loginName == null){
            errorMessage.setText("Wrong username/password combination");
        }
        else{
            HomePageController.setUsername(loginName);
            App.setRoot("homePage");
        }
    }
}

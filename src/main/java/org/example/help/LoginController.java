package org.example.help;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text errorMessage;

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        String loginName;
        loginName = DatabaseConnection.checkLogin(username.getText(),password.getText());
        if(loginName == null){
            errorMessage.setText("Wrong username/password combination");
        }
        else{
            App.setRoot("homePage");
        }
    }
}

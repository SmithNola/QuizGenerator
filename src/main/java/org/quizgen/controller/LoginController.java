package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.quizgen.App;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    // REPLACE WITH ERROR POPUP
    //    @FXML
    //    private Text errorMessage;

    private static String loginName;

    @FXML
    private void switchToHomePage() throws IOException, SQLException {
        loginName = DatabaseConnection.checkLogin(username.getText(),password.getText());
        username.setStyle("-fx-text-fill: black");
        if(loginName == null){
            //errorMessage.setText("Wrong username/password combination");
            username.setStyle("-fx-text-fill: red");
            username.setText("ERROR! Wrong username/password");

            System.out.println("Wrong username/password combination"); // REPLACE WITH ERROR POPUP
        }
        else{
            SceneLoader.switchScene(Views.HOME);
        }
    }

    @FXML
    private void switchToStartPage() throws IOException{
        SceneLoader.switchScene(Views.START);
    }

    public static String getUsername(){
        return loginName;
    }
}

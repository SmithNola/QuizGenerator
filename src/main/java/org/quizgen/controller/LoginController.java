package org.quizgen.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public void initialize(){
        username.setStyle("-fx-text-fill: black");
    }

    @FXML
    private void loginIntoApp() throws IOException, SQLException {
        if(isLoginValid()){
            //errorMessage.setText("Wrong username/password combination"); // DELETE?
            username.setStyle("-fx-text-fill: red");
            username.setText("ERROR! Wrong username/password"); // REPLACE WITH ERROR POPUP
        }
        else{
            SceneLoader.switchScene(Views.HOME);
        }
    }

    private boolean isLoginValid() throws SQLException {
        return DatabaseConnection.checkLogin(username.getText(),password.getText()) == null;
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent ae) throws IOException, SQLException {
        if(ae.getCode() == KeyCode.ENTER){
            loginIntoApp();
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

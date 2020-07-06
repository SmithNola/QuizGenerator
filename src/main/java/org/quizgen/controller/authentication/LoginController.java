package org.quizgen.controller.authentication;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import org.quizgen.data.DatabaseConnection;
import org.quizgen.model.User;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.authentication.LoginAuth;
import org.quizgen.domain.scenehandling.Views;


public class LoginController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Hyperlink resetPassword;

    @FXML
    private void switchToStartPage(){
        SceneHandler.setRoot(Views.START);
    }

    @FXML
    private void switchToHomePage() {
        boolean loginIsValid = LoginAuth.checkLoginIsValid(username.getText(), password.getText());
        if(loginIsValid){
            User.setUsername(DatabaseConnection.getUsername(username.getText())); // Sets the current user of this app
            SceneHandler.setRoot(Views.HOME);
        }
        else{
            createLoginAlert();
        }
    }

    public void createLoginAlert(){
        Label label = new Label();
        label.setFont(new Font("Monospaced Bold", 16));
        label.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
        label.setTextFill(Color.WHITE);
        label.setText(LoginAuth.getLoginError());
        JFXAlert<Void> loginErrorAlert = new JFXAlert<>(SceneHandler.getStage());
        loginErrorAlert.setOverlayClose(true);
        loginErrorAlert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        loginErrorAlert.setContent(label);
        loginErrorAlert.initModality(Modality.NONE);
        loginErrorAlert.show();
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            switchToHomePage();
        }
    }
}

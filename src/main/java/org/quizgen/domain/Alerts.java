package org.quizgen.domain;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts{
    private AlertMessages message;

    public Alerts(AlertMessages newMessage){
        this.message = newMessage;
    }

    public Optional<ButtonType> cancelAlert(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message.toString());
        return alert.showAndWait();
    }

    public void answersAlert(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setContentText(message.toString());
        alert.show();
    }
}

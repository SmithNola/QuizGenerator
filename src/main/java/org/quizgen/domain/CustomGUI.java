package org.quizgen.domain;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.quizgen.domain.scenehandling.SceneHandler;

public class CustomGUI {

    public static void createAlert(String error, Stage stage){
        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: white; -fx-font-family: Monospaced; -fx-font-size: 16px;");
        errorMessage.setText(error);
        VBox background = new VBox();
        background.setStyle("-fx-background-color: darkred; -fx-padding: 5px;");
        background.getChildren().add(errorMessage);
        JFXAlert<Void> loginErrorAlert = new JFXAlert<>(SceneHandler.getStage());
        loginErrorAlert.setOverlayClose(true);
        loginErrorAlert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        loginErrorAlert.setContent(background);
        loginErrorAlert.initModality(Modality.NONE);
        loginErrorAlert.show();
    }
}

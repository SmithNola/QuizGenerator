package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.quizgen.domain.scenehandling.SceneHandler;
import org.quizgen.domain.scenehandling.Views;

import java.io.IOException;

public class HomePageController {
    private static String buttonPressed;
    @FXML
    private Button PlayViewButton;
    @FXML
    private Button CreateViewButton;

    @FXML
    private void switchToPlay() throws Exception{
        buttonPressed = PlayViewButton.getText();
        SceneHandler.setRoot(Views.DISPLAYQUIZZES);
    }

    @FXML
    private void switchToCreate() throws IOException {
        buttonPressed = CreateViewButton.getText();
        SceneHandler.setRoot(Views.DISPLAYQUIZZES);
    }

    public static String getButtonPressed(){
        return buttonPressed;
    }
}
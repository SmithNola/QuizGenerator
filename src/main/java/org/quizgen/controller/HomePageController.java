package org.quizgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

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
        SceneLoader.switchScene(Views.DISPLAYQUIZZES);
    }

    @FXML
    private void switchToCreate() throws IOException {
        buttonPressed = CreateViewButton.getText();
        SceneLoader.switchScene(Views.DISPLAYQUIZZES);
    }

    public static String getButtonPressed(){
        return buttonPressed;
    }
}
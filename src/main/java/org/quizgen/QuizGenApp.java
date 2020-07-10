package org.quizgen;

import javafx.application.Application;
import javafx.stage.Stage;
import org.quizgen.domain.scenehandling.SceneHandler;

import java.io.IOException;

public class QuizGenApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler.setStage(stage);
        SceneHandler.initScene();
    }

    public static void main(String[] args) {
        launch();
    }
}
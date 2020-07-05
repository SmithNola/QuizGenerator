package org.quizgen;

import javafx.application.Application;
import javafx.stage.Stage;
import org.quizgen.utils.scene.SceneLoader;

import java.io.IOException;

public class QuizGenApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneLoader.stage = stage;
        SceneLoader.initScene();
    }

    public static void main(String[] args) {
        launch();
    }
}
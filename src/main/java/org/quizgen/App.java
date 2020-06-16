package org.quizgen;

import javafx.application.Application;
import javafx.stage.Stage;
import org.quizgen.utils.SceneLoader;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneLoader.stage = stage;
        SceneLoader.setScene();

    }

    public static void main(String[] args) {
        launch();
    }
}
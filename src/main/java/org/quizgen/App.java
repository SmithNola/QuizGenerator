package org.quizgen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.quizgen.utils.SceneLoader;
import org.quizgen.view.Views;

import java.io.IOException;

import static org.quizgen.utils.SceneLoader.loadFXML;
import static org.quizgen.utils.SceneLoader.scene;

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
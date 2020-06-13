package org.quizgen.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.quizgen.App;
import org.quizgen.view.Views;

import java.io.IOException;
import java.net.URL;

public class SceneLoader {
    private SceneLoader(){}

    public static Scene scene;
    public static Stage stage;

    public static void switchScene(Views fxml) throws IOException {
        System.out.println(fxml.toString());
        scene.setRoot(loadFXML(fxml.toString()));
        loadScene();
    }

    public static void loadScene(){
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void setScene() throws IOException {
        scene = new Scene(loadFXML(Views.START.toString()));
        loadScene();
    }

    public static Parent loadFXML(String fxmlFileName) throws IOException {
        URL pathToFXML = fxmlPath(fxmlFileName);
        return new FXMLLoader(pathToFXML).load();
    }

    private static URL fxmlPath(String fxmlFileName){
        return App.class.getResource(fxmlFileName);
    }
}

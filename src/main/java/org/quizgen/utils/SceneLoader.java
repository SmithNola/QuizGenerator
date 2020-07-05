package org.quizgen.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.quizgen.Main;
import org.quizgen.view.Views;

import java.io.IOException;
import java.net.URL;

public class SceneLoader {

    public static Scene scene;
    public static Stage stage;

    // Initializes Scene; called when app starts
    public static void initScene() throws IOException {
        scene = new Scene(loadFXML(Views.START.toString()));
        setAndShowStage();
    }

    // Used for switching fxml views
    public static void setRoot(Views fxml) throws IOException {
        System.out.println(fxml.toString());
        scene.setRoot(loadFXML(fxml.toString()));
        setAndShowStage();
    }

    // Used for setting Stage
    // Configures the size of stage to the size defined in scene
    // Otherwise, app is displayed at the wrong size
    // TO-DO: implement the correct way to display scenes
    private static void setAndShowStage(){
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxmlFileName) throws IOException {
        URL pathToFXML = fxmlPath(fxmlFileName);
        return new FXMLLoader(pathToFXML).load();
    }

    private static URL fxmlPath(String fxmlFileName){
        return Main.class.getResource(fxmlFileName);
    }
}

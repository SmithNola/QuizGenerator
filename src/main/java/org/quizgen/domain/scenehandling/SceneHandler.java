package org.quizgen.domain.scenehandling;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.quizgen.Main;

import java.io.IOException;
import java.net.URL;

public class SceneHandler {

    private static Scene scene;
    private static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage appStage){
        stage = appStage;
    }

    // Initializes Scene; called when app starts
    public static void initScene(){
        scene = new Scene(loadFXML(Views.START.toString()));
        setAndShowStage();
    }

    // Used for switching fxml views
    public static void setRoot(Views fxml){
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

    private static Parent loadFXML(String fxmlFileName){
        URL pathToFXML = fxmlPath(fxmlFileName);
        try{
            return new FXMLLoader(pathToFXML).load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static URL fxmlPath(String fxmlFileName){
        return Main.class.getResource(fxmlFileName);
    }
}

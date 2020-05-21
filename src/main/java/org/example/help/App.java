package org.example.help;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage){
        try {

            Text title = new Text();
            title.setText("Welcome to Quiz Generator");
            title.setX(100);
            title.setY(100);
            Button loginButton = new Button("Login");
            Button signUpButton = new Button("Sign Up");

            VBox vbox = new VBox(title, loginButton, signUpButton);

            vbox.getStylesheets().add(App.class.getResource("stylesheet.css").toExternalForm());
            title.setId("title");
            vbox.setId("outside");
            //setting color to the scene
            Color color = Color.rgb(153, 153, 255);

            stage.setTitle("Quiz Generator");

            scene = new Scene(vbox,800, 500);
            scene.setFill(color);
            stage.setScene(scene);

            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
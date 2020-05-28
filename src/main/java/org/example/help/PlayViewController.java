package org.example.help;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PlayViewController {

    private static Scene scene;
    private static Stage stage = new Stage();
    private static int quizId;

    public static void start() throws Exception {
        VBox root = FXMLLoader.load(PlayViewController.class.getResource("playView.fxml"));
        VBox overall = new VBox();
        ArrayList<Quiz> quizzes;
        quizzes = DatabaseConnection.retrieveAllQuizzes();
        for(Quiz quiz: quizzes){
            HBox quizLayout = new HBox(4);
            overall.getChildren().add(createHbox(quiz, quizLayout));
        }
        root.getChildren().add(overall);
        scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }

    private static HBox createHbox(Quiz quiz, HBox quizLayout){
        Button quizButton = new Button();
        String quizProperties = quiz.getName() + "\t" + quiz.getGenre() + "\t" + quiz.getCreator() + "\t" + quiz.getCreationTime();
        quizButton.setText(quizProperties);
        quizButton.setId(String.valueOf(quiz.getQuizId()));
        quizButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                quizId = Integer.parseInt(quizButton.getId());
                QuizInfoController.start(quizId);
            }
        } );
        quizLayout.getChildren().addAll(quizButton);
        return quizLayout;
    }
}

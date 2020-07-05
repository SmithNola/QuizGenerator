package org.quizgen.utils.scene;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;

/*
    TO-DO:
        - add animations for scene transitions
 */

public class SceneTransition {
    private PauseTransition delayBeforeSceneLoad;
    private Views view;

    public SceneTransition(double seconds){
        this.delayBeforeSceneLoad = new PauseTransition(Duration.seconds(seconds));
        this.view = null;
    }

    public void startSceneSwitchDelay(Views view){
        this.view = view;
        delayBeforeSceneLoad.setOnFinished(this::sceneTransition);
        delayBeforeSceneLoad.play();
    }

    private void sceneTransition(ActionEvent e){
        try{
            SceneLoader.setRoot(view);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}

package org.quizgen.utils;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.quizgen.view.Views;

import java.io.IOException;


public class SceneTransition {
    private PauseTransition delayBeforeSceneLoad;
    private Views view;

    public SceneTransition(double seconds){
        this.delayBeforeSceneLoad = new PauseTransition(Duration.seconds(seconds));
        this.view = null;
    }

    public SceneTransition(){
        this(1.0);
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

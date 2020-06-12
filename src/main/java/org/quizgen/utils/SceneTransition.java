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

    public void beforeSceneTransitionDelay(Views view){
        this.view = view;
        delayBeforeSceneLoad.setOnFinished(this::afterSceneTransitionDelay);
        delayBeforeSceneLoad.play();
    }

    private void afterSceneTransitionDelay(ActionEvent e){
        try{
            SceneLoader.switchScene(view);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}

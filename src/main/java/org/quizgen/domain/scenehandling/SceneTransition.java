package org.quizgen.domain.scenehandling;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;

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

    public void delaySwitchTo(Views view){
        this.view = view;
        delayBeforeSceneLoad.setOnFinished(this::sceneTransition);
        delayBeforeSceneLoad.play();
    }

    private void sceneTransition(ActionEvent e){
        SceneHandler.setRoot(view);
    }
}

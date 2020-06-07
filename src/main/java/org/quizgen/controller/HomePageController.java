package org.quizgen.controller;

import javafx.fxml.FXML;
import org.quizgen.App;

import java.io.IOException;

public class HomePageController {
    private static String username;

    @FXML
    private void switchToPlay() throws Exception{
        App.setRoot("playView");
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("createView");
    }

    public static void setUsername(String newUsername){
       username = newUsername;
    }

    public static String getUsername(){
        return username;
    }
}

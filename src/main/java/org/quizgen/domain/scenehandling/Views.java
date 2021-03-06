package org.quizgen.domain.scenehandling;

public enum Views{
    START("startPage"),
    LOGIN("login"),
    HOME("homePage"),
    SIGNUP("signUp"),
    QUIZINFO("quizInfo"),
    CREATING("creating"),
    EDITING("editing"),
    PLAYING("playing"),
    QUIZSETTINGS("quizSettings"),
    SCORE("score"),
    DISPLAYQUIZZES("displayQuizzes"),
    RESET("resetPassword");

    private final String fxmlPath;

    Views(String fxml){
        String PREFIX = "/fxml/";
        String FILE_EXT = ".fxml";
        this.fxmlPath = PREFIX + fxml + FILE_EXT;
    }

    @Override
    public String toString(){
        return this.fxmlPath;
    }
}


package org.quizgen.view;

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
    DISPLAYQUIZZES("displayQuizzes");

    private String fxmlPath;

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


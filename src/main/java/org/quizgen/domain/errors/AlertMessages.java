package org.quizgen.domain.errors;

public enum AlertMessages{
    DELETEQUIZ("You are attempted to delete this quiz. This will be permanent."),
    CANCELQUIZ("Your quiz changes will not be not be saved if you cancel."),
    CREATIONANSWERS("Every question must have an answer"),
    PLAYINGANSWERS("Answer all questions"),
    CANCELPLAYING("Your answers will not be not be saved if you cancel.");

    private final String type;

    AlertMessages(String newType){
        this.type = newType;
    }

    @Override
    public String toString(){
        return this.type;
    }
}

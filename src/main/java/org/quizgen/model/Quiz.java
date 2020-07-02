package org.quizgen.model;

import java.util.ArrayList;

public class Quiz {
    private int quizId;
    private String name;
    private int ordered;
    private String creationTime;
    private String creator;
    private String genre;
    private ArrayList<Question> questions = new ArrayList <Question>();
    private int numberOfQuestions;

    public Quiz (int newQuizId, String newName, String newGenre, String newCreationTime, String newCreator, int newNumberOfQuestions){
        this.quizId = newQuizId;
        this.name = newName;
        this.genre = newGenre;
        this.creationTime = newCreationTime;
        this.creator = newCreator;
        this.numberOfQuestions = newNumberOfQuestions;
    }

    public Quiz (){
        this.quizId = 0;
        this.name = "";
        this.ordered = 0;
        this.genre = "";
    }

    public void setName(String newName) {

        this.name = newName;
    }

    public void setOrdered(int newOrdered) {
        this.ordered = newOrdered;
    }

    public void setCreator(String newCreator){
        this.creator = newCreator;
    }

    public void setQuestions(ArrayList<Question> newQuestions){ questions = newQuestions; }

    public void setGenre(String newGenre){
        this.genre = newGenre;
    }

    public String getName(){ return this.name; }

    public int getOrdered(){
        return this.ordered;
    }

    public String getCreationTime(){
        return this.creationTime;
    }

    public String getCreator() {
        return this.creator;
    }

    public String getGenre(){
        return this.genre;
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

    public int getNumberOfQuestions(){
        return this.numberOfQuestions;
    }

    public int getQuizId(){return this.quizId;}
}

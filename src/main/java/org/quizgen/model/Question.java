package org.quizgen.model;

import java.util.ArrayList;

public class Question {
    private int questionId;
    private String name;
    private ArrayList <Choice> choices;
    private int position;

    public Question(){
        questionId = 0;
        this.name = "";
        this.choices = new ArrayList <Choice>();
        this.position = 0;
    }

    public void setQuestionId(int newQuestionId){this.questionId = newQuestionId;}

    public void setName(String newName){
        this.name = newName;
    }

    public void setChoices(ArrayList <Choice> newChoices){
        choices = newChoices;
    }

    public String getName(){
        return this.name;
    }

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public ArrayList <Choice> getChoices(){
        return this.choices;
    }

    public int getPosition(){
        return this.position;
    }

    public int getQuestionId(){
        return this.questionId;
    }
}

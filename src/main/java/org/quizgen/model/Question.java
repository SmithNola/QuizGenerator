package org.quizgen.model;

import java.util.ArrayList;

public class Question {
    private int questionId;
    private String name;
    private int answer;
    private ArrayList <String> choices;
    private int position;

    public Question (int newQuestionId, String newName, int newAnswer, ArrayList<String> newChoices, int newPosition){
        this.questionId = newQuestionId;
        this.name = newName;
        this.answer = newAnswer;
        this.choices = newChoices;
        this.position = newPosition;
    }

    public Question(){
        questionId = 0;
        this.name = "";
        this.answer = 1;
        this.choices = new ArrayList <String>();
        this.position = 0;
    }

    public void setQuestionId(int newQuestionId){this.questionId = newQuestionId;}

    public void setName(String newName){
        this.name = newName;
    }

    public void setAnswer(int newAnswer) {
        this.answer = newAnswer;
    }

    public void setChoices(ArrayList <String> newChoices){
        choices = newChoices;
    }

    public String getName(){
        return this.name;
    }

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public int getAnswer(){
        return this.answer;
    }

    public ArrayList <String> getChoices(){
        return this.choices;
    }

    public int getPosition(){
        return this.position;
    }

    public String getQuestionId(){return String.valueOf(this.questionId);}
}

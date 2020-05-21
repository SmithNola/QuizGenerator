package org.example.help;

import java.util.ArrayList;

public class Question {
    private String name = "";
    private int answer = 1;
    private ArrayList <String> choices = new ArrayList <String>();
    private int position = 0;

    public Question (String newName, int newAnswer, ArrayList<String> newChoices, int newPosition){
        this.name = newName;
        this.answer = newAnswer;
        this.choices = newChoices;
        this.position = newPosition;
    }

    public Question(){
        this.name = "";
        this.answer = 1;
        this.choices = new ArrayList <String>();
        this.position = 0;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setAnswer(int newAnswer) {
        this.answer = newAnswer;
    }

    public void setChoices(ArrayList newChoices){
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
}

package org.quizgen.model;

public class Choice{

    private int id;
    private String name;
    private boolean answer = false;

    public void setAnswer(boolean newAnswer){
        answer = newAnswer;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setName(String newName){
        name = newName;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public boolean getAnswer(){
        return answer;
    }
}

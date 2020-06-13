package org.quizgen.model;

public class Choice{

    private int id;
    private String name;

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
}

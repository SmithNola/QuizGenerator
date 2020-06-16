package org.quizgen.model;

public class User {
    private static String username;

    public static void setUsername(String newUsername){
        username = newUsername;
    }

    public static  String getUsername(){
        return username;
    }
}

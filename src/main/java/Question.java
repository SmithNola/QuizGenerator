import java.util.ArrayList;

public class Question {
    private String name = "";
    private int answer = 1;
    private ArrayList <String> choices = new <String> ArrayList();

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

    public int getAnswer(){

        return this.answer;
    }

    public ArrayList getChoices(){

        return this.choices;
    }
}

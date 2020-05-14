public class Question {
    private String name = "";
    private int answer = 1;
    private String [] choices;

    public void setName(String newName){
        this.name = newName;
    }

    public void setAnswer(int newAnswer){
        this.answer = newAnswer;
    }

    public void setChoices(String [] newChoices){
        choices = newChoices;
    }

    public String getName(){
        return this.name;
    }

    public int getAnswer(){
        return this.answer;
    }

    public String [] getChoices(){
        return this.choices;
    }
}

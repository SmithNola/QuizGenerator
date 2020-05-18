import java.util.ArrayList;

public class Quiz {
    private int quizId;
    private String name;
    private int ordered;
    private String creationTime;
    private String creator;
    private String genre;
    private ArrayList<Question> questions;

    public Quiz (int newQuizId, String newName, String newGenre, String newCreationTime, String newCreator){
        this.quizId = newQuizId;
        this.name = newName;
        this.genre = newGenre;
        this.creationTime = newCreationTime;
        this.creator = newCreator;
    }

    public Quiz (){
        this.quizId = 0;
        this.name = "";
        this.ordered = 0;
        this.genre = "";
        this.questions = new <Question> ArrayList();
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

    public void setQuestion(Question newQuestions){
        questions.add(newQuestions);
    }

    public void setGenre(String newGenre){
        this.genre = newGenre;
    }

    public String getName(){
        return this.name;
    }

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

    public int getQuizId(){
        return this.quizId;
    }

    public String toString(){
        return "" + this.name + "\t" + this.genre + "\t" + this.creationTime + "\t" + this.creator;
    }
}

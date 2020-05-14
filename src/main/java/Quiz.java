import java.util.ArrayList;

public class Quiz {
    private String name = "";
    private int ordered = 0;
    //private Date creationTime;
    private String creator = "";
    private String genre = "";
    private ArrayList<Question> question = new <Question> ArrayList();

    public void setName(String newName) {
        this.name = newName;
    }

    public void setOrdered(int newOrdered) {
        this.ordered = newOrdered;
    }

    /*public void setDate(java.sql.Date newCreationTime){
        this.creationTime = newCreationTime;
    }*/

    public void setCreator(String newCreator){
        this.creator = newCreator;
    }

    public void setQuestion(Question newQuestion){
        question.add(newQuestion);
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

    /*public Date getCreationTime(){
        return this.creationTime;
    }*/

    public String getCreator() {
        return this.creator;
    }

    public String getGenre(){
        return this.genre;
    }
}

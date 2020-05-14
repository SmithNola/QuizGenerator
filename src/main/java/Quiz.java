import java.util.Date;

public class Quiz {
    private String name = "";
    private Boolean ordered = false;
    private Date creationTime;
    private String creator = "";
    private Question questions[];

    public void setName(String newName) {
        this.name = name;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public void setDate(Date newCreationTime){
        this.creationTime = newCreationTime;
    }

    public void setCreator(String newCreator){
        this.creator = newCreator;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getOrdered(){
        return this.ordered;
    }

    public Date getCreationTime(){
        return this.creationTime;
    }

    public String getCreator(){
        return this.creator;
    }
}

import java.sql.SQLException;

public class QuizApp {
    public static DatabaseConnection con = new DatabaseConnection();
    public static void main(String[] args) throws SQLException {
        boolean start = con.connect();
        if(start == true) {
            UserInteraction.startPage(con);
            con.disconnect();
        }
    }
}
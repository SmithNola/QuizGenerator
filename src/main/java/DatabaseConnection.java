import java.io.File;
import java.sql.*;

public class DatabaseConnection {
    private static Connection conn = null;

    public Boolean connect() throws SQLException {
        try {
            File dbfile = new File(".");
            String url = "jdbc:sqlite:" + dbfile.getAbsolutePath() + "\\src\\main\\d\\QuizGen.db";
            // create a connection to the database3
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to Database has been established.");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Will check if username already exits in database
    public static Boolean checkUsername(String username) throws SQLException {
        String query = "SELECT username FROM player WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1,username);
        ResultSet names = st.executeQuery();
        if(names.next()) {
            return true;
        }
        else{
            return false;
        }
    }

    //add user to database
    public static void addUser(String username, String password) throws SQLException {
        String query = "INSERT INTO player (username,password) VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st. setString(2, password);
        st.executeUpdate();
    }

    public static boolean checkLogin(String username, String password) throws SQLException {
        String user;
        String query = "SELECT username FROM player WHERE username = ? AND password = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1,username);
        st.setString(2, password);
        ResultSet names = st.executeQuery();
        if(names.next()) {
            user = names.getString("username");//will save username to userData later
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean saveQuiz(){
        return false;
    }

    public void disconnect() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
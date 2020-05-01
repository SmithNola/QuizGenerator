import java.io.File;
import java.sql.*;

public class DatabaseConnection {

    public static void connect() throws SQLException {
        Connection conn = null;
        try {
            File dbfile = new File(".");
            String url="jdbc:sqlite:" + dbfile.getAbsolutePath() + "\\src\\db\\QuizGen.db";
            // create a connection to the database3
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to Database has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

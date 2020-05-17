import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class DatabaseConnection {
    private static Connection conn;

    private static final String DATABASE_NAME = "QuizGen.db";
    public static final String CONNECTIONS_STRING = "jdbc:sqlite:" + Paths.get("src/main/db/" + DATABASE_NAME).toAbsolutePath();


    public boolean connect() {
        try {
            conn = DriverManager.getConnection(CONNECTIONS_STRING);
            System.out.println("Connection to " + DATABASE_NAME + " has been established.");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to " + DATABASE_NAME + " has been terminated.");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    //Will check if username already exists in database
    public static Boolean checkUsername(String username) throws SQLException {
        String query = "SELECT username FROM player WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet names = st.executeQuery();
        if (names.next()) {
            return true;
        } else {
            return false;
        }
    }

    //add user to database
    public static void addUser(String username, String password) throws SQLException {
        String query = "INSERT INTO player (username,password) VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        st.executeUpdate();
    }

    public static ArrayList<String> retrieveAllQuizzes() throws SQLException{
        String quiz;
        ArrayList <String> quizzes = new ArrayList<String>();
        String query = "SELECT quiz.quiz_name, quiz.genre, quiz.time_created, player.username FROM quiz INNER JOIN player ON  player.player_id = quiz.player_id;";
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet results = st.executeQuery();
        while (results.next()) {//puts each quiz property into one string for view
            quiz = results.getString("quiz_name") + "\t" + results.getString("genre") + "\t" + results.getString("time_created")
                    + "\t" + results.getString("username");
            quizzes.add(quiz);
        }
        return quizzes;
    }

    public static ArrayList<String> retrieveUserQuiz(String username) throws SQLException {
        String quizProperties;
        ArrayList<String> quizzes = new ArrayList<String>();
        String query = "SELECT quiz_name, genre, ordered, time_created FROM quiz INNER JOIN player ON player.player_id = quiz.player_id WHERE player.username = ? ;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet names = st.executeQuery();
        while (names.next()) {//puts each quiz property into one string for view
            quizProperties = names.getString("quiz_name") + "\t" + names.getString("genre") + "\t" + names.getString("ordered")
                    + "\t" + names.getString("time_created");
            quizzes.add(quizProperties);
        }
        return quizzes;
    }

    //Checks if user enters correct login info
    public static boolean checkLogin(String username, String password) throws SQLException {
        String user;
        String query = "SELECT username FROM player WHERE username = ? AND password = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        ResultSet names = st.executeQuery();
        if (names.next()) {
            user = names.getString("username");//will save username to userData later
            return true;
        } else {
            return false;
        }
    }

    //Saves quiz to the database
    public static void saveQuiz(Quiz quiz) throws SQLException {
        String query = "INSERT INTO quiz (quiz_name,ordered,genre,time_created,player_id) VALUES (?,?,?,CURRENT_TIMESTAMP,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, quiz.getName());
        st.setInt(2, quiz.getOrdered());
        st.setString(3, quiz.getGenre());
        st.setInt(4, getPlayerId(quiz.getCreator()));
        st.executeUpdate();
        ResultSet quizIdResult = st.getGeneratedKeys();
        int quizId = quizIdResult.getInt(1);
        saveQuestions(quiz, quizId);
    }

    public static void saveQuestions(Quiz quiz, int quizId) throws SQLException {
        String query = "INSERT INTO question (question_name,quiz_id,position) VALUES (?,?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        //saves each individual question to the database
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = quiz.getQuestions().get(i);
            st.setString(1, question.getName());
            st.setInt(2, quizId);
            //if quiz is ordered will insert position otherwise will leave NULL
            if (quiz.getOrdered() == 1) {
                st.setInt(3, question.getPosition());
            } else {
                st.setInt(3, NULL);
            }
            st.executeUpdate();
            ResultSet questionIdResult = st.getGeneratedKeys();
            int questionId = questionIdResult.getInt(1);
            saveChoices(question, questionId);
        }
    }

    private static void saveChoices(Question question, int questionId) throws SQLException {
        String query = "INSERT INTO choice (choice_name,question_id,answer) VALUES (?,?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        //saves each individual choice to the database
        for (int i = 0; i < question.getChoices().size(); i++) {
            String choice = question.getChoices().get(i);
            st.setString(1, choice);
            st.setInt(2, questionId);
            //if choice is the answer will set as answer other wise leave answer null
            if (question.getAnswer() == i) {
                st.setInt(3, 1);
            } else {
                st.setInt(3, NULL);
            }
            st.executeUpdate();
        }
    }

    //retrieves creator's Id
    private static int getPlayerId(String creator) throws SQLException {
        String query = "SELECT player_id FROM player WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, creator);
        ResultSet names = st.executeQuery();
        return names.getInt("player_id");
    }
}
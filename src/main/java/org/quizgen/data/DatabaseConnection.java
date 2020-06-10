package org.quizgen.data;

import org.quizgen.model.Choice;
import org.quizgen.model.Question;
import org.quizgen.model.Quiz;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class DatabaseConnection {
    private static Connection conn;
    private static final String DATABASE_NAME = "QuizGen.db";
    private static final String CONNECTIONS_STRING = "jdbc:sqlite:" + Paths.get("src/main/db/" + DATABASE_NAME).toAbsolutePath();

    public static boolean connect() {
        try {
            conn = DriverManager.getConnection(CONNECTIONS_STRING);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to " + DATABASE_NAME + " has been terminated.");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public static void updateQuiz(Quiz quiz) throws SQLException{
        String query = "UPDATE quiz SET quiz_name = ?, genre = ?, ordered = ? WHERE quiz_id = ?;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, quiz.getName());
        st.setString(2, quiz.getGenre());
        st.setInt(3, quiz.getOrdered());
        st.setInt(4, quiz.getQuizId());
        st.executeQuery();
        st.close();
    }

    public static void updateQuestions(Question question) throws SQLException{
        String query = "UPDATE question SET question_name = ? WHERE question_id = ?;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, question.getName());
        st.setInt(2, Integer.parseInt(question.getQuestionId()));
        st.executeQuery();
        st.close();
    }

    public static void updateChoices(Choice choice) throws SQLException{
        String query = "Update choice SET choice_name = ? WHERE choice_id = ?;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, choice.getName());
        st.setInt(2, choice.getId());
        st.executeQuery();
        st.close();
    }

    public static boolean checkIfPlayed(String username, int quizId) throws SQLException{
        String query = "SELECT * FROM quiz_played WHERE player_id = ? AND quiz_id = ?;";
        PreparedStatement st = conn.prepareStatement(query);
        int id = getPlayerId(username);
        st.setInt(1,id);
        st.setInt(2,quizId);
        ResultSet results = st.executeQuery();
        st.close();
        return results.next();
    }

    public static void saveScore(int score, String username, int quizId) throws SQLException {
            String query = "INSERT INTO quiz_played (player_id, quiz_id, score) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            int id = getPlayerId(username);
            st.setInt(1, id);
            st.setInt(2, quizId);
            st.setInt(3, score);
            st.executeUpdate();
            st.close();
    }

    //Will check if username already exists in database
    public static boolean checkUsername(String username) throws SQLException {
        String query = "SELECT username FROM player WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet names = st.executeQuery();
        st.close();
        return names.next();
    }

    //add user to database
    public static void addUser(String username, String password) throws SQLException {
        String query = "INSERT INTO player (username,password) VALUES (?,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        st.executeUpdate();
        st.close();
    }

    public static Quiz retrieveQuestions(Quiz quiz) throws SQLException{
        ArrayList<Question> questions = new ArrayList<>();
        String query = "SELECT question.question_id, question.question_name, question.position, choice.choice_name FROM quiz " +
                "LEFT JOIN question ON quiz.quiz_id = question.quiz_id " +
                "LEFT JOIN choice ON question.question_id = choice.question_id " +
                "WHERE quiz.quiz_id = ? AND choice.answer = 1;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, quiz.getQuizId());
        ResultSet results = st.executeQuery();
        while(results.next()){
            ArrayList <String> choices = retrieveChoices(results.getInt("question_id"));
            Question question = new Question(results.getInt("question_id"), results.getString("question_name"), choices.indexOf(results.getString("choice_name")) + 1,choices,results.getInt("position") );
            questions.add(question);
        }
        quiz.setQuestions(questions);
        st.close();
        return quiz;
    }

    public static ArrayList <String> retrieveChoices(int questionId) throws SQLException {
        ArrayList <String> choices = new ArrayList<String>();
        String query = "SELECT choice.choice_name FROM question " +
                        "LEFT JOIN choice ON choice.question_id = question.question_id WHERE question.question_id = ?;";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, questionId);
        ResultSet results = st.executeQuery();
        while(results.next()){
            choices.add(results.getString("choice_name"));
        }
        st.close();
        return choices;
    }

    public static ArrayList<Quiz> retrieveAllQuizzes() throws SQLException{
        Quiz quiz;
        ArrayList <Quiz> quizzes = new ArrayList<Quiz>();
        String query = "SELECT quiz.quiz_Id, quiz.quiz_name, quiz.genre, quiz.time_created, player.username,COUNT(question.quiz_id) as number_of_questions FROM quiz " +
                        "INNER JOIN player ON  player.player_id = quiz.player_id " +
                        "LEFT JOIN question ON quiz.quiz_id = question.quiz_id GROUP BY quiz.quiz_name;";
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet results = st.executeQuery();
        while (results.next()) {//puts each quiz property into one string for view
            quiz = new Quiz(results.getInt("quiz_Id"), results.getString("quiz_name"), results.getString("genre"),
                    results.getString("time_created"), results.getString("username"), results.getInt("number_of_questions"));
            quizzes.add(quiz);
        }
        st.close();
        return quizzes;
    }

    public static ArrayList<Quiz> retrieveUserQuiz(String username) throws SQLException {
        Quiz quiz;
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        String query = "SELECT quiz.quiz_id, quiz.quiz_name, quiz.genre, quiz.ordered, quiz.time_created, COUNT(question.quiz_id) as number_of_questions FROM quiz " +
                        "LEFT JOIN player ON player.player_id = quiz.player_id " +
                        "LEFT JOIN question ON quiz.quiz_id = question.quiz_id WHERE player.username = ? GROUP BY quiz.quiz_name";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet results = st.executeQuery();
        while (results.next()) {//puts each quiz property into one string for view
            quiz = new Quiz(results.getInt("quiz_Id"), results.getString("quiz_name"), results.getString("genre"),
                    results.getString("time_created"), username, results.getInt("number_of_questions"));
            quizzes.add(quiz);
        }
        st.close();
        return quizzes;
    }

    //Checks if user enters correct login info
    public static String checkLogin(String username, String password) throws SQLException {
        String query = "SELECT username FROM player WHERE username = ? AND password = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, password);
        ResultSet names = st.executeQuery();
        //st.close();
        if (names.next()) {
            return names.getString("username");//will save username to userData later
        } else {
            return null;
        }
    }

    //Saves quiz to the database
    public static void saveQuiz(Quiz quiz) throws SQLException {
        int id = getPlayerId(quiz.getCreator());
        String query = "INSERT INTO quiz (quiz_name,ordered,genre,time_created,player_id) VALUES (?,?,?,CURRENT_TIMESTAMP,?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, quiz.getName());
        st.setInt(2, quiz.getOrdered());
        st.setString(3, quiz.getGenre());
        st.setInt(4, id);
        st.executeUpdate();
        ResultSet quizIdResult = st.getGeneratedKeys();
        int quizId = quizIdResult.getInt(1);
        saveQuestions(quiz, quizId);
        st.close();
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
        st.close();
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
            if (question.getAnswer() == i + 1) {//if answer is equal to choice index
                st.setInt(3, 1);
            } else {
                st.setInt(3, 0);
            }
            st.executeUpdate();//save to database
        }
        st.close();
    }

    //retrieves creator's Id
    private static int getPlayerId(String username) throws SQLException {
        int playerId = 0;
        String query = "SELECT player_id FROM player WHERE username = ?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username);
        ResultSet id = st.executeQuery();
        if(id.next() == true){
             playerId = id.getInt("player_id");
        }
        st.close();
        return playerId;
    }
}
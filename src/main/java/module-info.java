module org.quizgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.quizgen to javafx.fxml;
    opens org.quizgen.controller to javafx.fxml;
    exports org.quizgen;
    exports org.quizgen.controller;
}
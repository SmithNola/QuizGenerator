module org.quizgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires org.junit.jupiter.api;
    requires junit;

    opens org.quizgen to javafx.fxml;
    opens org.quizgen.controller to javafx.fxml;
    exports org.quizgen;
    exports org.quizgen.controller;
}
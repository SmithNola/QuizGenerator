module org.quizgen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires commons.codec;

    opens org.quizgen to javafx.fxml;
    opens org.quizgen.controller to javafx.fxml;
    opens org.quizgen.controller.authentication to javafx.fxml;
    exports org.quizgen;
    exports org.quizgen.controller;
    exports org.quizgen.controller.authentication;
}
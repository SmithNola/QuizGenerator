module org.example.help {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.help to javafx.fxml;
    exports org.example.help;
}
module com.example.kursrabot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kursrabot to javafx.fxml;
    exports com.example.kursrabot;
}
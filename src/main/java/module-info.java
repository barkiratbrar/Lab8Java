module com.example.lab8java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab8java to javafx.fxml;
    exports com.example.lab8java;
}
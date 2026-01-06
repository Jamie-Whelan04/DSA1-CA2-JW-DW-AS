module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports LinkedList;
    opens LinkedList to javafx.fxml;
    exports app;
    opens app to javafx.fxml;
    exports hashtables;
    opens hashtables to javafx.fxml;
    exports models;
    opens models to javafx.fxml;
    exports sort;
    opens sort to javafx.fxml;
}
module com.example.rubiks {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.rubiks to javafx.fxml;
    exports com.example.rubiks;
}
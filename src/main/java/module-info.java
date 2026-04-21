module org.example.interacticecounterapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.interacticecounterapp to javafx.fxml;
    exports org.example.interacticecounterapp.Application;
    opens org.example.interacticecounterapp.Application to javafx.fxml;
}
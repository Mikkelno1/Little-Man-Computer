module org.example.interacticecounterapp {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.example.llc.Application;
    opens org.example.llc.Application to javafx.fxml;
}
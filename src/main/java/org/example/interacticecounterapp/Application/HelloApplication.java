package org.example.interacticecounterapp.Application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.interacticecounterapp.UI.UI;

import java.io.IOException;

public class HelloApplication extends Application
{
    Parent root = null;
    int height = 600;
    int width = 600;

    @Override
    public void start(Stage stage) throws IOException
    {
        UI ui = new UI();
        root = ui.getView();

        Scene scene = new Scene(root, height, width);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

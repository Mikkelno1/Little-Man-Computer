package org.example.llc.Application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.llc.UI.UI;

import java.io.IOException;

/**
 * Defines stage and launches application
 */
public class HelloApplication extends Application
{
    Parent root = null;
    int width = 1280;
    int height = 720;

    @Override
    public void start(Stage stage) throws IOException
    {

        UI ui = new UI();
        root = ui.getView();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("LLC!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

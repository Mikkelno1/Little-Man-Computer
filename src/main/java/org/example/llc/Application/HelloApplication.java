package org.example.llc.Application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.llc.UI.MemoryCell;
import org.example.llc.UI.UI;

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
        BorderPane root = new BorderPane();
        TextField test2  = new TextField("MMMM");
        MemoryCell test = new MemoryCell(12);
        root.setCenter(test);
        root.setRight(test2);



        Scene scene = new Scene(root, height, width);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

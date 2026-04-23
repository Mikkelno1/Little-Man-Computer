package org.example.llc.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CCVBoxInsert extends VBox
{

    private TextField tf;
    private Label lb;

    public CCVBoxInsert(String text, int x, int y, boolean editable)
    {
        tf = new TextField();
        lb = new Label(text);
        tf.setEditable(editable);
        getChildren().addAll(lb,tf);
        setSpacing(5);
        setAlignment(Pos.CENTER);
        tf.setMaxSize(x, y);
        tf.setMinSize(x, y);
    }

    public String getText()
    {
        return tf.getText();
    }

    public void setText(String text)
    {
        tf.setText(text);
    }
}

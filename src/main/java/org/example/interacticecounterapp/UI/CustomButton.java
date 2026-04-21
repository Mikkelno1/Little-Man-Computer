package org.example.interacticecounterapp.UI;

import javafx.scene.control.Button;

public class CustomButton extends Button
{

    public CustomButton(int layoutX, int layoutY, String text)
    {
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setText(text);
    }

}

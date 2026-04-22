package org.example.interacticecounterapp.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.interacticecounterapp.Application.HelloController;


public class UI
{

    public Pane root = null;
    private Label label = null;
    private CustomButton cbIncrement = null;
    private CustomButton cbDecrement = null;
    private CustomButton cbReset = null;
    private final HelloController CONTROLLER = new HelloController();

    public UI()
    {
        layout();

        cbIncrement.setOnAction(event -> {CONTROLLER.increment(); updateLabel();});
        cbDecrement.setOnAction(event -> {CONTROLLER.decrement(); updateLabel();});
        cbReset.setOnAction(event -> {CONTROLLER.reset(); updateLabel();});
    }

    public void layout()
    {
        root = new Pane();
        cbIncrement = new CustomButton(100,500, "Increment");
        cbDecrement = new CustomButton(200,500, "Decrement");
        cbReset = new CustomButton(300,500, "Reset");

        label = new Label();
        label.setLayoutX(200);
        label.setLayoutY(200);

        root.getChildren().addAll(cbIncrement, cbDecrement, cbReset, label);
    }

    public Pane getView()
    {
        return root;
    }

    public void updateLabel()
    {
        label.setText(String.valueOf(CONTROLLER.showValue()));
    }
}

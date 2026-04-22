package org.example.llc.UI;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;

public class MemoryCell extends VBox {

    private final int address;
    private final Label addressLbl;
    private final TextField memoryField;

    public MemoryCell(int address) {
        this.address = address;

        this.addressLbl = new Label(String.format("%02d", address));
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            if (newText.matches("\\d{0,3}"))  //tjekker om det er et digit \\d og input er 3 digits lang {0,3}
            {
                return change; // ændre Text
            }
            else
            {
                return null; // ændre ikke
            }
        };
        this.memoryField = new TextField("000");
        memoryField.setTextFormatter(new TextFormatter<>(filter));

        getChildren().addAll(this.addressLbl, memoryField);
        setSpacing(2);
    }

    public int getAddress() {
        return address;
    }

    public TextField getMemoryField() {
        return memoryField;
    }

    public String getText() {
        return memoryField.getText();
    }

    public void setText(String text) {

        memoryField.setText(text);
    }
}




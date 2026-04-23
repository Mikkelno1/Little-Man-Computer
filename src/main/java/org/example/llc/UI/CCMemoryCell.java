package org.example.llc.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;

public class CCMemoryCell extends VBox {

    private final int address;
    private final Label addressLbl;
    private final TextField memoryField;

    public CCMemoryCell(int address) {
        super();
        this.address = address;
        this.addressLbl = new Label(String.format("%02d", address));
        this.memoryField = initializeMemField();

        getChildren().addAll(this.addressLbl, memoryField);
        setAlignment(Pos.CENTER);

    }

    public int getAddress() {
        return address;
    }

    public TextField getMemoryField()  //Objektet
    {
        return memoryField;
    }

    public String getValue()              //Teksten i objektet
    {
        return memoryField.getText(); //Integer.parseInt(memoryField.getText());
    }

    public void setText(String text) {

        memoryField.setText(text);
    }

    private TextField initializeMemField()
    {
        TextField memField = new TextField("000");
        memField.setAlignment(Pos.CENTER);

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            if (newText.isEmpty()) return change;

            if (newText.matches("-?\\d{0,3}")) return change;

            return null;
        };

        memField.setTextFormatter(new TextFormatter<>(filter));

        memField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) { //Gained focus
                if (memField.getText().equals("000")) {
                    memField.clear();
                }
            }

            if (!newVal) { //lost focus
                String text = memField.getText();

                if (text.isEmpty()) {
                    memField.setText("000");
                } else {
                    memField.setText(String.format("%03d", Integer.parseInt(text)));
                }
            }
        });

        return memField;
    }

    public void setValue(String text)
    {
        if (text == null || text.isEmpty())
        {
            memoryField.setText("000");
            return;
        }
        try
        {
            int value = Integer.parseInt(text);
            memoryField.setText(String.format("%03d", value));
        }
        catch (NumberFormatException e)
        {
            memoryField.setText("000");
        }
    }


}




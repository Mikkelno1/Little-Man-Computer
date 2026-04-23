package org.example.llc.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.example.llc.Application.HelloController;
import org.example.llc.Service.MachineSim;

import java.io.File;
import java.io.FileNotFoundException;


public class UI
{
    private final HelloController controller = new HelloController();
    BorderPane root = new BorderPane();
    private VBox vbLeft;
    private VBox vbRight;
    private HBox hbTop;
    private HBox hbBottom;
    private GridPane gpAddress;
    private Label lbWarning;
    private Label lbOutput;
    private Button btnSave;
    private Button btnLoad;
    private Button btnRun;
    private Button btnStep;
    private Button btnStop;
    private Button btnReset;
    private ListView<Integer> lvOutput;
    private ObservableList<Integer> outputList = FXCollections.observableArrayList();
    private CCVBoxInsert ccVbInput;
    private CCVBoxInsert ccVbProgram;
    private CCVBoxInsert ccVbInstReg;
    private CCVBoxInsert ccVbAddReg;
    private CCVBoxInsert ccVbAcc;

    private final CCMemoryCell[] cells = new CCMemoryCell[100];
    private String[] tfLoadArray;
    private String[] tfSaveArray;

    public UI()
    {
        paneLayout();
        leftLayout();
        rightLayout();
        topLayout();
        bottomLayout();
        createCells();
        writeToOutput();

        btnSave.setOnAction(event -> {
            valueFetch();
            saveFile(tfSaveArray);
        });

        btnLoad.setOnAction(event -> loadFile());

        btnRun.setOnAction(event -> {simulateGame(true); loadInput(); writeToOutput();});
    }


        private void paneLayout ()
        {
            hbBottom = new HBox();
            hbTop = new HBox();
            vbLeft = new VBox();
            vbRight = new VBox();
            gpAddress = new GridPane();

        root.setPadding(new Insets(15));
        root.setBottom(hbBottom);
        root.setTop(hbTop);
        root.setLeft(vbLeft);
        root.setRight(vbRight);
        root.setCenter(gpAddress);
    }



    private void createCells()
    {
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                int address = row * 10 + col;

                CCMemoryCell cell = new CCMemoryCell(address);
                cells[address] = cell;
                cell.getMemoryField().focusedProperty().addListener((obs, oldVal, newVal) -> {
                    if (!newVal) { // lost focus = user finished editing
                        int addr = cell.getAddress();
                        int value = Integer.parseInt(cell.getValue());
                        controller.passOpcodeToMemory(addr, value);
                    }
                });

                gpAddress.add(cell, col, row);
            }
        }
        gpAddress.setAlignment(Pos.CENTER);
        gpAddress.setPadding(new Insets(15));
    }

    private void valueFetch()
    {
        tfSaveArray = new String[100];
        for (int i = 0; i < cells.length; i++)
        {
            tfSaveArray[i] = String.valueOf(cells[i].getValue());
        }
    }

        private void rightLayout ()
        {
            lbOutput = new Label("Output");

            lvOutput = new ListView<>();

            vbRight.setAlignment(Pos.CENTER);
            vbRight.setPadding(new Insets(10));
            vbRight.getChildren().addAll(lbOutput, lvOutput);

        }

        private void leftLayout ()
        {
            ccVbInput = new CCVBoxInsert("Input", 40, 40, true);
            ccVbProgram = new CCVBoxInsert("Program counter", 40, 40, false);
            ccVbInstReg = new CCVBoxInsert("Instruction Register", 40, 40, false);
            ccVbAddReg = new CCVBoxInsert("Address Register", 40, 40, false);
            ccVbAcc = new CCVBoxInsert("Accumulator", 40, 40,false );

            vbLeft.setAlignment(Pos.CENTER);
            vbLeft.setPadding(new Insets(5));
            vbLeft.setSpacing(30);
            vbLeft.getChildren().addAll(ccVbInput, ccVbProgram, ccVbInstReg, ccVbAddReg, ccVbAcc);
        }

        private void topLayout ()
        {
            btnSave = new Button("Save");
            btnLoad = new Button("Load");
            Rectangle filler = new Rectangle();

            filler.setFill(Color.TRANSPARENT);
            filler.setWidth(10);

            hbTop.setAlignment(Pos.TOP_RIGHT);
            hbTop.setPadding(new Insets(38));
            hbTop.setSpacing(30);
            hbTop.getChildren().addAll(btnSave, btnLoad, filler);
        }

    private void bottomLayout() {
        btnRun = new Button("Run");
        btnRun.setPrefSize(45, 25);

        btnStep = new Button("Step");
        btnStep.setPrefSize(45, 25);

        btnStop = new Button("Stop");
        btnStop.setPrefSize(45, 25);

        btnReset = new Button("Reset");
        btnReset.setPrefSize(45, 25);

        lbWarning = new Label();

        hbBottom.setAlignment(Pos.BOTTOM_CENTER);
        hbBottom.setPadding(new Insets(20));
        hbBottom.setSpacing(100);
        hbBottom.getChildren().addAll(
                btnRun, btnStep, btnStop, btnReset, lbWarning
        );
    }

    private void saveFile(String[] data)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("ASCII", "*.ASC")
        );

        File file = fileChooser.showSaveDialog(null);

        if (file != null)
        {
            controller.saveFile(data, file);
        }
    }



    private void loadFile() {
        try {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("ASCII", "*.ASC")
            );

            File file = fileChooser.showOpenDialog(null);

            if (file != null)
            {
                tfLoadArray = controller.loadFile(file);
                updateOperators();
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void updateOperators()
    {
        for (int i = 0; i < cells.length; i++)
        {
            if (tfLoadArray != null && i < tfLoadArray.length && tfLoadArray[i] != null)
            {
                cells[i].setValue(tfLoadArray[i]);
            } else
            {
                cells[i].setValue("000");
            }
        }
    }

    private void loadInput()
    {
        if (ccVbInput.getText().isBlank())
        {
            controller.loadInput("000");
        } else
        {
            controller.loadInput(ccVbInput.getText());
        }
    }

    private void writeToOutput()
    {
        int accumulator = controller.writeToOutput();
        outputList.add(accumulator);

        lvOutput.setItems(outputList);
    }

    public BorderPane getView() {
        return root;
    }

    public void simulateGame(boolean running)
    {
        controller.simulateGame(running);
    }


}
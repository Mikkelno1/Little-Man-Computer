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
    private Button btnResetProg;
    private Button btnReset;
    private Button btnEnter;
    private AnchorPane ap;
    private ListView<Integer> lvOutput;
    private ObservableList<Integer> outputList = FXCollections.observableArrayList();
    private CCVBoxInsert ccVbInput;
    private CCVBoxInsert ccVbProgram;
    private CCVBoxInsert ccVbInstReg;
    private CCVBoxInsert ccVbAddReg;
    private CCVBoxInsert ccVbAcc;
    private int buttonClicked;

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
        //writeToOutput();

        btnSave.setOnAction(event -> {
            valueFetch();
            saveFile(tfSaveArray);
        });

        btnLoad.setOnAction(event -> loadFile());

        btnStep.setOnAction(event -> {
            buttonClicked = 2;
            controller.setRunning(true);
            controller.step();
            refreshUI();
        });

        btnRun.setOnAction(event -> {
            buttonClicked = 1;
            controller.setRunning(true);
            while(!controller.isWaiting() && controller.isRunning()){
                System.out.println(controller.isWaiting());
                btnStep.fire();
            }
            /*loadInput();
            writeToOutput()*/;});

        btnEnter.setOnAction(event -> {

            if (controller.isWaiting())
            {
                controller.loadInput(ccVbInput.getText());
                controller.setRunning(true);
                lbWarning.setText("");
            }
            if (buttonClicked == 1)
            {
                btnRun.fire();
            } else if (buttonClicked == 2)
            {
                btnStep.fire();
            }
            refreshUI();
        });

        btnResetProg.setOnAction(event -> {resetCPU(); refreshUI(); resetProgram();});

        btnReset.setOnAction(event -> {resetProgCount(); refreshUI(); refreshMemory();});

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
            lvOutput.setItems(outputList);

            vbRight.setAlignment(Pos.CENTER);
            vbRight.setPadding(new Insets(10));
            vbRight.getChildren().addAll(lbOutput, lvOutput);

        }

        private void leftLayout ()
        {
            ccVbInput = new CCVBoxInsert("Input", 40, 40, true);
            btnEnter = new Button("Enter Value");
            ccVbProgram = new CCVBoxInsert("Program counter", 40, 40, false);
            ccVbInstReg = new CCVBoxInsert("Instruction Register", 40, 40, false);
            ccVbAddReg = new CCVBoxInsert("Address Register", 40, 40, false);
            ccVbAcc = new CCVBoxInsert("Accumulator", 40, 40,false );

            vbLeft.setAlignment(Pos.CENTER);
            vbLeft.setPadding(new Insets(5));
            vbLeft.setSpacing(30);
            vbLeft.getChildren().addAll(ccVbInput, btnEnter , ccVbProgram, ccVbInstReg, ccVbAddReg, ccVbAcc);
        }

        private void topLayout ()
        {
            btnSave = new Button("Save");
            btnLoad = new Button("Load");

            Rectangle filler = new Rectangle();
            ap = new AnchorPane();
            lbWarning = new Label();

            filler.setFill(Color.TRANSPARENT);
            filler.setWidth(10);

            hbTop.setAlignment(Pos.TOP_RIGHT);
            hbTop.setPadding(new Insets(38));
            hbTop.setSpacing(30);
            AnchorPane.setLeftAnchor(lbWarning, 100.0);
            AnchorPane.setTopAnchor(lbWarning, 10.0);
            lbWarning.setPrefSize(250,50);
            lbWarning.setStyle("-fx-font-size: 50; -fx-text-fill: #FF0000");

            AnchorPane.setRightAnchor(btnSave, 180.0);
            AnchorPane.setTopAnchor(btnSave, 50.0);
            AnchorPane.setRightAnchor(btnLoad, 50.0);
            AnchorPane.setTopAnchor(btnLoad, 50.0);
            ap.getChildren().addAll(lbWarning, btnSave, btnLoad, filler);
            root.setTop(ap);

        }

    private void bottomLayout() {
        btnRun = new Button("Run");
        btnRun.setPrefSize(45, 25);

        btnStep = new Button("Step");
        btnStep.setPrefSize(45, 25);

        btnResetProg = new Button("Clear");
        btnResetProg.setPrefSize(45, 25);

        btnReset = new Button("Reset");
        btnReset.setPrefSize(45,25);

        hbBottom.setAlignment(Pos.BOTTOM_CENTER);
        hbBottom.setPadding(new Insets(20));
        hbBottom.setSpacing(100);
        hbBottom.getChildren().addAll(
                btnRun, btnStep, btnReset, btnResetProg
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
    /*
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
     */
    /*
    private void writeToOutput()
    {
        int accumulator = controller.sendAccuToOutput();
        outputList.add(accumulator);

        lvOutput.setItems(outputList);
    }
    */

    public BorderPane getView() {
        return root;
    }



    public void refreshUI()
    {
        if(controller.isWaiting())
        {
            btnRun.setDisable(true);
            btnStep.setDisable(true);
            lbWarning.setText("INPUT PLS");
        } else {
            btnRun.setDisable(false);
            btnStep.setDisable(false);
        }
        refreshOutput();
        refreshAccumulator();
        refreshProgCounter();
        refreshAddress();
        refreshInstReg();
    }

    private void refreshOutput()
    {
        outputList.setAll(controller.getOutputValues());
    }

    private void refreshAccumulator()
    {
        ccVbAcc.setText(Integer.toString(controller.getAccumulator()));
    }

    private void refreshProgCounter()
    {
        ccVbProgram.setText(Integer.toString(controller.getProgramCounter()));
    }

    private void refreshAddress()
    {
        ccVbAddReg.setText(Integer.toString(controller.getAddress()));
    }

    private void refreshInstReg()
    {
        ccVbInstReg.setText(Integer.toString(controller.getInstReg()));
    }

    private void resetCPU()
    {
        controller.resetProgram();
    }

    private void resetProgCount()
    {
        controller.resetProgCount();
    }

    private void refreshMemory()
    {
        int[] memory = controller.getMemory();
        for (int i = 0; i < cells.length; i++)
        {
            CCMemoryCell cell = cells[i];
            cell.setValue(String.valueOf(memory[i]));
        }
    }

    private void resetProgram()
    {
        controller.resetProgram();
        for (int i = 0; i < cells.length; i++)
        {
            CCMemoryCell cell = cells[i];
            cell.setValue("000");
        }
    }

}
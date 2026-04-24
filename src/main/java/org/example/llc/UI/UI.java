package org.example.llc.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import org.example.llc.Service.MachineSim;

import java.io.File;
import java.io.FileNotFoundException;


public class UI
{
    MachineSim mSim = new MachineSim();
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
    private String[] tfSaveArray;

    public UI()
    {
        paneLayout();
        leftLayout();
        rightLayout();
        topLayout();
        bottomLayout();
        createCells();

        btnSave.setOnAction(event -> {
            valueFetch();
            saveFile(tfSaveArray);
        });

        btnLoad.setOnAction(event -> {
            loadFile();
            refreshUI();
        });

        btnStep.setOnAction(event -> {
            buttonClicked = 0;
            lbWarning.setText("");
            mSim.setRunning(true);
            mSim.step();
            refreshUI();
        });

        btnRun.setOnAction(event -> {
            buttonClicked = 1;
            mSim.setRunning(true);
            lbWarning.setText("");
            while(!mSim.isWaiting() && mSim.isRunning()){
                System.out.println(mSim.isWaiting());
                mSim.step();
                refreshUI();
            }
            });

        btnEnter.setOnAction(event -> {

            if (mSim.isWaiting())
            {
                mSim.loadInput(ccVbInput.getText());
                mSim.setRunning(true);
                lbWarning.setText("");
                if (buttonClicked == 1) {
                    btnRun.fire();
                }
                else
                {
                    btnStep.fire();
                }
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
                        mSim.setMemoryValue(addr, value);
                    }
                });

                gpAddress.add(cell, col, row);
            }
        }
        gpAddress.setAlignment(Pos.CENTER);
        gpAddress.setPadding(new Insets(15));
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

        ap = new AnchorPane();
        lbWarning = new Label();

        hbTop.setAlignment(Pos.TOP_RIGHT);
        hbTop.setPadding(new Insets(38));
        hbTop.setSpacing(30);
        AnchorPane.setLeftAnchor(lbWarning, 100.0);
        AnchorPane.setTopAnchor(lbWarning, 10.0);
        lbWarning.setPrefSize(300,50);
        lbWarning.setStyle("-fx-font-size: 50; -fx-text-fill: #FF0000");

        AnchorPane.setRightAnchor(btnSave, 180.0);
        AnchorPane.setTopAnchor(btnSave, 50.0);
        AnchorPane.setRightAnchor(btnLoad, 50.0);
        AnchorPane.setTopAnchor(btnLoad, 50.0);

        ap.getChildren().addAll(lbWarning, btnSave, btnLoad);
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
            mSim.saveFile(file);
        }
    }

    private void valueFetch()
    {
        tfSaveArray = new String[100];
        for (int i = 0; i < cells.length; i++)
        {
            tfSaveArray[i] = String.valueOf(cells[i].getValue());
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
                mSim.loadFile(file);
                refreshUI();
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public BorderPane getView() {
        return root;
    }

    public void refreshUI()
    {
        if(mSim.isWaiting())
        {
            btnRun.setDisable(true);
            btnStep.setDisable(true);
            lbWarning.setText("INPUT PLS");
        } else {
            btnRun.setDisable(false);
            btnStep.setDisable(false);
        }
        if(mSim.isTrouble())
        {
            lbWarning.setText("Wrong input");
            mSim.setTrouble(false);
            mSim.resetProgCount();
        }

        refreshOutput();
        refreshAccumulator();
        refreshProgCounter();
        refreshAddress();
        refreshInstReg();
        refreshMemory();
    }

    private void refreshOutput()
    {
        outputList.setAll(mSim.getOutputValues());
    }

    private void refreshAccumulator()
    {
        ccVbAcc.setText(Integer.toString(mSim.getAccumulator()));
    }

    private void refreshProgCounter()
    {
        ccVbProgram.setText(Integer.toString(mSim.getProgramCounter()));
    }

    private void refreshAddress()
    {
        ccVbAddReg.setText(Integer.toString(mSim.getAddress()));
    }

    private void refreshInstReg()
    {
        ccVbInstReg.setText(Integer.toString(mSim.getInstReg()));
    }

    private void resetCPU()
    {
        mSim.resetProgram();
    }

    private void resetProgCount()
    {
        mSim.resetProgCount();
    }

    private void refreshMemory()
    {
        int[] memory = mSim.getMemory();
        for (int i = 0; i < cells.length; i++)
        {
            CCMemoryCell cell = cells[i];
            cell.setValue(String.valueOf(memory[i]));
        }
    }

    private void resetProgram()
    {
        mSim.resetProgram();
        for (int i = 0; i < cells.length; i++)
        {
            CCMemoryCell cell = cells[i];
            cell.setValue("000");
        }
    }
}
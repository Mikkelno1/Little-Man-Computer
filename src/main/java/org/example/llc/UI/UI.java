package org.example.llc.UI;

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

public class UI {

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
    private ListView<String> lvOutput;

    private CCMemoryCell[] memCellArray;
    private String[] tfLoadArray;
    private String[] tfSaveArray;

    private final HelloController CONTROLLER = new HelloController();

    public UI() {
        paneLayout();
        leftLayout();
        rightLayout();
        topLayout();
        bottomLayout();
        createCells();

        btnSave.setOnAction(event -> {
            valueFetch();
            saveFile();
        });

        btnLoad.setOnAction(event -> loadFile());
    }

    private void paneLayout() {
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

    private void createCells() {
        int k = 0;
        memCellArray = new CCMemoryCell[100];
        tfSaveArray = new String[100];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                CCMemoryCell cell = new CCMemoryCell(k);
                gpAddress.add(cell, j, i);
                memCellArray[k] = cell;
                k++;
            }
        }

        gpAddress.setAlignment(Pos.CENTER);
        gpAddress.setPadding(new Insets(15));
        gpAddress.setVgap(10);
        gpAddress.setHgap(10);
    }

    private void valueFetch() {
        for (int i = 0; i < memCellArray.length; i++) {
            tfSaveArray[i] = String.valueOf(memCellArray[i].getValue());
        }
    }

    private void rightLayout() {
        lbOutput = new Label("Output");
        lvOutput = new ListView<>();

        vbRight.setAlignment(Pos.CENTER);
        vbRight.setPadding(new Insets(10));
        vbRight.getChildren().addAll(lbOutput, lvOutput);
    }

    private void leftLayout() {
        CCVBoxInsert ccVbInput = new CCVBoxInsert("Program counter", 40, 40);
        CCVBoxInsert ccVbProgram = new CCVBoxInsert("Program counter", 40, 40);
        CCVBoxInsert ccVbInstReg = new CCVBoxInsert("Instruction Register", 40, 40);
        CCVBoxInsert ccVbAddReg = new CCVBoxInsert("Address Register", 40, 40);
        CCVBoxInsert ccVbAcc = new CCVBoxInsert("Accumulator", 40, 40);

        vbLeft.setAlignment(Pos.CENTER);
        vbLeft.setPadding(new Insets(5));
        vbLeft.setSpacing(30);
        vbLeft.getChildren().addAll(
                ccVbInput, ccVbProgram, ccVbInstReg, ccVbAddReg, ccVbAcc
        );
    }

    private void topLayout() {
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

    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("ASCII", "*.ASC")
        );

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            CONTROLLER.saveFile(tfSaveArray, file);
        }
    }

    private void loadFile() {
        try {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("ASCII", "*.ASC")
            );

            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                tfLoadArray = CONTROLLER.loadFile(file);
                updateOperators();
            }

        } catch (RuntimeException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateOperators() {
        for (int i = 0; i < memCellArray.length; i++) {
            if (tfLoadArray != null && i < tfLoadArray.length && tfLoadArray[i] != null) {
                memCellArray[i].setValue(tfLoadArray[i]);
            } else {
                memCellArray[i].setValue("000");
            }
        }
    }

    public BorderPane getView() {
        return root;
    }
}
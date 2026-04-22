package org.example.llc.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UI
{

    BorderPane root = new BorderPane();
    private VBox vbLeft = null;
    private VBox vbRight = null;
    private HBox hbTop = null;
    private HBox hbBottom = null;
    private GridPane gpAddress = null;
    private Label lbWarning = null;
    private Label lbOutput = null;
    private Button btnSave = null;
    private Button btnLoad = null;
    private Button btnRun = null;
    private Button btnStep = null;
    private Button btnStop = null;
    private Button btnReset = null;
    private ListView lvOutput = null;

    public UI()
    {
        paneLayout();
        leftLayout();
        rightLayout();
        topLayout();
        bottomLayout();
        daiouwd();
    }


        private void paneLayout ()
        {
            hbBottom = new HBox();
            hbTop = new HBox();
            vbLeft = new VBox();
            vbRight = new VBox();
            gpAddress = new GridPane();

            root.setBottom(hbBottom);
            root.setTop(hbTop);
            root.setLeft(vbLeft);
            root.setRight(vbRight);
            root.setCenter(gpAddress);
        }

        private void daiouwd ()
        {
            int k = 0;
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    gpAddress.add(new CCMemoryCell(k++), j, i);
                    gpAddress.setVgap(10);
                    gpAddress.setHgap(10);
                }
            }
            gpAddress.setAlignment(Pos.CENTER);
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
            CCVBoxInsert ccVbInput = new CCVBoxInsert("Program counter", 40, 40);
            CCVBoxInsert ccVbProgram = new CCVBoxInsert("Program counter", 40, 40);
            CCVBoxInsert ccVbInstReg = new CCVBoxInsert("Instruction Register", 40, 40);
            CCVBoxInsert ccVbAddReg = new CCVBoxInsert("Address Register", 40, 40);
            CCVBoxInsert ccVbAcc = new CCVBoxInsert("Accumulator", 40, 40);

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


        private void bottomLayout ()
        {
            btnRun = new Button("Run");
            btnRun.setPrefSize(45 , 25);
            btnStep = new Button("Step");
            btnStep.setPrefSize(45 , 25);
            btnStop = new Button("Stop");
            btnStop.setPrefSize(45 , 25);
            btnReset = new Button("Reset");
            btnReset.setPrefSize(45 , 25);
            lbWarning = new Label();

            hbBottom.setAlignment(Pos.BOTTOM_CENTER);
            hbBottom.setPadding(new Insets(20));
            hbBottom.setSpacing(100);
            hbBottom.getChildren().addAll(btnRun, btnStep, btnStop, btnReset, lbWarning);
        }


        public BorderPane getView ()
        {
            return root;
        }


    }


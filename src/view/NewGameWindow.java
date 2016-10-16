package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.LuffaSchackController;
import model.PieceValueEnum;

/**
 * 
 * @author Krist
 */

/**
 * The class creates and handles events from a window where to adjust settings
 * for the game. For example, select which color to play with, select which player
 * to make first move, select if opponent is going to be another human player or
 * an Ai, select the boardsize (12x12 or 15x15).
 * @author Erik
 */
public class NewGameWindow {

    private PieceValueEnum selectedColor;
    private boolean whoGoesFirst, opponent;
    private LuffaSchackController luffaSchackController;
    private int selectedBoardSize;

    public void setController(LuffaSchackController luffaSchackController) {
        this.luffaSchackController = luffaSchackController;
    }

    /**
     * The method creates the main window for the enitre game and displays it
     * on the screen
     */
    public void display() {
        Stage newGameWindow = new Stage();
        newGameWindow.initModality(Modality.APPLICATION_MODAL);
        newGameWindow.setTitle("New Game");

        BorderPane windowLayout = new BorderPane();

        Label labelColor = new Label("Select color");
        Label labelTurn = new Label("\nSelect player to begin");
        Label labelBoardSize = new Label("\nSelect board size");
        Label labelopponent = new Label("\nSelect opponent");

        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            luffaSchackController.eventHandlerOkButton();
            newGameWindow.close();
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> newGameWindow.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));
        HBox buttonMenu = new HBox(2);
        buttonMenu.setSpacing(10);
        buttonMenu.setPadding(new Insets(30, 10, 10, 10));
        buttonMenu.setAlignment(Pos.CENTER);

        buttonMenu.getChildren().addAll(ok, cancel);

        windowLayout.setBottom(buttonMenu);
        //********************************************************************
        //              Creating of RadioButtons
        //*********************************************************

        selectedColor = PieceValueEnum.WHITE; // default value
        whoGoesFirst = true; //default value
        selectedBoardSize = 15; // default value
        opponent = true; //default value (AI)

        ToggleGroup groupColor = new ToggleGroup();
        ToggleGroup groupWhoGoesFirst = new ToggleGroup();
        ToggleGroup groupBoardSize = new ToggleGroup();
        ToggleGroup groupOpponent = new ToggleGroup();

        RadioButton rbWhite = new RadioButton("White");
        RadioButton rbBlack = new RadioButton("Black");
        RadioButton rbPlayer = new RadioButton("Player1");
        RadioButton rbOpponent = new RadioButton("Opponent");
        RadioButton rbBoardSize15x15 = new RadioButton("15x15");
        RadioButton rbBoardSize12x12 = new RadioButton("12x12");
        RadioButton rbAI = new RadioButton("AI");
        RadioButton rbPlayer2 = new RadioButton("Player");

        rbAI.setToggleGroup(groupOpponent);
        rbAI.setSelected(true);
        rbPlayer2.setToggleGroup(groupOpponent);

        rbWhite.setToggleGroup(groupColor);
        rbWhite.setSelected(true);
        rbBlack.setToggleGroup(groupColor);

        rbPlayer.setToggleGroup(groupWhoGoesFirst);
        rbPlayer.setSelected(true);
        rbOpponent.setToggleGroup(groupWhoGoesFirst);

        rbBoardSize15x15.setToggleGroup(groupBoardSize);
        rbBoardSize15x15.setSelected(true);
        rbBoardSize12x12.setToggleGroup(groupBoardSize);

        //*************************************************************************
        //                      Opponent Toggle
        //************************************************************************  
        groupOpponent.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();

                if (check.getText().equalsIgnoreCase("AI")) {
                    opponent = true;

                } else if (check.getText().equalsIgnoreCase("Player")) {
                    opponent = false;

                }
            }
        });

        //*************************************************************************
        //                      Color Toggle
        //************************************************************************  
        groupColor.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();

                if (check.getText().equalsIgnoreCase("White")) {
                    selectedColor = PieceValueEnum.WHITE;

                } else if (check.getText().equalsIgnoreCase("Black")) {
                    selectedColor = PieceValueEnum.BLACK;

                }
            }
        });
        //*************************************************************************
        //                      Who Goes First Toggle
        //************************************************************************
        groupWhoGoesFirst.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();

                if (check.getText().equalsIgnoreCase("Player1")) {
                    whoGoesFirst = true;

                } else if (check.getText().equalsIgnoreCase("Opponent")) {
                    whoGoesFirst = false;

                }
            }
        });
        //*************************************************************************
        //                      Board Size Toggle
        //************************************************************************

        groupBoardSize.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                RadioButton check = (RadioButton) new_toggle.getToggleGroup().getSelectedToggle();

                if (check.getText().equalsIgnoreCase("15x15")) {
                    selectedBoardSize = 15;

                } else if (check.getText().equalsIgnoreCase("12x12")) {
                    selectedBoardSize = 12;
                }
            }
        });

        //*************************************************************************
        //                   Scene & Stage  
        //************************************************************************
        layout.getChildren().addAll(labelColor, rbWhite, rbBlack, labelTurn,
                rbPlayer, rbOpponent, labelBoardSize, rbBoardSize15x15, rbBoardSize12x12,
                labelopponent, rbAI, rbPlayer2);
        windowLayout.setCenter(layout);

        Scene scene = new Scene(windowLayout);

        newGameWindow.setScene(scene);
        newGameWindow.setResizable(false);
        newGameWindow.showAndWait();

    }

    /**
     * 
     * @return the selected boardsize (15x15) or (12x12)
     */
    public int getBoardSize() {
        return selectedBoardSize;
    }

    /**
     * 
     * @return returns the choosen color of a player
     */
    public PieceValueEnum getSelectedColor() {
        return selectedColor;
    }

    /**
     * 
     * @return returns true or false depending on which player to make the first move 
     */
    public boolean getWhoGoesFirst() {
        return whoGoesFirst;
    }

    /**
     * 
     * @return return the type of opponent (true = Ai), (false = Humanplayer)
     */
    public boolean getOpponent() {
        return opponent;
    }
}

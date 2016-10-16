package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.LuffaSchackController;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import jdk.nashorn.internal.parser.TokenType;
import model.PieceValueEnum;

/**
 *
 * @author Krist
 */
public class BoardWindow extends BorderPane {

    private Label player1Label, opponentLabel;
    private LuffaSchackController luffaSchackController;
    private GridPane gameBoard;
    private Point2D coordinate;
    private VBox playerBox, aiBox;
    private Circle gameSymbolP1, gameSymbolP2;
    private Line winningLine;
    private Menu fileMenu, helpMenu;

    public BoardWindow() {
        this.coordinate = null;
    }

    public void setController(LuffaSchackController luffaSchackController) {
        this.luffaSchackController = luffaSchackController;
    }

    public void initView() {

        gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(10, 1, 1, 10));
        gameBoard.setGridLinesVisible(true);

        //*********************************************************************
        //                      Label & Circle Code
        //*********************************************************************
        gameSymbolP1 = new Circle(20, Color.TRANSPARENT);
        gameSymbolP2 = new Circle(20, Color.TRANSPARENT);

        player1Label = (new Label("Player1"));
        player1Label.setFont(new Font("Arial", 30));
        opponentLabel = (new Label("Opponent"));
        opponentLabel.setFont(new Font("Arial", 30));

        playerBox = new VBox(100);
        playerBox.setPadding(new Insets(50, 50, 50, 50));
        playerBox.setAlignment(Pos.CENTER);
        playerBox.getChildren().addAll(player1Label, gameSymbolP1);

        aiBox = new VBox(100);
        aiBox.setPadding(new Insets(50, 50, 50, 50));
        aiBox.setAlignment(Pos.CENTER);
        aiBox.getChildren().addAll(opponentLabel, gameSymbolP2);

        //**********************************************************************
        //                          Menu Code
        //*********************************************************************
        fileMenu = new Menu();
        fileMenu.setText("File");

        helpMenu = new Menu();
        helpMenu.setText("Help");

        MenuItem newGameItem = new MenuItem();
        newGameItem.setText("New Game");

        MenuItem pauseItem = new MenuItem();
        pauseItem.setText("Pause game");
        fileMenu.getItems().addAll(newGameItem, pauseItem);

        MenuItem aboutItem = new MenuItem();
        aboutItem.setText("About");

        MenuItem rulesItem = new MenuItem();
        rulesItem.setText("Rules");
        helpMenu.getItems().addAll(rulesItem, aboutItem);

        aboutItem.setOnAction(e -> alertWindowMenu("About", "Kicken & GrahnFän\nkristka@kth.se & erigra@kth.se"));
        rulesItem.setOnAction(e -> alertWindowMenu("Rules",
                getRules()));
        pauseItem.setOnAction(e -> alertWindowMenu("Pause", "Click OK to resume the game"));

        newGameItem.setOnAction(e -> luffaSchackController.eventHandlerMenuAction());

        MenuBar gameMenuBar = new MenuBar();
        gameMenuBar.getMenus().addAll(fileMenu, helpMenu);

        //********************************************************************
        //                      BorderPane Code
        //********************************************************************
        this.setTop(gameMenuBar);
        this.setCenter(gameBoard);
        this.setLeft(playerBox);
        this.setRight(aiBox);

    }

    public void addCircle(PieceValueEnum color, Point2D coordinate) {
        Circle circle = new Circle(20);
        if (color == PieceValueEnum.WHITE) {
            circle.setFill(Color.WHITE);

        } else if (color == PieceValueEnum.BLACK) {
            circle.setFill(Color.BLACK);
        }

        gameBoard.add(circle, (int) coordinate.getX(), (int) coordinate.getY());
        GridPane.setHalignment(circle, HPos.CENTER);
    }

    public void fillGridPane(int boardSize) {
        int noRowAndColumn = boardSize;
        gameBoard.setStyle("-fx-background-color:#006400;");

        for (int i = 0; i < noRowAndColumn; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);

            gameBoard.getColumnConstraints().add(column);
            gameBoard.getRowConstraints().add(row);
        }

        for (int i = 0; i < noRowAndColumn; i++) {
            for (int j = 0; j < noRowAndColumn; j++) {
                addPane(i, j);
            }
        }

    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();

        pane.setOnMouseClicked(e -> {
            coordinate = new Point2D(colIndex, rowIndex);
            luffaSchackController.eventHandlerPlayerMove(coordinate);
        });

        gameBoard.add(pane, colIndex, rowIndex);
    }

    public void playWinAnimation(ArrayList<Point2D> list, String winner) {
        double k, endX = 0, endY = 0;
        winningLine = new Line(list.get(0).getX(), list.get(0).getY(), list.get(0).getX(), list.get(0).getY());
        k = ((list.get(1).getY() - list.get(0).getY()) / (list.get(1).getX() - list.get(0).getX()));

        if (k == 0) {
            endX = list.get(1).getX() + 240;
            endY = list.get(1).getY();
        } else if (k == -1) {
            endX = list.get(1).getX() + 240;
            endY = list.get(1).getY() - 240;
            GridPane.setValignment(winningLine, VPos.BOTTOM);

        } else if (k == 1) {
            endX = list.get(1).getX() + 240;
            endY = list.get(1).getY() + 240;
            GridPane.setValignment(winningLine, VPos.TOP);
        } else {
            endX = list.get(1).getX();
            endY = list.get(1).getY() + 240;
            GridPane.setValignment(winningLine, VPos.BASELINE);
            GridPane.setHalignment(winningLine, HPos.CENTER);
        }

        gameBoard.setConstraints(winningLine, (int) list.get(0).getX(), (int) list.get(0).getY());
        winningLine.setStrokeWidth(10);
        winningLine.setStroke(Color.PINK);
        gameBoard.getChildren().add(winningLine);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2),
                new KeyValue(winningLine.endXProperty(), endX),
                new KeyValue(winningLine.endYProperty(), endY)));

        timeline.play();
        timeline.setOnFinished(e -> alertWindowWinner("WINNER!!!", "The Winner is: " + winner));
    }

    public void setPlayerTurnColor() {
        if (luffaSchackController.getPlayerTurn()) { // Använd GameRules istället
            playerBox.setStyle("-fx-background-color:#00FF00;");
            aiBox.setStyle("-fx-background-color: transparent;");
        } else {
            aiBox.setStyle("-fx-background-color:#00FF00;");
            playerBox.setStyle("-fx-background-color: transparent;");
        }

    }

    public void setTurnGameOver() {
        playerBox.setStyle("-fx-background-color: transparent;");
        aiBox.setStyle("-fx-background-color: transparent;");
    }

    public void addGameSymbol(PieceValueEnum color) {

        if (color == PieceValueEnum.WHITE) {
            gameSymbolP1.setFill(Color.WHITE);
            gameSymbolP2.setFill(Color.BLACK);

        } else if (color == PieceValueEnum.BLACK) {
            gameSymbolP1.setFill(Color.BLACK);
            gameSymbolP2.setFill(Color.WHITE);
        }

    }

    public void alertWindowMenu(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("LuffaSchack");
        alert.setContentText(content);
        alert.show();

    }

    public void alertWindowWinner(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private String getRules() {
        String rules = "Luffarschack is a board game for two players who take turns in putting "
                + "black and white stones on the board. Each players' goal is to"
                + " create an unbroken row of five stones horizontally, vertically,"
                + " or diagonally. The size of the board can be 15x15 or 12x12 squares"
                + "and the color of the stones is black and white.\n" + "The game ends as a draw "
                + "if the board is fully covered with stones and none of the players "
                + "has managed to get five in a row. A player who makes an"
                + " unbroken row of exactly five stones in any direction "
                + "wins the game";

        return rules;
    }

    /**
     * @param player2Label the player2Label to set
     */
    public void setOpponentLabel(String opponent) {
        this.opponentLabel.setText(opponent);
    }

}

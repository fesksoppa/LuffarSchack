
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
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.PieceValueEnum;

/**
 *
 * @author Krist 
 */
public class BoardWindow {
        
        private Stage primaryStage;
        private Label playerLabel, aiLabel;
        private LuffaSchackController luffaSchackController;
        private GridPane gameBoard;
        private Point2D coordinate;
        private HBox playerBox,aiBox;
     public BoardWindow(Stage primaStage, LuffaSchackController luffaSchackController){
         this.luffaSchackController=luffaSchackController;
         this.primaryStage=primaStage;
         this.coordinate=null;
     }
     
     public void initView(){
         
        BorderPane theGame = new BorderPane();
        gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(10,1,1,10));
        fillGridPane();
        gameBoard.setGridLinesVisible(true);
       
         
        
        playerLabel=new Label("Player");
        playerLabel.setFont(new Font("Arial", 30));
        aiLabel = new Label("Player");
        aiLabel.setFont(new Font("Arial", 30));
        
        playerBox = new HBox(100);
        playerBox.setPadding(new Insets(50,50,50,50));
        playerBox.setAlignment(Pos.CENTER);
        playerBox.getChildren().add(playerLabel);
        
        
        aiBox = new HBox(100);
        aiBox.setPadding(new Insets(50,50,50,50));
        aiBox.setAlignment(Pos.CENTER);
        aiBox.getChildren().add(aiLabel);
        
        Menu fileMenu = new Menu();
        fileMenu.setText("File");
        
        MenuItem newGameItem = new MenuItem();
        newGameItem.setText("New Game");
        fileMenu.getItems().add(newGameItem);
        
        newGameItem.setOnAction(e ->luffaSchackController.eventHandlerMenuAction());
        
        MenuBar gameMenuBar = new MenuBar();
        gameMenuBar.getMenus().add(fileMenu);
        
        
        
        theGame.setTop(gameMenuBar);
        theGame.setCenter(gameBoard);
        theGame.setLeft(playerBox);
        theGame.setRight(aiBox);
        
        Scene scen1 = new Scene(theGame);
        
        primaryStage.setScene(scen1);
        primaryStage.setResizable(false);
        primaryStage.show();
           
        
    }
     
     public void addCircle(PieceValueEnum color, Point2D coordinate){
         Circle circle = new Circle(20);
         if(color == PieceValueEnum.WHITE){
             circle.setFill(Color.WHITE);
         }
         else if(color==PieceValueEnum.BLACK){
            circle.setFill(Color.BLACK);
         }
        
        gameBoard.add(circle,(int)coordinate.getX(), (int)coordinate.getY());
        GridPane.setHalignment(circle, HPos.CENTER);  
     }
     
    public void fillGridPane(){
        int noRowAndColumn = 15;
        
        gameBoard.setStyle("-fx-background-color:#006400;");
        
        
        for(int i=0 ; i<noRowAndColumn;i++){
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            
            gameBoard.getColumnConstraints().add(column);
            gameBoard.getRowConstraints().add(row);
        }  
        
        for(int i=0; i<noRowAndColumn; i++){
            for(int j=0; j<noRowAndColumn;j++){
                addPane(i,j);
            }
        }
        
    }
    
    private void addPane(int colIndex, int rowIndex){
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            
          coordinate = new Point2D(colIndex, rowIndex);
          luffaSchackController.eventHandlerPlayerMove(coordinate);
        });
        
        
        gameBoard.add(pane, colIndex, rowIndex);
        
       
    }
    
    public void addGameSymbol(PieceValueEnum color){
        Circle gameSymbolP1 = new Circle(20);
        Circle gameSymbolP2 = new Circle(20);
        
        if(color == PieceValueEnum.WHITE){
             gameSymbolP1.setFill(Color.WHITE);
             gameSymbolP2.setFill(Color.BLACK);
             
         }
         else if(color==PieceValueEnum.BLACK){
            gameSymbolP1.setFill(Color.BLACK);
            gameSymbolP2.setFill(Color.WHITE);
         }
        playerBox.getChildren().add(gameSymbolP1);
        aiBox.getChildren().add(gameSymbolP2);
        
    }
     
     
}

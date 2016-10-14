
package view;

import java.util.Observable;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.LuffaSchackController;
import model.PieceValueEnum;

/**
 *
 * @author Krist 
 */
    public class NewGameWindow {
        private PieceValueEnum selectedColor; 
        private boolean whoGoesFirst;
        private LuffaSchackController luffaSchackController;
        private int selectedBoardSize;
        
     public NewGameWindow(LuffaSchackController luffaSchackController){
         this.luffaSchackController=luffaSchackController;
     } 
    
   public void display(){
       Stage newGameWindow = new Stage();
       newGameWindow.initModality(Modality.APPLICATION_MODAL);
       newGameWindow.setTitle("New Game");
       
       BorderPane windowLayout = new BorderPane();
       
       Label labelColor = new Label("Select color");
       Label labelTurn = new Label("\nSelect player to begin");
       Label labelBoardSize = new Label("\nSelect board size");
       
       Button ok = new Button("OK");
       ok.setOnAction(e ->{
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
       buttonMenu.setPadding(new Insets(30, 10,10, 10));
       buttonMenu.setAlignment(Pos.CENTER);
       
       
       buttonMenu.getChildren().addAll(ok, cancel);
       
       windowLayout.setBottom(buttonMenu);
       //********************************************************************
      //              Creating of RadioButtons
      //*********************************************************
      
       selectedColor=PieceValueEnum.WHITE; // default value
       whoGoesFirst=true; 
       selectedBoardSize=15;
       
       ToggleGroup groupColor = new ToggleGroup();
       ToggleGroup groupWhoGoesFirst = new ToggleGroup();
       ToggleGroup groupBoardSize = new ToggleGroup();
       
       RadioButton rbWhite = new RadioButton("White");
       RadioButton rbBlack = new RadioButton("Black");
       RadioButton rbPlayer = new RadioButton("Player1");
       RadioButton rbPlayer2 = new RadioButton("Player2");
       RadioButton rbBoardSize15x15 = new RadioButton("15x15");
       RadioButton rbBoardSize12x12 = new RadioButton("12x12");
       
       rbWhite.setToggleGroup(groupColor);
       rbWhite.setSelected(true);
       rbBlack.setToggleGroup(groupColor);
       
       rbPlayer.setToggleGroup(groupWhoGoesFirst);
       rbPlayer.setSelected(true);
       rbPlayer2.setToggleGroup(groupWhoGoesFirst);
       
       rbBoardSize15x15.setToggleGroup(groupBoardSize);
       rbBoardSize15x15.setSelected(true);
       rbBoardSize12x12.setToggleGroup(groupBoardSize);
   
         
     groupColor.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
         
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle){
          RadioButton check= (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
                
                if(check.getText().equalsIgnoreCase("White")){
                    selectedColor=PieceValueEnum.WHITE;
                    
                }
                else if(check.getText().equalsIgnoreCase("Black")){
                    selectedColor=PieceValueEnum.BLACK; 
                    
                }
            }
        });
//***************************************************************************
     groupWhoGoesFirst.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
         
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle){
          RadioButton check= (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
                
                if(check.getText().equalsIgnoreCase("Player1")){
                    whoGoesFirst=true;
                    
                }
                else if(check.getText().equalsIgnoreCase("Player2")){
                   whoGoesFirst=false;
                    
                }
            }
        });
     //*************************************************************************
     groupBoardSize.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
         
      @Override
      public void changed(ObservableValue<? extends Toggle> ov,Toggle old_toggle, Toggle new_toggle){
          RadioButton check= (RadioButton)new_toggle.getToggleGroup().getSelectedToggle();
                
                if(check.getText().equalsIgnoreCase("15x15")){
                    selectedBoardSize=15;
                    
                }
                else if(check.getText().equalsIgnoreCase("12x12")){
                    selectedBoardSize=12; 
                }
            }
        });
     
     
     
     
       //************************************************************
       layout.getChildren().addAll(labelColor, rbWhite,rbBlack, labelTurn,
               rbPlayer, rbPlayer2,labelBoardSize,rbBoardSize15x15,rbBoardSize12x12);
       windowLayout.setCenter(layout);
       
       Scene scene = new Scene(windowLayout);
       
       newGameWindow.setScene(scene);
       newGameWindow.setResizable(false);
       newGameWindow.showAndWait();
       
       
   } 
   public int getBoardSize(){
       return selectedBoardSize; 
   }
   
   public PieceValueEnum getSelectedColor(){
        return selectedColor; 
   }
   
   public boolean getWhoGoesFirst(){
       return whoGoesFirst; 
   }
}


package controller;

import javafx.geometry.Point2D;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import view.BoardWindow;
import view.NewGameWindow;
import model.GameRules;
import model.PieceValueEnum;

/**
 *
 * @author Krist 
 */
public class LuffaSchackController {
       
    //Är tvungen att testa att pusha :D :D
        
        private boolean gameOver;
        private BoardWindow boardWindow;
        private NewGameWindow newGameWindow;
        private GameRules gameRules;
        
    
    public LuffaSchackController(Stage primaryStage){
        boardWindow = new BoardWindow(primaryStage, this);
        newGameWindow= new NewGameWindow(this); 
         gameRules= new GameRules(15,15);
        
        this.gameOver=false; 
        initGame();
        
    }
    
    
    public void initGame(){
        boardWindow.initView();
        
    }
    
    public void Aiturn(){
        
        
    }
    
    public void setGameOver(){
        
    }
    
    public void eventHandlerMenuAction(){
        newGameWindow.display();
        //gameRules.resetBoard();
        boardWindow.initView();
  
    }
    
    public void eventHandlerPlayerMove(Point2D coordinate){
    
        if(newGameWindow.getSelectedColor()!= null && gameRules.setPlayerMove(coordinate)){
            boardWindow.addCircle(gameRules.getPlayerColor(), coordinate);
            
        }
        
    }
    
    public void eventHandlerOkButton(){
        gameRules.resetBoard();
        boardWindow.addGameSymbol(newGameWindow.getSelectedColor());
        
        
        if(newGameWindow.getSelectedColor().ordinal()==1){
            gameRules.createPlayer(PieceValueEnum.WHITE, newGameWindow.getWhoGoesFirst());
            gameRules.createPlayer(PieceValueEnum.BLACK,!newGameWindow.getWhoGoesFirst());
        }
        else if(newGameWindow.getSelectedColor().ordinal()==2 ){
           gameRules.createPlayer(PieceValueEnum.BLACK, newGameWindow.getWhoGoesFirst());
           gameRules.createPlayer(PieceValueEnum.WHITE,!newGameWindow.getWhoGoesFirst()); 
        }
        
        
       //boardWindow.addCircle(newGameWindow.getSelectedColor());
        
    }
}


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
      //  private boolean gameOver;
        private BoardWindow boardWindow;
        private NewGameWindow newGameWindow;
        private GameRules gameRules;
        
    
    public LuffaSchackController(BoardWindow boardWindow, NewGameWindow newGameWindow){
       this.boardWindow =boardWindow; 
       this.newGameWindow=newGameWindow; 
       boardWindow.setController(this);
       newGameWindow.setController(this);
       initGame();
        
    }
    
    public void initGame(){
        boardWindow.initView();
        
    }
    
    public boolean getPlayerTurn(){
        return gameRules.getPlayerTurn();
        
    }
    
    public void setGameOver(){
        if(gameRules.getWhiteWin()){
            System.out.println("HEJPÅDIG");
            boardWindow.playWinAnimation(gameRules.getWinningCoordinates(),"White");
            boardWindow.setTurnGameOver();
           
        }
        else if(gameRules.getBlackWin()){
            boardWindow.playWinAnimation(gameRules.getWinningCoordinates(), "Black");
            boardWindow.setTurnGameOver();
        }
        else if(gameRules.checkDraw()){
            boardWindow.alertWindowWinner("DRAW!!!!" ,"Draw");
            boardWindow.setTurnGameOver();
            
        }
        
    }
    
    public void eventHandlerMenuAction(){
        newGameWindow.display();

    }
    
    public void eventHandlerPlayerMove(Point2D coordinate){
      
        if(newGameWindow.getSelectedColor()!= null && gameRules.setPlayerMove(coordinate)){
            boardWindow.addCircle(gameRules.getPlayerColor(), coordinate);
            boardWindow.setPlayerTurnColor();
            setGameOver();
            
        } 
       // System.out.println(gameRules.SetAIMove());
         if(gameRules.aiExcists() && !gameRules.getBlackWin() && !gameRules.getWhiteWin()){
            Point2D aiMove = new Point2D(10,10); //WHAT?!
            aiMove=gameRules.SetAIMove();
            boardWindow.addCircle(gameRules.getPlayerColor(),aiMove);
            boardWindow.setPlayerTurnColor(); 
            setGameOver();
         }
         
    }
    
    // creates gameRules and players 
    
    public void eventHandlerOkButton(){
        
        boardWindow.initView();// SKapar Grafisk spelplan
        
        boardWindow.addGameSymbol(newGameWindow.getSelectedColor()); //säter färg på symbol
        
        gameRules= new GameRules(newGameWindow.getBoardSize(),
                newGameWindow.getBoardSize()); // skapar logisk spelplan
        
        gameRules.resetBoard(); // resetar spelplan,spelare och ai. 
        
        
        
        if(newGameWindow.getSelectedColor().ordinal()==1){
            
            gameRules.createPlayer(PieceValueEnum.WHITE, newGameWindow.getWhoGoesFirst());
            
            if(newGameWindow.getOpponent()){
                gameRules.createAi(PieceValueEnum.BLACK, !newGameWindow.getWhoGoesFirst());
            }
            else
                gameRules.createPlayer(PieceValueEnum.BLACK,!newGameWindow.getWhoGoesFirst());
            
        }
        else if(newGameWindow.getSelectedColor().ordinal()==2 ){
           gameRules.createPlayer(PieceValueEnum.BLACK, newGameWindow.getWhoGoesFirst());
           
           if(newGameWindow.getOpponent()){
               gameRules.createAi(PieceValueEnum.WHITE, !newGameWindow.getWhoGoesFirst());
           }
           else
                gameRules.createPlayer(PieceValueEnum.WHITE,!newGameWindow.getWhoGoesFirst()); 
        }
        
        boardWindow.setPlayerTurnColor();
        
        boardWindow.fillGridPane(newGameWindow.getBoardSize());
        
        
    }
}

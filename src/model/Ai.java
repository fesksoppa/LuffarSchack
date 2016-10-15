/*
 * Copyright 2016
 */
package model;

import javafx.geometry.Point2D;
import view.BoardWindow;

/**
 *
 * @author Erik
 */
public class Ai {
    
    private boolean turn;
    private PieceValueEnum circleColor;
    private int [][] board;
    private GameRules gameRules;
    private Point2D firstWinningCoordinate,lastWinningCoordinate;
    private boolean blackWin, whiteWin;
    private boolean gameOver;
    private Point2D aiMove;
    
    
    public Ai(PieceValueEnum circleColor,boolean turn, GameRules gameRules)
    {
        this.gameRules = gameRules;
        this.turn = turn;
        this.circleColor = circleColor;
        board = this.gameRules.getBoard();           
    }
    
    public boolean getAiTurn()
    {
        return turn;
    }
    
    public PieceValueEnum getCircleColor()
    {
        return circleColor;
    }
    
    public void setAiTurn()
    {
        this.turn = !this.turn;
    }
    
    public Point2D aiLogic()
    {
        boolean testBreak = false; 
        
        for(int i = 0; i <board.length; i++)
        {
            if (testBreak) break; 
            
            for(int j = 0; j < board.length; j++)
            {
             if(board[j][i] == PieceValueEnum.EMPTY.ordinal())
             {
                 // Horizpntal Ai Win checking right
                 if( j<board.length-4 && board[j+1][i] == circleColor.ordinal() && board[j+2][i] == circleColor.ordinal() &&
                    board[j+3][i] == circleColor.ordinal() && board[j+4][i] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     
                     break;
                 } // Horizontal AI win checking left
                else if( (j>3 && j<board.length) && board[j-1][i] == circleColor.ordinal() && board[j-2][i] == circleColor.ordinal() &&
                    board[j-3][i] == circleColor.ordinal() && board[j-4][i] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     break;
                 }
                 // Vertical AI win checking down 
                else if( i<board.length-4 && board[j][i+1] == circleColor.ordinal() && board[j][i+2] == circleColor.ordinal() &&
                    board[j][i+3] == circleColor.ordinal() && board[j][i+4] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     
                     break;
                 } // Horizontal AI win checking up
                else if( (i>3 && j<board.length) && board[j][i-1] == circleColor.ordinal() && board[j][i-2] == circleColor.ordinal() &&
                    board[j][i-3] == circleColor.ordinal() && board[j][i-4] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     break;
                 }
                  // Up rising AI win checking right 
                else if(( i>3 && j<board.length-4) && board[j+1][i-1] == circleColor.ordinal() && board[j+2][i-2] == circleColor.ordinal() &&
                    board[j+3][i-3] == circleColor.ordinal() && board[j+4][i-4] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     
                     break;
                 } // Up rising AI win checking left
                else if( (j>3 && i<board.length-4) && board[j-1][i+1] == circleColor.ordinal() && board[j-2][i+2] == circleColor.ordinal() &&
                    board[j-3][i+3] == circleColor.ordinal() && board[j-4][i+4] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
                      System.out.println(aiMove.toString());
                     testBreak=true; 
                     break;
                 }
                 
             }
                
                
            }
            
        }
        
       
        
        return aiMove;
    }
    
    
    @Override
    public String toString()
    {
        String info="Player: " +circleColor+" & "+ turn; 
        return info;
    }
    
}

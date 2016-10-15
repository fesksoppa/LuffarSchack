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
    
    
    public Ai(boolean turn, PieceValueEnum circleColor)
    {
        
        this.turn = turn;
        this.circleColor = circleColor;
        board = gameRules.getBoard();           
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
        
        for(int i = 0; i <board.length; i++)
        {
            
            for(int j = 0; j < board.length; j++)
            {
             if(board[j][i] == PieceValueEnum.EMPTY.ordinal())
             {
                 if(board[j+1][i] == circleColor.ordinal() && board[j+2][i] == circleColor.ordinal() &&
                    board[j+3][i] == circleColor.ordinal() && board[j+4][i] == circleColor.ordinal())
                 {
                     aiMove = new Point2D(j, i);
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

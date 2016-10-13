
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javax.swing.text.html.CSS;

/**
 *
 * @author Krist 
 */
public class GameRules {
    private int[][] board;
    private boolean gameOver;
    private final int boardWidth;
    private final int boardHeight;
    private Player player1,player2;
    private boolean blackWin, whiteWin;
    private Point2D firstWinningCoordinate,lastWinningCoordinate;
    
    public GameRules(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        blackWin=false;
        whiteWin=false;
        gameOver=false; 
        
        board=new int[this.boardWidth][this.boardHeight];
         
    }
    
    //gör en till konstruktor med AI som in
    
    public boolean setPlayerMove(Point2D coordinate)
    {
        if(validMove(coordinate) && !gameOver){
            
        
        
            if(player1.getPlayerTurn())
            {
              board[(int)coordinate.getX()][(int)coordinate.getY()]=player1.getCircleColor().ordinal();
                setTurn();
            }
            else if(player2.getPlayerTurn()){
                board[(int)coordinate.getX()][(int)coordinate.getY()]=player2.getCircleColor().ordinal();  
                setTurn();
            }


            checkWin();
            return true;
        }    
        return false; 
    }
    
    public PieceValueEnum getPlayerColor(){
        if(!player1.getPlayerTurn()){
           return player1.getCircleColor();
        }
        else if(!player2.getPlayerTurn())
        {
            return player2.getCircleColor();
        }
        else 
            return null; 
    }
    
    private void setTurn(){
        player1.setPlayerTurn();
        player2.setPlayerTurn();
    }
    public void createPlayer(PieceValueEnum circleColor, boolean turn){
        if(player1!=null){
            player2=new Player(circleColor, turn);
            System.out.println("2  " +player2.toString());
        }
        else{ 
            player1=new Player(circleColor, turn);
        System.out.println("1  " +player1.toString());
        }
        
    }
    
    private boolean validMove(Point2D coordinate){
 
        return board[(int)coordinate.getX()][(int)coordinate.getY()] == PieceValueEnum.EMPTY.ordinal(); 
              
    }
    
            
    
    private void checkWin(){
        
        
        int whiteCount=1, blackCount = 1; 
        
        //*****************************************************************
        //                 Check horizontal winner
        //******************************************************************
        
        for(int i=0; i<boardWidth;i++){
            if(blackCount>=5){           
               break;
            }
            else if(whiteCount >=5){  
                break;
            }
            else {
                blackCount=1;
                whiteCount=1;
            }
        for(int j=0; j<boardHeight-1; j++){
            if(board[j][i] == 1 && board[j+1][i] == 1  ){
                 whiteCount++;
                 if(whiteCount==1){
                     
                 }
            }
            else if(board[j][i] == 2 && board[j+1][i] == 2  ){
                 blackCount++;
            }
            else {
                 blackCount=1;
                 whiteCount=1;
            }
            if(blackCount>=5){
                firstWinningCoordinate =new Point2D(j-3, i);
                lastWinningCoordinate= new Point2D(j+1,i);
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                blackWin=true;
                break;
            }
                else if(whiteCount >=5){
                    firstWinningCoordinate =new Point2D(j-3, i);
                    lastWinningCoordinate= new Point2D(j+1,i);
                    System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                    whiteWin=true;
                break;
            }       
        }
        }
       
        //*****************************************************************
        //                  Check vertical winner
        //******************************************************************
        whiteCount = 1;
        blackCount =1;
        
      
        for(int i=0; i<boardWidth;i++){
            if(blackCount>=5){           
               break;
            }
            else if(whiteCount >=5){  
                break;
            }
            else {
                blackCount=1;
                whiteCount=1;
            }
            
        for(int j=0; j<boardHeight-1; j++){
            if(board[i][j] == 1 && board[i][j+1] == 1  ){
                 whiteCount++;
            }
            else if(board[i][j] == 2 && board[i][j+1] == 2  ){
                 blackCount++;
            }
            else {
                 blackCount=1;
                 whiteCount=1;
            }
            if(blackCount>=5){
               blackWin=true;
               firstWinningCoordinate =new Point2D(i, j-3);
               lastWinningCoordinate= new Point2D(i,j+1);
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                break;
            }
            else if(whiteCount >=5){
                    firstWinningCoordinate =new Point2D(i,j-3);
                    lastWinningCoordinate= new Point2D(i,j+1);
                
                    System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                    System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                    whiteWin=true;
                break;
                       
            }       
        }
        }
        
        
       //************************************************************* 
       //               Check Down Rising Winner
       //*************************************************************
       
       
        whiteCount = 1;
        blackCount =1;
        
      
        for(int i=0; i<boardWidth-4;i++){
            if(blackCount>=5){           
               break;
            }
            else if(whiteCount >=5){  
                break;
            }
        for(int j=0; j<boardHeight-4; j++){
            if(board[i][j] == 1 && board[i+1][j+1] == 1 && board[i+2][j+2] == 1
                  && board[i+3][j+3] == 1 && board[i+4][j+4] == 1 ){
                 whiteCount=5;
                 whiteWin=true;
                 
               firstWinningCoordinate =new Point2D(i, j);
               lastWinningCoordinate= new Point2D(i+4,j+4);
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                 
                 break;
            }
            else if(board[i][j] == 2 && board[i+1][j+1] == 2 && board[i+2][j+2] == 2
                  && board[i+3][j+3] == 2 && board[i+4][j+4] == 2 ){
                 blackCount=5;
                 blackWin=true;
                 
                firstWinningCoordinate =new Point2D(i, j);
                lastWinningCoordinate= new Point2D(i+4,j+4);
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                 
                 break;
            }
            }
       
        }
        
       
       //************************************************************* 
       //               Check Up Rising Winner
       //*************************************************************
        
       
        whiteCount = 1;
        blackCount =1;
        
      
        for(int i=0; i<boardWidth-4;i++){
            if(blackCount>=5){           
               break;
            }
            else if(whiteCount >=5){  
                break;
            }
        for(int j=4; j<boardHeight; j++){
            if(board[i][j] == 1 && board[i+1][j-1] == 1 && board[i+2][j-2] == 1
                  && board[i+3][j-3] == 1 && board[i+4][j-4] == 1 ){
                 whiteCount=5;
                 whiteWin=true;
                  firstWinningCoordinate =new Point2D(i, j);
                  lastWinningCoordinate= new Point2D(i+4,j-4);
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                break; 
            }
            else if(board[i][j] == 2 && board[i+1][j-1] == 2 && board[i+2][j-2] == 2
                  && board[i+3][j-3] == 2 && board[i+4][j-4] == 2 ){
                 blackCount=5;
                 blackWin=true;
                 
                firstWinningCoordinate =new Point2D(i, j);
                lastWinningCoordinate= new Point2D(i+4,j-4);
                
                
                System.out.println("First: " + firstWinningCoordinate.getX()+ "," + firstWinningCoordinate.getY());
                System.out.println("LastBlackCockt: " + lastWinningCoordinate.getX()+ "," + lastWinningCoordinate.getY());
                break;
            }
           
        }
        }
        if(whiteWin || blackWin){
            gameOver=true; 
            
        }
 
    }
    
    public ArrayList<Point2D> getWinningCoordinates(){
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        list.add(firstWinningCoordinate);
        list.add(lastWinningCoordinate);
        return list;
    }
    
    public boolean getBlackWin(){
        return blackWin;
    }
    
    public boolean getWhiteWin(){
        return whiteWin; 
    }
    
    public void resetBoard(){
        gameOver=false;
        whiteWin=false;
        blackWin=false;
        
        for(int i=0; i<boardWidth ; i++){
            for(int j=0;j<boardHeight;j++){
                board[i][j] = PieceValueEnum.EMPTY.ordinal();
            }
            
        }
        player1 = null;
        player2 = null;
    }
    
    public boolean getPlayerTurn(){
        return player1.getPlayerTurn();
    }
    
    @Override
    public String toString()
    {
        StringBuilder info = new StringBuilder();
        
        for(int i = 0; i <boardWidth;i++)
        {
                info.append("\n");
            for(int j = 0; j<boardWidth; j++)
            {
                info.append('[');
                info.append(board[j][i]);
                info.append("]     ");
            }
        
        }
        return info.toString();
    }
}

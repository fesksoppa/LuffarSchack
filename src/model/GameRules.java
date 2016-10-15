
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
    private Ai ai;
    private boolean blackWin, whiteWin;
    private Point2D firstWinningCoordinate,lastWinningCoordinate;
    private static int noOfMoves;
    
    public GameRules(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        blackWin=false;
        whiteWin=false;
        gameOver=false; 
        noOfMoves=0;
        
        board=new int[this.boardWidth][this.boardHeight];
         
    }
    
    public int getNoOfMoves(){
        return noOfMoves;
    }
    
    public Point2D SetAIMove(){
        Point2D test;
        if(ai != null && ai.getAiTurn()){
                    System.out.println(ai.getAiTurn());
                    test= ai.aiLogic();
                    board[(int)test.getX()][(int)test.getY()]=ai.getCircleColor().ordinal();
                    setTurn();
                    noOfMoves++;
                    
                    checkWin();
                    
                   return test;  
                 }
       
        return null;
    }
    
    public boolean setPlayerMove(Point2D coordinate)
    {
        //System.out.println(coordinate.getX()+ "  " + coordinate.getY());
       
        if(validMove(coordinate) && !gameOver){
            
        
                //System.out.println(player1);
            if(player1.getPlayerTurn())
            {
              board[(int)coordinate.getX()][(int)coordinate.getY()]=player1.getCircleColor().ordinal();
              setTurn();
              
            }
            else if(player2 != null && player2.getPlayerTurn()){ //player2
                board[(int)coordinate.getX()][(int)coordinate.getY()]=player2.getCircleColor().ordinal(); //player2  
                setTurn();
            }
             
               
           
            noOfMoves++;
            checkWin(); 
            
            return true;
        }    
        return false; 
    }
    
    public PieceValueEnum getPlayerColor(){
        if(!player1.getPlayerTurn()){
           return player1.getCircleColor();
        }
        else if(player2 != null && !player2.getPlayerTurn()) 
        {
            return player2.getCircleColor(); 
        }
         else if(ai != null && !ai.getAiTurn()) 
        {
            System.out.println("HEJAITURN"+ai.getAiTurn());
            System.out.println(ai.getCircleColor());
            return ai.getCircleColor(); 
        }
        else 
            return null; 
    }
    
    private void setTurn(){
        player1.setPlayerTurn();
        if(player2 != null)
            player2.setPlayerTurn();
        else
            ai.setAiTurn();
    }
    public void createPlayer(PieceValueEnum circleColor, boolean turn){
        if(player1!=null){
            player2=new Player(circleColor, turn);
            System.out.println("Player 2  " +player2.toString());
 
        }
        else{ 
            player1=new Player(circleColor, turn);
        System.out.println("Player 1  " +player1.toString());
        }
        
    }
    public void createAi(PieceValueEnum circleColor, boolean turn){
        ai = new Ai(circleColor, turn,this);
        System.out.println("AI"+ ai.toString());
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
                blackWin=true;
                break;
            }
                else if(whiteCount >=5){
                    firstWinningCoordinate =new Point2D(j-3, i);
                    lastWinningCoordinate= new Point2D(j+1,i);
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
               break;
            }
            else if(whiteCount >=5){
                firstWinningCoordinate =new Point2D(i,j-3);
                lastWinningCoordinate= new Point2D(i,j+1);
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
               break;
            }
            else if(board[i][j] == 2 && board[i+1][j+1] == 2 && board[i+2][j+2] == 2
                && board[i+3][j+3] == 2 && board[i+4][j+4] == 2 ){
                blackCount=5;
                blackWin=true;
                firstWinningCoordinate =new Point2D(i, j);
                lastWinningCoordinate= new Point2D(i+4,j+4);
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
                break; 
            }
            else if(board[i][j] == 2 && board[i+1][j-1] == 2 && board[i+2][j-2] == 2
                && board[i+3][j-3] == 2 && board[i+4][j-4] == 2 ){
                blackCount=5;
                blackWin=true;
                firstWinningCoordinate =new Point2D(i, j);
                lastWinningCoordinate= new Point2D(i+4,j-4);
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
    
    public int [][] getBoard()
    {
        return board.clone();
    }
    
    public boolean getBlackWin(){
        return blackWin;
    }
    
    public boolean getWhiteWin(){
        return whiteWin; 
    }
    
    public boolean checkDraw(){
        
        return noOfMoves == boardHeight*boardWidth; 
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
        ai = null;
        
    }
    
    public boolean getPlayerTurn(){
        return player1.getPlayerTurn();
    }
    public boolean aiExcists(){
        return ai != null;
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

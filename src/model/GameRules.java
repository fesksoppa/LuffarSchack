package model;

import java.util.ArrayList;
import javafx.geometry.Point2D;


/**
 * The class GameRules represents the game logic for the boardgame five in a row.
 * 
 * It can handle both player vs player or player vs Ai (Computer).
 * 
 */
public class GameRules {

    private int[][] board;
    private boolean gameOver;
    private final int boardWidth;
    private final int boardHeight;
    private Player player1, player2;
    private Ai ai;
    private boolean blackWin, whiteWin;
    private Point2D firstWinningCoordinate, lastWinningCoordinate;
    private static int noOfMoves;
/**
 * Creates the logic gameboard with the given parameters. 
 * Initializes blackWin,WhiteWin and gameOver to false and sets noOfMove to 0. 
 * @param boardWidth 
 * @param boardHeight 
 */
    public GameRules(int boardWidth, int boardHeight) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        blackWin = false;
        whiteWin = false;
        gameOver = false;
        noOfMoves = 0;
        board = new int[this.boardWidth][this.boardHeight];

    }
    /**
     * returns the value of noOfMoves
     * @return int representing the noOfMoves
     */

    public int getNoOfMoves() {
        return noOfMoves;
    }
    /**
     * Checks if an ai exists and if it is the ais turn to make a move. 
     * If the check above equals to true, the nOOfMoves will be incremented, the turn will change and the method will return a Point2D representing
     * the coordinate of the AImove.
     * If the check equals to false, the method will return null. 
     * @return coordinate for Ai move, or null.
     */

    public Point2D SetAIMove() {
        Point2D test;
        if (ai != null && ai.getAiTurn()) {
            test = ai.aiLogic();
            board[(int) test.getX()][(int) test.getY()] = ai.getCircleColor().ordinal();
            setTurn();
            noOfMoves++;

            checkWin();

            return test;
        }

        return null;
    }
    /**
     * Checks if the given coordinate represents a valid move and that gameOver is false.
     * If so, the move will be placed on the gameBoard,the turn will change and returns true.
     * 
     * @param coordinate playermove
     * @return true,if valid move and not game over. Otherwise false. 
     */

    public boolean setPlayerMove(Point2D coordinate) {

        if (validMove(coordinate) && !gameOver) {

            if (player1.getPlayerTurn()) {
                board[(int) coordinate.getX()][(int) coordinate.getY()] = player1.getCircleColor().ordinal();
                setTurn();

            } else if (player2 != null && player2.getPlayerTurn()) { 
                board[(int) coordinate.getX()][(int) coordinate.getY()] = player2.getCircleColor().ordinal(); 
                setTurn();
            }

            noOfMoves++;
            checkWin();

            return true;
        }
        return false;
    }
    /** Returns the color of player who made the last move. 
     *  The color is represented as a PieceValueEnum
     * @return piece color
     */

    public PieceValueEnum getPlayerColor() {
        if (!player1.getPlayerTurn()) {
            return player1.getCircleColor();
        } else if (player2 != null && !player2.getPlayerTurn()) {
            return player2.getCircleColor();
        } else if (ai != null && !ai.getAiTurn()) {
            return ai.getCircleColor();
        } else {
            return null;
        }
    }
    /**
     * Changes the turn of the players.
     */

    private void setTurn() {
        player1.setPlayerTurn();
        if (player2 != null) {
            player2.setPlayerTurn();
        } else {
            ai.setAiTurn();
        }
    }
    /**
     * Creates a human player
     * @param circleColor Pieace color 
     * @param turn true if player should start, otherwise false.
     */

    public void createPlayer(PieceValueEnum circleColor, boolean turn) {
        if (player1 != null) {
            player2 = new Player(circleColor, turn);

        } else {
            player1 = new Player(circleColor, turn);
        }

    }
    /**
    * Creates ai
    * @param circleColor Pieace color 
    * @param turn true if the ai should start, otherwise false.
    */
    public void createAi(PieceValueEnum circleColor, boolean turn) {
        ai = new Ai(circleColor, turn, this);
    }
    /**
     * Checks if the gameBoard is empty (0) at the given coordinate. 
     * @param coordinate 
     * @return true if board coordinate is empty, otherwise false
     */
    private boolean validMove(Point2D coordinate) {
        return board[(int) coordinate.getX()][(int) coordinate.getY()] == PieceValueEnum.EMPTY.ordinal();
    }
    /**
     * The logic to check if anyone has won. If white/black has five in a row,
     * whiteWin/blackWin and gameOver will be set to true and the first/last coordinate of the winning move
     * will be placed in firstWinningCoordinate/lastWinningCoordinate. 
     */
    private void checkWin() {
        int whiteCount = 1, blackCount = 1;

        //*****************************************************************
        //                 Check horizontal winner
        //******************************************************************
        for (int i = 0; i < boardWidth; i++) {
            if (blackCount >= 5) {
                break;
            } else if (whiteCount >= 5) {
                break;
            } else {
                blackCount = 1;
                whiteCount = 1;
            }
            for (int j = 0; j < boardHeight - 1; j++) {
                if (board[j][i] == 1 && board[j + 1][i] == 1) {
                    whiteCount++;
                } else if (board[j][i] == 2 && board[j + 1][i] == 2) {
                    blackCount++;
                } else {
                    blackCount = 1;
                    whiteCount = 1;
                }
                if (blackCount >= 5) {
                    firstWinningCoordinate = new Point2D(j - 3, i);
                    lastWinningCoordinate = new Point2D(j + 1, i);
                    blackWin = true;
                    break;
                } else if (whiteCount >= 5) {
                    firstWinningCoordinate = new Point2D(j - 3, i);
                    lastWinningCoordinate = new Point2D(j + 1, i);
                    whiteWin = true;
                    break;
                }
            }
        }

        //*****************************************************************
        //                  Check vertical winner
        //******************************************************************
        whiteCount = 1;
        blackCount = 1;
        for (int i = 0; i < boardWidth; i++) {
            if (blackCount >= 5) {
                break;
            } else if (whiteCount >= 5) {
                break;
            } else {
                blackCount = 1;
                whiteCount = 1;
            }

            for (int j = 0; j < boardHeight - 1; j++) {
                if (board[i][j] == 1 && board[i][j + 1] == 1) {
                    whiteCount++;
                } else if (board[i][j] == 2 && board[i][j + 1] == 2) {
                    blackCount++;
                } else {
                    blackCount = 1;
                    whiteCount = 1;
                }
                if (blackCount >= 5) {
                    blackWin = true;
                    firstWinningCoordinate = new Point2D(i, j - 3);
                    lastWinningCoordinate = new Point2D(i, j + 1);
                    break;
                } else if (whiteCount >= 5) {
                    firstWinningCoordinate = new Point2D(i, j - 3);
                    lastWinningCoordinate = new Point2D(i, j + 1);
                    whiteWin = true;
                    break;

                }
            }
        }

        //************************************************************* 
        //               Check Down Rising Winner
        //*************************************************************
        whiteCount = 1;
        blackCount = 1;
        for (int i = 0; i < boardWidth - 4; i++) {
            if (blackCount >= 5) {
                break;
            } else if (whiteCount >= 5) {
                break;
            }
            for (int j = 0; j < boardHeight - 4; j++) {
                if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1
                        && board[i + 3][j + 3] == 1 && board[i + 4][j + 4] == 1) {
                    whiteCount = 5;
                    whiteWin = true;
                    firstWinningCoordinate = new Point2D(i, j);
                    lastWinningCoordinate = new Point2D(i + 4, j + 4);
                    break;
                } else if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2
                        && board[i + 3][j + 3] == 2 && board[i + 4][j + 4] == 2) {
                    blackCount = 5;
                    blackWin = true;
                    firstWinningCoordinate = new Point2D(i, j);
                    lastWinningCoordinate = new Point2D(i + 4, j + 4);
                    break;
                }
            }
        }
        //************************************************************* 
        //               Check Up Rising Winner
        //*************************************************************
        whiteCount = 1;
        blackCount = 1;
        for (int i = 0; i < boardWidth - 4; i++) {
            if (blackCount >= 5) {
                break;
            } else if (whiteCount >= 5) {
                break;
            }
            for (int j = 4; j < boardHeight; j++) {
                if (board[i][j] == 1 && board[i + 1][j - 1] == 1 && board[i + 2][j - 2] == 1
                        && board[i + 3][j - 3] == 1 && board[i + 4][j - 4] == 1) {
                    whiteCount = 5;
                    whiteWin = true;
                    firstWinningCoordinate = new Point2D(i, j);
                    lastWinningCoordinate = new Point2D(i + 4, j - 4);
                    break;
                } else if (board[i][j] == 2 && board[i + 1][j - 1] == 2 && board[i + 2][j - 2] == 2
                        && board[i + 3][j - 3] == 2 && board[i + 4][j - 4] == 2) {
                    blackCount = 5;
                    blackWin = true;
                    firstWinningCoordinate = new Point2D(i, j);
                    lastWinningCoordinate = new Point2D(i + 4, j - 4);
                    break;
                }

            }
        }
        if (whiteWin || blackWin) {
            gameOver = true;

        }

    }
    /**
     * Returns the first and last coordinate of the winning move.
     * The first coordinate will be placed first in the returned ArrayList. 
     * @return firstWinningCoordinate, lastWinningCoordinate
     */
    public ArrayList<Point2D> getWinningCoordinates() {
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        list.add(firstWinningCoordinate);
        list.add(lastWinningCoordinate);
        return list;
    }
    /**
     * Returns a clone of the board.
     * @return board
     */
    public int[][] getBoard() {
        return board.clone();
    }
    /**
     * Returns true if black has five in a row
     * @return blackWin 
     */

    public boolean getBlackWin() {
        return blackWin;
    }
    /**
     * Returns true if white has five in a row
     * @return whiteWin
     */
    public boolean getWhiteWin() {
        return whiteWin;
    }
    /**
     * returns true if the board is filled 
     * @return true if board is filled, false if not
     */
    public boolean checkDraw() {
        return noOfMoves == boardHeight * boardWidth;
    }
    /**
     * Resets the game.
     * Sets gameOver,whiteWin and blackWin to false.
     * Sets player(s) and ai to null.
     * Sets the board to emprty (0).
     */

    public void resetBoard() {
        gameOver = false;
        whiteWin = false;
        blackWin = false;

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                board[i][j] = PieceValueEnum.EMPTY.ordinal();
            }

        }
        player1 = null;
        player2 = null;
        ai = null;

    }
    /**
     * Retuns the turn for the player.
     * If true, its the Players turn.
     * @return Player turn
     */

    public boolean getPlayerTurn() {
        return player1.getPlayerTurn();
    }
    /**
     * Returns true if an Ai object is created. 
     * @return 
     */
    public boolean aiExcists() {
        return ai != null;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();

        for (int i = 0; i < boardWidth; i++) {
            info.append("\n");
            for (int j = 0; j < boardWidth; j++) {
                info.append('[');
                info.append(board[j][i]);
                info.append("]     ");
            }

        }
        return info.toString();
    }
}

/*
 * Copyright 2016
 */
package model;

import javafx.geometry.Point2D;

/**
 *
 * @author Erik
 */
public class Ai {

    private boolean turn;
    private PieceValueEnum circleColor;
    private int[][] board;
    private GameRules gameRules;
    private Point2D aiMove;
    private int opponentColor;

    public Ai(PieceValueEnum circleColor, boolean turn, GameRules gameRules) {
        this.gameRules = gameRules;
        this.turn = turn;
        this.circleColor = circleColor;
        board = this.gameRules.getBoard();

        if (this.circleColor.ordinal() == 1) {
            opponentColor = 2;
        } else {
            opponentColor = 1;
        }

    }

    public boolean getAiTurn() {
        return turn;
    }

    public PieceValueEnum getCircleColor() {
        return circleColor;
    }

    public void setAiTurn() {
        this.turn = !this.turn;
    }

    public Point2D aiLogic() {
        int counter = 1;
        this.aiMove = new Point2D(100, 100); // Kan vi ändra? 

        while (this.aiMove.getX() == 100 && this.aiMove.getY() == 100) {
            Point2D temp = new Point2D(Math.random() * 14, Math.random() * 14);
            switch (counter) {
                case 1:
                    this.aiMove = aiCheckWin();
                    counter++;
                    break;
                case 2:
                    aiCheckLose();
                    counter++;
                    break;
                case 3:
                    aiCheck3InARowAI();
                    counter++;
                    break;
                case 4:
                    aiCheck3InARowPlayer();
                    counter++;
                    break;
                case 5:
                    aiCheck2InARowAI();
                    counter++;
                    break;
                case 6:
                    aiBehaveLikaAHuman();
                    counter++;
                    break;

                default:
                    if (board[(int) temp.getX()][(int) temp.getY()] == PieceValueEnum.EMPTY.ordinal()) {
                        this.aiMove = temp;
                    }
                    break;
            }

        }

        return aiMove;
    }

    public Point2D aiCheckWin() {
        boolean testBreak = false;

        for (int y = 0; y < board.length; y++) {
            if (testBreak) {
                break;
            }

            for (int x = 0; x < board.length; x++) {
                if (board[x][y] == PieceValueEnum.EMPTY.ordinal()) {
                    // Horizpntal Ai Win checking right
                    if (x < board.length - 4 && board[x + 1][y] == circleColor.ordinal() && board[x + 2][y] == circleColor.ordinal()
                            && board[x + 3][y] == circleColor.ordinal() && board[x + 4][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1) && board[x + 1][y] == circleColor.ordinal() && board[x - 1][y] == circleColor.ordinal()
                            && board[x - 2][y] == circleColor.ordinal() && board[x - 3][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 3) && board[x - 1][y] == circleColor.ordinal() && board[x + 1][y] == circleColor.ordinal()
                            && board[x + 2][y] == circleColor.ordinal() && board[x + 3][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 2) && board[x - 1][y] == circleColor.ordinal() && board[x - 2][y] == circleColor.ordinal()
                            && board[x + 2][y] == circleColor.ordinal() && board[x + 1][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking left
                    else if ((x > 3 && x < board.length) && board[x - 1][y] == circleColor.ordinal() && board[x - 2][y] == circleColor.ordinal()
                            && board[x - 3][y] == circleColor.ordinal() && board[x - 4][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking down 
                    else if (y < board.length - 4 && board[x][y + 1] == circleColor.ordinal() && board[x][y + 2] == circleColor.ordinal()
                            && board[x][y + 3] == circleColor.ordinal() && board[x][y + 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 2 && y < board.length - 1) && board[x][y + 1] == circleColor.ordinal() && board[x][y - 1] == circleColor.ordinal()
                            && board[x][y - 3] == circleColor.ordinal() && board[x][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 0 && y < board.length - 3) && board[x][y + 1] == circleColor.ordinal() && board[x][y - 1] == circleColor.ordinal()
                            && board[x][y + 3] == circleColor.ordinal() && board[x][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 1 && y < board.length - 2) && board[x][y + 1] == circleColor.ordinal() && board[x][y - 1] == circleColor.ordinal()
                            && board[x][y + 2] == circleColor.ordinal() && board[x][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking up
                    else if ((y > 3 && x < board.length) && board[x][y - 1] == circleColor.ordinal() && board[x][y - 2] == circleColor.ordinal()
                            && board[x][y - 3] == circleColor.ordinal() && board[x][y - 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Up rising AI win checking right 
                    else if ((y > 3 && x < board.length - 4) && board[x + 1][y - 1] == circleColor.ordinal() && board[x + 2][y - 2] == circleColor.ordinal()
                            && board[x + 3][y - 3] == circleColor.ordinal() && board[x + 4][y - 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 3 && y < board.length - 1 && y > 2) && board[x - 1][y + 1] == circleColor.ordinal() && board[x + 1][y - 1] == circleColor.ordinal()
                            && board[x + 2][y - 2] == circleColor.ordinal() && board[x + 3][y - 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1 && y < board.length - 3 && y > 0) && board[x - 1][y + 1] == circleColor.ordinal() && board[x + 1][y - 1] == circleColor.ordinal()
                            && board[x - 2][y + 2] == circleColor.ordinal() && board[x - 3][y + 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 2 && y < board.length - 2 && y > 1) && board[x - 1][y + 1] == circleColor.ordinal() && board[x + 1][y - 1] == circleColor.ordinal()
                            && board[x + 2][y - 2] == circleColor.ordinal() && board[x - 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking left
                    else if ((x > 3 && y < board.length - 4) && board[x - 1][y + 1] == circleColor.ordinal() && board[x - 2][y + 2] == circleColor.ordinal()
                            && board[x - 3][y + 3] == circleColor.ordinal() && board[x - 4][y + 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking right 
                    else if ((y < board.length - 4 && x < board.length - 4) && board[x + 1][y + 1] == circleColor.ordinal() && board[x + 2][y + 2] == circleColor.ordinal()
                            && board[x + 3][y + 3] == circleColor.ordinal() && board[x + 4][y + 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1 && y > 2 && y < board.length - 1) && board[x + 1][y + 1] == circleColor.ordinal() && board[x - 1][y - 1] == circleColor.ordinal()
                            && board[x - 3][y - 3] == circleColor.ordinal() && board[x - 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } else if ((x > 0 && x < board.length - 3 && y > 0 && y < board.length - 3) && board[x + 1][y + 1] == circleColor.ordinal() && board[x - 1][y - 1] == circleColor.ordinal()
                            && board[x + 3][y + 3] == circleColor.ordinal() && board[x + 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } else if ((x > 1 && x < board.length - 2 && y > 1 && y < board.length - 2) && board[x + 1][y + 1] == circleColor.ordinal() && board[x - 1][y - 1] == circleColor.ordinal()
                            && board[x + 2][y + 2] == circleColor.ordinal() && board[x - 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking left
                    else if ((x > 3 && y > 3) && board[x - 1][y - 1] == circleColor.ordinal() && board[x - 2][y - 2] == circleColor.ordinal()
                            && board[x - 3][y - 3] == circleColor.ordinal() && board[x - 4][y - 4] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    }

                }

            }

        }

        return aiMove;
    }

    public Point2D aiCheckLose() {
        boolean testBreak = false;

        for (int y = 0; y < board.length; y++) {
            if (testBreak) {
                break;
            }

            for (int x = 0; x < board.length; x++) {
                if (board[x][y] == PieceValueEnum.EMPTY.ordinal()) {
                    // Horizpntal Ai Win checking right
                    if (x < board.length - 4 && board[x + 1][y] == opponentColor && board[x + 2][y] == opponentColor
                            && board[x + 3][y] == opponentColor && board[x + 4][y] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1) && board[x + 1][y] == opponentColor && board[x - 1][y] == opponentColor
                            && board[x - 2][y] == opponentColor && board[x - 3][y] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 3) && board[x - 1][y] == opponentColor && board[x + 1][y] == opponentColor
                            && board[x + 2][y] == opponentColor && board[x + 3][y] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 2) && board[x - 1][y] == opponentColor && board[x - 2][y] == opponentColor
                            && board[x + 2][y] == opponentColor && board[x + 1][y] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking left
                    else if ((x > 2 && x < board.length) && board[x - 1][y] == opponentColor && board[x - 2][y] == opponentColor
                            && board[x - 3][y] == opponentColor && board[x - 4][y] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Horizontal AI win checking up
                    else if ((y > 3 && x < board.length) && board[x][y - 1] == opponentColor && board[x][y - 2] == opponentColor
                            && board[x][y - 3] == opponentColor && board[x][y - 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking down 
                    else if (y < board.length - 4 && board[x][y + 1] == opponentColor && board[x][y + 2] == opponentColor
                            && board[x][y + 3] == opponentColor && board[x][y + 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 2 && y < board.length - 1) && board[x][y + 1] == opponentColor && board[x][y - 1] == opponentColor
                            && board[x][y - 3] == opponentColor && board[x][y - 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 0 && y < board.length - 3) && board[x][y + 1] == opponentColor && board[x][y - 1] == opponentColor
                            && board[x][y + 3] == opponentColor && board[x][y + 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 1 && y < board.length - 2) && board[x][y + 1] == opponentColor && board[x][y - 1] == opponentColor
                            && board[x][y + 2] == opponentColor && board[x][y - 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking right 
                    else if ((y > 3 && x < board.length - 4) && board[x + 1][y - 1] == opponentColor && board[x + 2][y - 2] == opponentColor
                            && board[x + 3][y - 3] == opponentColor && board[x + 4][y - 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 3 && y < board.length - 1 && y > 2) && board[x - 1][y + 1] == opponentColor && board[x + 1][y - 1] == opponentColor
                            && board[x + 2][y - 2] == opponentColor && board[x + 3][y - 3] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1 && y < board.length - 3 && y > 0) && board[x - 1][y + 1] == opponentColor && board[x + 1][y - 1] == opponentColor
                            && board[x - 2][y + 2] == opponentColor && board[x - 3][y + 3] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 2 && y < board.length - 2 && y > 1) && board[x - 1][y + 1] == opponentColor && board[x + 1][y - 1] == opponentColor
                            && board[x + 2][y - 2] == opponentColor && board[x - 2][y + 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking left
                    else if ((x > 3 && y < board.length - 4) && board[x - 1][y + 1] == opponentColor && board[x - 2][y + 2] == opponentColor
                            && board[x - 3][y + 3] == opponentColor && board[x - 4][y + 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking right 
                    else if ((y < board.length - 4 && x < board.length - 4) && board[x + 1][y + 1] == opponentColor && board[x + 2][y + 2] == opponentColor
                            && board[x + 3][y + 3] == opponentColor && board[x + 4][y + 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 2 && x < board.length - 1 && y > 2 && y < board.length - 1) && board[x + 1][y + 1] == opponentColor && board[x - 1][y - 1] == opponentColor
                            && board[x - 3][y - 3] == opponentColor && board[x - 2][y - 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } else if ((x > 0 && x < board.length - 3 && y > 0 && y < board.length - 3) && board[x + 1][y + 1] == opponentColor && board[x - 1][y - 1] == opponentColor
                            && board[x + 3][y + 3] == opponentColor && board[x + 2][y + 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } else if ((x > 1 && x < board.length - 2 && y > 1 && y < board.length - 2) && board[x + 1][y + 1] == opponentColor && board[x - 1][y - 1] == opponentColor
                            && board[x + 2][y + 2] == opponentColor && board[x - 2][y - 2] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking left
                    else if ((x > 3 && y > 3) && board[x - 1][y - 1] == opponentColor && board[x - 2][y - 2] == opponentColor
                            && board[x - 3][y - 3] == opponentColor && board[x - 4][y - 4] == opponentColor) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    }

                }

            }

        }

        return aiMove;
    }

    public Point2D aiCheck3InARowAI() {
        boolean testBreak = false;

        for (int y = 0; y < board.length; y++) {
            if (testBreak) {
                break;
            }

            for (int x = 0; x < board.length; x++) {
                if (board[x][y] == PieceValueEnum.EMPTY.ordinal()) {
                    // Horizpntal Ai Win checking right
                    if (x < board.length - 4 && board[x + 1][y] == circleColor.ordinal() && board[x + 2][y] == circleColor.ordinal()
                            && board[x + 3][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 1) && board[x + 1][y] == circleColor.ordinal() && board[x - 1][y] == circleColor.ordinal()
                            && board[x - 2][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 2) && board[x - 1][y] == circleColor.ordinal() && board[x + 1][y] == circleColor.ordinal()
                            && board[x + 2][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking up
                    else if ((y > 3 && x < board.length) && board[x][y - 1] == circleColor.ordinal() && board[x][y - 2] == circleColor.ordinal()
                            && board[x][y - 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Horizontal AI win checking left
                    else if ((x > 3 && x < board.length) && board[x - 1][y] == circleColor.ordinal() && board[x - 2][y] == circleColor.ordinal()
                            && board[x - 3][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking down 
                    else if (y < board.length - 4 && board[x][y + 1] == circleColor.ordinal() && board[x][y + 2] == circleColor.ordinal()
                            && board[x][y + 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 1 && y < board.length - 1) && board[x][y + 1] == circleColor.ordinal() && board[x][y - 1] == circleColor.ordinal()
                            && board[x][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((y > 0 && y < board.length - 2) && board[x][y + 1] == circleColor.ordinal() && board[x][y - 1] == circleColor.ordinal()
                            && board[x][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking right 
                    else if ((y > 3 && x < board.length - 4) && board[x + 1][y - 1] == circleColor.ordinal() && board[x + 2][y - 2] == circleColor.ordinal()
                            && board[x + 3][y - 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 0 && x < board.length - 2 && y < board.length - 1 && y > 1) && board[x - 1][y + 1] == circleColor.ordinal() && board[x + 1][y - 1] == circleColor.ordinal()
                            && board[x + 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 1 && y < board.length - 2 && y > 0) && board[x - 1][y + 1] == circleColor.ordinal() && board[x + 1][y - 1] == circleColor.ordinal()
                            && board[x - 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking left
                    else if ((x > 3 && y < board.length - 4) && board[x - 1][y + 1] == circleColor.ordinal() && board[x - 2][y + 2] == circleColor.ordinal()
                            && board[x - 3][y + 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking right 
                    else if ((y < board.length - 4 && x < board.length - 4) && board[x + 1][y + 1] == circleColor.ordinal() && board[x + 2][y + 2] == circleColor.ordinal()
                            && board[x + 3][y + 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } else if ((x > 1 && x < board.length - 1 && y > 1 && y < board.length - 1) && board[x + 1][y + 1] == circleColor.ordinal() && board[x - 1][y - 1] == circleColor.ordinal()
                            && board[x - 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } else if ((x > 0 && x < board.length - 2 && y > 0 && y < board.length - 2) && board[x + 1][y + 1] == circleColor.ordinal() && board[x - 1][y - 1] == circleColor.ordinal()
                            && board[x + 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking left
                    else if ((x > 3 && y > 3) && board[x - 1][y - 1] == circleColor.ordinal() && board[x - 2][y - 2] == circleColor.ordinal()
                            && board[x - 3][y - 3] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    }

                }

            }

        }

        return aiMove;
    }

    public Point2D aiCheck3InARowPlayer() {
        boolean testBreak = false;

        for (int y = 0; y < board.length; y++) {
            if (testBreak) {
                break;
            }

            for (int x = 0; x < board.length; x++) {
                if (board[x][y] == PieceValueEnum.EMPTY.ordinal()) {
                    // Horizpntal Ai Win checking right 0XXX
                    if (x < board.length - 4 && board[x + 1][y] == opponentColor && board[x + 2][y] == opponentColor
                            && board[x + 3][y] == opponentColor && board[x + 4][y] != circleColor.ordinal() && x != 0) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // XX0X
                    else if ((x > 2 && x < board.length - 2) && board[x + 1][y] == opponentColor && board[x - 1][y] == opponentColor
                            && board[x - 2][y] == opponentColor && board[x - 3][y] != circleColor.ordinal() && board[x + 2][y] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // X0XX
                    else if ((x > 1 && x < board.length - 3) && board[x - 1][y] == opponentColor && board[x + 1][y] == opponentColor
                            && board[x + 2][y] == opponentColor && board[x - 2][y] != circleColor.ordinal() && board[x + 3][y] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking left XXX0
                    else if ((x > 3 && x < board.length) && board[x - 1][y] == opponentColor && board[x - 2][y] == opponentColor
                            && board[x - 3][y] == opponentColor && board[x - 4][y] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking up X
                    //                           X
                    //                           X
                    //                           0
                    else if ((y > 3 && x < board.length) && board[x][y - 1] == opponentColor && board[x][y - 2] == opponentColor
                            && board[x][y - 3] == opponentColor && board[x][y - 4] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking down 0
                    //                               X
                    //                               X
                    //                               X
                    else if (y < board.length - 4 && board[x][y + 1] == opponentColor && board[x][y + 2] == opponentColor
                            && board[x][y + 3] == opponentColor && board[x][y + 4] != circleColor.ordinal()
                            && y != 0) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } //              X
                    //              X
                    //              0
                    //              X
                    else if ((y > 2 && y < board.length - 2) && board[x][y + 1] == opponentColor && board[x][y - 1] == opponentColor
                            && board[x][y - 2] == opponentColor && board[x][y - 3] != circleColor.ordinal()
                            && board[x][y + 2] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } //                  X
                    //                  0
                    //                  X
                    //                  X
                    else if ((y > 1 && y < board.length - 3) && board[x][y + 1] == opponentColor && board[x][y - 1] == opponentColor
                            && board[x][y + 2] == opponentColor && board[x][y - 2] != circleColor.ordinal()
                            && board[x][y + 3] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking right 
                    //                             X
                    //                            X
                    //                           X
                    //                          0
                    else if ((y > 3 && x < board.length - 4) && board[x + 1][y - 1] == opponentColor && board[x + 2][y - 2] == opponentColor
                            && board[x + 3][y - 3] == opponentColor && board[x + 4][y - 4] != circleColor.ordinal() && x != 0
                            && y != board.length - 1) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } //             X
                    //            X
                    //           0
                    //          X
                    else if ((x > 1 && x < board.length - 3 && y < board.length - 1 && y > 1) && board[x - 1][y + 1] == opponentColor
                            && board[x + 1][y - 1] == opponentColor
                            && board[x + 2][y - 2] == opponentColor && board[x + 3][y - 3] != circleColor.ordinal()
                            && board[x - 2][y + 2] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } //         X
                    //        0
                    //       X
                    //      X
                    else if ((x > 2 && x < board.length - 1 && y < board.length - 2 && y > 1) && board[x - 1][y + 1] == opponentColor
                            && board[x + 1][y - 1] == opponentColor
                            && board[x - 2][y + 2] == opponentColor && board[x + 2][y - 2] != circleColor.ordinal()
                            && board[x - 3][y + 3] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking left
                    //          0
                    //         X
                    //        X
                    //       X    
                    else if ((x > 3 && y < board.length - 4) && board[x - 1][y + 1] == opponentColor && board[x - 2][y + 2] == opponentColor
                            && board[x - 3][y + 3] == opponentColor && board[x - 4][y + 4] != circleColor.ordinal() && x != board.length - 1
                            && y != 0) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking right 
                    //        0  
                    //         X
                    //          X
                    //           X
                    else if ((y < board.length - 4 && x < board.length - 4) && board[x + 1][y + 1] == opponentColor && board[x + 2][y + 2] == opponentColor
                            && board[x + 3][y + 3] == opponentColor && board[x + 4][y + 4] != circleColor.ordinal() && x != 0
                            && y != 0) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } //         X
                    //          X
                    //           0   
                    //            X
                    else if ((x > 2 && x < board.length - 1 && y > 1 && y < board.length - 2) && board[x + 1][y + 1] == opponentColor && board[x - 1][y - 1] == opponentColor
                            && board[x - 2][y - 2] == opponentColor && board[x - 3][y - 3] != circleColor.ordinal()
                            && board[x + 2][y + 2] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } //         X
                    //          0
                    //           X
                    //            X
                    else if ((x > 1 && x < board.length - 2 && y > 0 && y < board.length - 3) && board[x + 1][y + 1] == opponentColor && board[x - 1][y - 1] == opponentColor
                            && board[x + 2][y + 2] == opponentColor && board[x - 2][y - 2] != circleColor.ordinal()
                            && board[x + 3][y + 3] != circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking left
                    //          X
                    //           X
                    //            X
                    //             0
                    else if ((x > 3 && y > 3) && board[x - 1][y - 1] == opponentColor && board[x - 2][y - 2] == opponentColor
                            && board[x - 3][y - 3] == opponentColor && board[x - 4][y - 4] != circleColor.ordinal() && x != board.length - 1
                            && y != board.length - 1) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    }

                }

            }

        }

        return aiMove;
    }

    public Point2D aiCheck2InARowAI() {
        boolean testBreak = false;

        for (int y = 0; y < board.length; y++) {
            if (testBreak) {
                break;
            }

            for (int x = 0; x < board.length; x++) {
                if (board[x][y] == PieceValueEnum.EMPTY.ordinal()) {
                    // Horizpntal Ai Win checking right
                    if (x < board.length - 4 && board[x + 1][y] == circleColor.ordinal() && board[x + 2][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking left
                    else if ((x > 3 && x < board.length) && board[x - 1][y] == circleColor.ordinal() && board[x - 2][y] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Vertical AI win checking down 
                    else if (y < board.length - 4 && board[x][y + 1] == circleColor.ordinal() && board[x][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Horizontal AI win checking up
                    else if ((y > 3 && x < board.length) && board[x][y - 1] == circleColor.ordinal() && board[x][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Up rising AI win checking right 
                    else if ((y > 3 && x < board.length - 4) && board[x + 1][y - 1] == circleColor.ordinal() && board[x + 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Up rising AI win checking left
                    else if ((x > 3 && y < board.length - 4) && board[x - 1][y + 1] == circleColor.ordinal() && board[x - 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    } // Down rising AI win checking right 
                    else if ((y < board.length - 4 && x < board.length - 4) && board[x + 1][y + 1] == circleColor.ordinal() && board[x + 2][y + 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;

                        break;
                    } // Down rising AI win checking left
                    else if ((x > 3 && y > 3) && board[x - 1][y - 1] == circleColor.ordinal() && board[x - 2][y - 2] == circleColor.ordinal()) {
                        aiMove = new Point2D(x, y);
                        testBreak = true;
                        break;
                    }

                }

            }

        }

        return aiMove;
    }

    public Point2D aiBehaveLikaAHuman() {
        if (board[7][7] == PieceValueEnum.EMPTY.ordinal()) {
            aiMove = new Point2D(7, 7);
        } else {
            for (int i = 0; i < 50; i++) { //Seöver loopen, se till att inte fastna. Tänk på att vi använder Random
                aiMove = new Point2D((int) (Math.random() * ((8 - 6) + 1)) + 6, (int) (Math.random() * ((8 - 6) + 1)) + 6);
                if (board[(int) aiMove.getX()][(int) aiMove.getY()] == PieceValueEnum.EMPTY.ordinal()) {
                    break;
                }

            }

        }

        return aiMove;
    }

    @Override
    public String toString() {
        String info = "AI: " + circleColor + " & " + turn;
        return info;
    }

}

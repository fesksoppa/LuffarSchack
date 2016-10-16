package controller;

import javafx.geometry.Point2D;
import view.BoardWindow;
import view.NewGameWindow;
import model.GameRules;
import model.PieceValueEnum;

/**
 *
 * @author Krist
 */
public class LuffaSchackController {

    private BoardWindow boardWindow;
    private NewGameWindow newGameWindow;
    private GameRules gameRules;

    /**
     * Constructor of the controller to initialize members and provide information
     * to other objects.
     * @param boardWindow The main window of the game
     * @param newGameWindow The menu where to adjust settings for the game.
     */
    public LuffaSchackController(BoardWindow boardWindow, NewGameWindow newGameWindow) {
        this.boardWindow = boardWindow;
        this.newGameWindow = newGameWindow;
        boardWindow.setController(this);
        newGameWindow.setController(this);
        initGame();

    }

    /**
     * The method sets the initial vew of the game to be displayed when program
     * executes
     */
    public void initGame() {
        boardWindow.initView();

    }

    /**
     * The method fetches information about which turn it is
     * @return Return true of false depedning on whch players turn it is
     */
    public boolean getPlayerTurn() {
        return gameRules.getPlayerTurn();

    }

    /**
     * The method checks if a win or draw has occured and sets the game to gameover
     */
    public void setGameOver() {
        if (gameRules.getWhiteWin()) {
            boardWindow.playWinAnimation(gameRules.getWinningCoordinates(), "White");
            boardWindow.setTurnGameOver();

        } else if (gameRules.getBlackWin()) {
            boardWindow.playWinAnimation(gameRules.getWinningCoordinates(), "Black");
            boardWindow.setTurnGameOver();
        } else if (gameRules.checkDraw()) {
            boardWindow.alertWindowWinner("DRAW!!!!", "Draw");
            boardWindow.setTurnGameOver();
        }

    }

    /**
     * The method handles the events from the menubar 
     */
    public void eventHandlerMenuAction() {
        newGameWindow.display();

    }

    /**
     * The method handles the events from the clicked cells in the grid 
     * and paints the circles in the specifik cell.
     * @param coordinate 
     */
    public void eventHandlerPlayerMove(Point2D coordinate) {
        if (newGameWindow.getSelectedColor() != null && gameRules.setPlayerMove(coordinate)) {
            boardWindow.addCircle(gameRules.getPlayerColor(), coordinate);
            boardWindow.setPlayerTurnColor();
            setGameOver();
        }
        if (gameRules.aiExcists() && !gameRules.getBlackWin() && !gameRules.getWhiteWin()) {
            aiMove();
        }
    }

    /**
     * The method tells the ai to make a move
     */
    public void aiMove() {
        Point2D aiMove = new Point2D(0, 0); //WHAT?!
        aiMove = gameRules.SetAIMove();
        boardWindow.addCircle(gameRules.getPlayerColor(), aiMove);
        boardWindow.setPlayerTurnColor();
        setGameOver();
    }

    /**
     * The method handels the events from the newGameWindow where users can
     * adjust settings for the game
     */
    public void eventHandlerOkButton() {

        boardWindow.initView();// SKapar Grafisk spelplan
        boardWindow.addGameSymbol(newGameWindow.getSelectedColor()); //säter färg på symbol
        gameRules = new GameRules(newGameWindow.getBoardSize(), newGameWindow.getBoardSize()); // skapar logisk spelplan
        gameRules.resetBoard(); // resetar spelplan,spelare och ai. 

        if (newGameWindow.getSelectedColor().ordinal() == 1) {
            gameRules.createPlayer(PieceValueEnum.WHITE, newGameWindow.getWhoGoesFirst());
            if (newGameWindow.getOpponent()) {
                gameRules.createAi(PieceValueEnum.BLACK, !newGameWindow.getWhoGoesFirst());
                boardWindow.setOpponentLabel("  Ai  ");
            } else {
                gameRules.createPlayer(PieceValueEnum.BLACK, !newGameWindow.getWhoGoesFirst());
                boardWindow.setOpponentLabel("Player2");
            }
        } else if (newGameWindow.getSelectedColor().ordinal() == 2) {
            gameRules.createPlayer(PieceValueEnum.BLACK, newGameWindow.getWhoGoesFirst());
            if (newGameWindow.getOpponent()) {
                gameRules.createAi(PieceValueEnum.WHITE, !newGameWindow.getWhoGoesFirst());
            } else {
                gameRules.createPlayer(PieceValueEnum.WHITE, !newGameWindow.getWhoGoesFirst());
                boardWindow.setOpponentLabel("Player2");
            }
        }
        boardWindow.setPlayerTurnColor();
        boardWindow.fillGridPane(newGameWindow.getBoardSize());
        if (!newGameWindow.getWhoGoesFirst() && gameRules.aiExcists()) {
            aiMove();
        }
    }
}

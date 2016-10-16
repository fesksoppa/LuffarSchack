package model;

/** The Player class represents a player in the class GameRules.
 *  The Object has two class attributes circleColor and turn.
 *  CircleColor represents the pieace color of the player during the game.
 *  Turn represents the player turn.
 * 
 */
public class Player {

    private boolean turn;
    private final PieceValueEnum circleColor;

    /**
     * Initilizes the class attributies with the given parameters. 
     * @param cirleColor represents the pieace color for the player
     * @param turn represents the turn for the player. 
     */
    public Player(PieceValueEnum cirleColor, boolean turn) {
        this.turn = turn;
        this.circleColor = cirleColor;

    }
    /**
     * Returns the Player attribute turn
     * @return turn
     */

    public boolean getPlayerTurn() {
        return this.turn;
    }
    /**
     * Return the pieace color representing the player object.
     * @return circleColor
     */
    public PieceValueEnum getCircleColor() {
        return this.circleColor;
    }
    /**
     * Changes the turn for the Player object
     */
    public void setPlayerTurn() {
        this.turn = !this.turn;
    }
    
    @Override
    /**
     * Returns infomation about the Player object
     */
    public String toString() {
        String info = "Player: " + circleColor + " & " + turn;
        return info;
    }
}

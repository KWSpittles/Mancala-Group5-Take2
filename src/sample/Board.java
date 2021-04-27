package sample;

/**
 * Class to control game board mechanics
 * like creating a new board, checking for the count in Sides,
 * for setting and getting the pit values
 * and also for incrementing and checking whether a player's Side is empty or not
 *
 * @author
 */
public class Board {

    Hole Player1Store = new Hole();
    Hole Player2Store = new Hole();
    private Side Player1Side;
    private Side Player2Side;

    /**
     * Constructor for creating a new board of holes
     * and two player sides
     */
    public Board() {
        Player1Side = new Side(1, 4);
        Player2Side = new Side(2, 4);
        Hole Player1Store = new Hole(0);
        Hole Player2Store = new Hole(0);
    }

    /**
     * Constructor with a parameter for creating a new board of holes
     * and two player sides
     *
     * @param stones - the number of stones inside a certain pit
     */
    public Board(int stones) {
        Player1Side = new Side(1, stones);
        Player2Side = new Side(2, stones);
        Hole Player1Store = new Hole();
        Hole Player2Store = new Hole();
    }

    /**
     * Method to get the pit value of player 1 side
     *
     * @param Player1   - determines the boolean value for player 1
     * @param pitNumber - the pit number on player 1 side
     * @return - integer value of the pit
     */
    public int getPitValue(boolean Player1, int pitNumber) {
        if (Player1) {
            if (pitNumber <= 5) {
                return Player1Side.getPit(pitNumber).getPitValue();
            } else return Player1Store.getPitValue();
        } else {
            if (pitNumber <= 7) {
                return Player2Side.getPit(pitNumber).getPitValue();
            } else return Player2Store.getPitValue();
        }
    }

    /**
     * Method to for setting the pit value of player 1
     *
     * @param Player1   - boolean parameter for player 1
     * @param pitNumber - integer value of certain pit
     * @param value     - value to be set to the given pit
     */
    public void setPitValue(boolean Player1, int pitNumber, int value) {
        if (Player1) {
            if (pitNumber <= 5) {
                Player1Side.getPit(pitNumber).setPitValue(value);
            } else Player1Store.setPitValue(value);
        } else {
            if (pitNumber <= 12) {
                Player2Side.getPit(pitNumber).setPitValue(value);
            } else Player2Store.setPitValue(value);
        }
    }

    /**
     * Method to increment to the pit value for player 1
     *
     * @param Player1   - boolean parameter for player 1
     * @param pitNumber - the pit number on player 1 side
     */
    public void incrementPitValue(boolean Player1, int pitNumber) {
        if (Player1) {
            if (pitNumber <= 5) {
                Player1Side.getPit(pitNumber).incrementPitValue();
            } else Player1Store.incrementPitValue();
        } else {
            if (pitNumber <= 5) {
                Player2Side.getPit(pitNumber).incrementPitValue();
            } else Player2Store.incrementPitValue();
        }
    }

    /**
     * Method to check if player1's Side is empty or not
     *
     * @return - boolean value after checking player1's Side
     */
    public boolean player1sideEmpty() {

        boolean flag = true;

        for (int i = 0; i <= 5; i++) {
            if (getPlayer1Side().getPit(i).getPitValue() != 0) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Method to check if player2's Side is empty or not
     *
     * @return - boolean value after checking player2's Side
     */
    public boolean player2sideEmpty() {

        boolean flag = true;

        for (int i = 0; i <= 5; i++) {
            if (getPlayer2Side().getPit(i).getPitValue() != 0) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Method to get the player 1 Side
     *
     * @return - the Side of player 1
     */
    public Side getPlayer1Side() {
        return Player1Side;
    }

    /**
     * Method to get the player 2 Side
     *
     * @return - the Side of player 2
     */
    public Side getPlayer2Side() {
        return Player2Side;
    }

    /**
     * Method to get the player 1 Store
     *
     * @return - the Store of player 1
     */
    public Hole getPlayer1Store() {
        return Player1Store;
    }

    /**
     * Method to get the player 2 Store
     *
     * @return - the Store of player 2
     */
    public Hole getPlayer2Store() {
        return Player2Store;
    }
}

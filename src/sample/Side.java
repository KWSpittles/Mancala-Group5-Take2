package sample;

/**
 * Class to get the side of the board
 * implements initialization of various game modes and access to sign-in pages
 * applies set FXML resources
 *
 * @author
 */
public class Side {

    private Hole[] playerPits;
    private int playerNumber;


    /**
     * Constructor for the Side of the game board
     *
     * @param playerNumber - takes in one of the numbers of the two players
     * @param stones       - takes in the number of stones in the side
     */
    public Side(int playerNumber, int stones) {
        //array of size 6 for the Holes
        this.playerPits = new Hole[6];

        //loop to create new Holes for each player's pits
        for (int i = 0; i <= 5; i++) {
            playerPits[i] = new Hole(stones);
        }

        this.playerNumber = playerNumber;
    }

    /**
     * Method to set the value of the pit
     *
     * @param pitNumber - takes in the number of a certain pit
     * @return - the number of stones inside a pit of given number
     */
    public Hole getPit(int pitNumber) {
        return playerPits[pitNumber];
    }

}

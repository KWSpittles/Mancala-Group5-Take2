package sample;

public class Side {

    private Hole[] playerPits;
    private int playerNumber;

    public Side(int playerNumber, int stones) {
        this.playerPits = new Hole[6];
        for (int i = 0; i <= 5; i++) {
            playerPits[i] = new Hole(stones);
        }

        this.playerNumber = playerNumber;
    }


    public Hole getPit(int pitNumber){
        return playerPits[pitNumber];
    }




}

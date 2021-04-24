package sample;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Player extends User {
	//boolean to check if its player's turn
    public boolean isCurrentTurn;
    private int currentScore;
    private int playerNumber;
    
    //no argument constructor
    public Player() {
	}
    
    //Sets whether if player is Player 1 or Player 2
    public void setPlayerNumber(int playerNumber) {
    	this.playerNumber = playerNumber;
    }
    
    //gets current score of player
    public int getCurrentScore () {
    	return currentScore;
    }
    
}


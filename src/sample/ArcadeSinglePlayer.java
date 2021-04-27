package sample;

/**
 * This class is used to run arcade in the single player mode.
 * @author KaKoi Pun;
 * @author Kieren Spittles;
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ArcadeSinglePlayer extends ArcadeMultiPlayer implements Initializable  {

    /**
     * Variables to store JavaFXML resources
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Variable for board type for current board status
     */
    private Board gameBoard;
    /**
     * User variables to store Users
     */
    private static User player1;
    private static User player2;
    /**
     * ArrayList to store the current moves for AI
     */
    private static ArrayList<Integer> computersValidMoves;
    private int frequencyOfPowerUpsAndSpecialStone;
    /**
     * Variables to hold buttons for powerups
     */
    public boolean continueTurnButton1 = false;
    public boolean doublePointsButton1= false;
    public boolean continueTurnButton2 = false;
    public boolean doublePointsButton2 = false;
    /**
     * Boolean to hold status of rounds of gameplay
     */
    public boolean firstRound;
    /**
     * Integer variable to hold stones in player's hand
     */
    public int stonesInHand;

    /**
     * Default constructor.
     * Constructs the arcade mode single player which can be called with no arguments.
     * Calls and returns a new board and the players
     */
    public ArcadeSinglePlayer() {
        this.frequencyOfPowerUpsAndSpecialStone = 0;
        gameBoard = new Board();
        player1 = new User();
        player2 = new User();
    }

    /**
     * Constructor for arcade single player mode that allows you to specify the
     * starting number of stonesPerPit in each pit.
     * @param stonesPerPit Number of stones each player will start with in each pit.
     */
    public ArcadeSinglePlayer(int stonesPerPit) {
        gameBoard = new Board(stonesPerPit);
        player1 = new User();
        player2 = new User();
    }

    /**
     * Constructor for arcade single player mode.
     * Returns a new board with the starting stones and the two starting users.
     * Initialises the computer as second player.
     * @param stonesPerPit Number of stones each player will start with in each pit.
     * @param player1 A User, player1
     * @param player2 A User, player2
     */
    public ArcadeSinglePlayer(int stonesPerPit, User player1, User player2) {
        gameBoard = new Board(stonesPerPit);
        this.player1 = player1;
        this.player2 = player2;
        player2 = new User();
        computersValidMoves = new ArrayList<>();
    }

    /**
     * Method to reset the board. Puts 4 stones in each pit, and 0 in each
     * players store. Then displays the Board on the GUI.
     * Alerts the player of this.
     */
    public void resetBoard() {
        gameBoard.getPlayer1Side().getPit(0).setPitValue(4);
        gameBoard.getPlayer1Side().getPit(1).setPitValue(4);
        gameBoard.getPlayer1Side().getPit(2).setPitValue(4);
        gameBoard.getPlayer1Side().getPit(3).setPitValue(4);
        gameBoard.getPlayer1Side().getPit(4).setPitValue(4);
        gameBoard.getPlayer1Side().getPit(5).setPitValue(4);
        gameBoard.getPlayer1Store().setPitValue(0);
        gameBoard.getPlayer2Side().getPit(0).setPitValue(4);
        gameBoard.getPlayer2Side().getPit(1).setPitValue(4);
        gameBoard.getPlayer2Side().getPit(2).setPitValue(4);
        gameBoard.getPlayer2Side().getPit(3).setPitValue(4);
        gameBoard.getPlayer2Side().getPit(4).setPitValue(4);
        gameBoard.getPlayer2Side().getPit(5).setPitValue(4);
        gameBoard.getPlayer2Store().setPitValue(0);

        displayBoard();

        System.out.println("You have Reset the Board");
        System.out.println(player1);
    }

    @FXML
    public Label labelpit0, labelpit1, labelpit2, labelpit3, labelpit4, labelpit5, labelpit6, labelpit7, labelpit8, labelpit9, labelpit10, labelpit11, labelpit12, labelpit13;
    @FXML
    ImageView imageview1;

    /**
     * Method to display the GUI. Displays the board on the GUI, displays a
     * players profile picture and shows which players turn it is.
     * Returns this throughout the gameplay.
     */
    public void displayBoard() {
        labelpit0.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(0).getPitValue()));
        labelpit1.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(1).getPitValue()));
        labelpit2.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(2).getPitValue()));
        labelpit3.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(3).getPitValue()));
        labelpit4.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(4).getPitValue()));
        labelpit5.setText(String.valueOf(gameBoard.getPlayer1Side().getPit(5).getPitValue()));
        labelpit6.setText(String.valueOf(gameBoard.getPlayer1Store().getPitValue()));
        labelpit7.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(0).getPitValue()));
        labelpit8.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(1).getPitValue()));
        labelpit9.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(2).getPitValue()));
        labelpit10.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(3).getPitValue()));
        labelpit11.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(4).getPitValue()));
        labelpit12.setText(String.valueOf(gameBoard.getPlayer2Side().getPit(5).getPitValue()));
        labelpit13.setText(String.valueOf(gameBoard.getPlayer2Store().getPitValue()));

        //Displays profile picture on the board
        Image profilepic1 = new Image(getClass().getResourceAsStream("/" + player1.getUserName() + ".jpg"),150,150,false,false);
        imageview1.setImage(profilepic1);

//        Image profilepic2 = new Image(getClass().getResourceAsStream("/Computer.jpg"),150,150,false,false);
//        imageview2.setImage(profilepic2);

        System.out.println("You have displayed a new Board");

        if(player1.isCurrentTurn){
            turnMessage.setText("It is " + player1.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else {
            turnMessage.setText("It is " + player2.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
    }

    /**
     *  Assets for pit buttons
     */
    @FXML
    public Button buttonpit0, buttonpit1,buttonpit2,buttonpit3,buttonpit4,buttonpit5,buttonpit7,buttonpit8,buttonpit9,buttonpit10,buttonpit11,buttonpit12;
    public Label store;

    /**
     * Method for player1's 1st pit button. Gets player input and starts a turn.
     */
    public void buttonpit0() {
        validMove(gameBoard, true, 0);
    }
    /**
     * Method for player1's 2nd pit button. Gets player input and starts a turn.
     */
    public void buttonpit1() {
        validMove(gameBoard, true, 1);
    }
    /**
     * Method for player1's 3rd pit button. Gets player input and starts a turn.
     */
    public void buttonpit2() {
        validMove(gameBoard, true, 2);
    }
    /**
     * Method for player1's 4th pit button. Gets player input and starts a turn.
     */
    public void buttonpit3() {
        validMove(gameBoard, true, 3);
    }
    /**
     * Method for player1's 5th pit button. Gets player input and starts a turn.
     */
    public void buttonpit4() {
        validMove(gameBoard, true, 4);
    }
    /**
     * Method for player1's 6th pit button. Gets player input and starts a turn.
     */
    public void buttonpit5() {
        validMove(gameBoard, true, 5);
    }
    /**
     * Method for player2's 1st pit button. Gets player input and starts a turn.
     */
    public void buttonpit7() {
        validMove(gameBoard, false, 0);
    }
    /**
     * Method for player2's 2nd pit button. Gets player input and starts a turn.
     */
    public void buttonpit8() {
        validMove(gameBoard, false, 1 );
    }
    /**
     * Method for player2's 3rd pit button. Gets player input and starts a turn.
     */
    public void buttonpit9() {
        validMove(gameBoard, false, 2);
    }
    /**
     * Method for player1's 4th pit button. Gets player input and starts a turn.
     */
    public void buttonpit10() {
        validMove(gameBoard, false, 3);
    }
    /**
     * Method for player1's 5th pit button. Gets player input and starts a turn.
     */
    public void buttonpit11() {
        validMove(gameBoard, false, 4);
    }
    /**
     * Method for player1's 6th pit button. Gets player input and starts a turn.
     */
    public void buttonpit12() {
        validMove(gameBoard, false, 5);
    }

    @FXML
    Label gameover;
    @FXML
    Label turnMessage;
    @FXML
    Label invalidTurnMessage;

    /**
     * method checks if the game is over. if side is empty then ends the game and the GUI is updated.
     * Player score is updated by this.
     * @param gameBoard The current state of the board.
     */
    public void checkGameOver(Board gameBoard){

        if (gameBoard.player1sideEmpty() | gameBoard.player2sideEmpty()){
            System.out.println("Game Over");
            for (int i = 0; i <= 5 ; i++) {
                int store1 = gameBoard.Player1Store.getPitValue();
                int store2 = gameBoard.Player2Store.getPitValue();
                int value1 = gameBoard.getPitValue(true, i);
                int value2 = gameBoard.getPitValue(false, i);

                gameBoard.setPitValue(true, i, 0);
                gameBoard.setPitValue(false, i, 0);
                gameBoard.Player1Store.setPitValue(store1 + value1);
                gameBoard.Player2Store.setPitValue(store2 + value2);
            }
            if (gameBoard.Player1Store.getPitValue() < gameBoard.Player2Store.getPitValue()) {
                gameover.setText("Player 2 Wins");
                turnMessage.setText("");
                player1.addWinLossDraw(1);
                player2.addWinLossDraw(0);
            }
            if (gameBoard.Player1Store.getPitValue() > gameBoard.Player2Store.getPitValue()) {
                gameover.setText("Player 1 Wins");
                turnMessage.setText("");
                player1.addWinLossDraw(0);
                player2.addWinLossDraw(1);
            }
            if (gameBoard.Player1Store.getPitValue() == gameBoard.Player2Store.getPitValue()) {
                gameover.setText("Draw");
                turnMessage.setText("");
                player1.addWinLossDraw(2);
                player2.addWinLossDraw(2);
            }
        }
    }

    /**
     * Checks if a given move is valid on a given turn, with a special stone trigger.
     * If valid it will execute the move starting from the particular pit.
     * If invalid an error message is returned to the user.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */
    public void validMove(Board gameBoard, boolean player1Side, int pitPressed) {

        if (player1.isCurrentTurn) {
            if (player1Side && pitPressed <= 5 && (Math.random() > 0.1)) {
                if(gameBoard.getPitValue(true,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    makeMove(gameBoard, true, pitPressed);
                    invalidTurnMessage.setText("processing");
                }
            }

            else if (player1Side && pitPressed <= 5 && (Math.random() <= 0.033)) {
                if(gameBoard.getPitValue(true,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    halfHand(gameBoard, true, pitPressed);
                    invalidTurnMessage.setText("A Half hand special stone has been triggered.");
                }
            }

            else if (player1Side && pitPressed <= 5 &&  (Math.random() > 0.033 && Math.random() <= 0.066)) {
                if (gameBoard.getPitValue(true, pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else {
                    reverseTurn(gameBoard, true, pitPressed);
                    invalidTurnMessage.setText("A reverse turn special stone has been triggered.");
                }
            }

            else if (player1Side && pitPressed <= 5 && (Math.random() >0.66 && Math.random() <= 0.1)) {
                if (gameBoard.getPitValue(true, pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                } else {
                    switchSides(gameBoard, true, pitPressed);
                    invalidTurnMessage.setText("A switch sides special stone has been triggered.");
                }
            }
            else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
                invalidTurnMessage.setText("Pit is invalid. Please choose a pit on your side.");
            }

        }
        else {
            if ((!player1Side) && pitPressed <= 5 && (Math.random() >= 0.1)) {
                if(gameBoard.getPitValue(false,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    makeMove(gameBoard, false, pitPressed);
                    invalidTurnMessage.setText("processing");
                }
            }
            else if ((!player1Side) && pitPressed <= 5 && (Math.random() <= 0.033)) {
                if(gameBoard.getPitValue(false,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    halfHand(gameBoard, false, pitPressed);
                    invalidTurnMessage.setText("A Half hand special stone has been triggered.");
                }
            }
            else if ((!player1Side) && pitPressed <= 5 && (Math.random() > 0.033 && Math.random() <= 0.066)) {
                if (gameBoard.getPitValue(false, pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else {
                    reverseTurn(gameBoard, false, pitPressed);
                    invalidTurnMessage.setText("A reverse turn special stone has been triggered.");
                }
            }
            else if ((!player1Side) && pitPressed <= 5 && (Math.random() >0.066 && Math.random() <= 0.099)) {
                if (gameBoard.getPitValue(false, pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                } else {
                    switchSides(gameBoard, false, pitPressed);
                    invalidTurnMessage.setText("A switch sides special stone has been triggered.");
                }
            }
            else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
                invalidTurnMessage.setText("Pit is invalid. Please choose a pit on your side.");
            }

        }
    }





    /**
     * Method to select starting player of game with 50% chance of selection.
     * Updates GUI to display whose turn.
     */
    public void firstPlayer(){
        if(Math.random()>=0){
            player1.isCurrentTurn = true;
            turnMessage.setText("It is " + player1.getFirstName() +"'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else{
            player1.isCurrentTurn = false;
            turnMessage.setText("It is " + player2.getFirstName() +"'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
    }


    /**
     * An arraylist which returns the computer's valid moves for single player
     * @return - Computer's current valid moves
     */
    public ArrayList<Integer> getComputersValidMoves(){
        return computersValidMoves;
    }



    /**
     * Method makes the computer's move.
     * @param computersValidMoves - Takes if valid moves can be made
     * @return - Makes move if valid and returns to user and displays on GUI.
     */
    public int getComputersMove(ArrayList<Integer> computersValidMoves){
        for (int i = 0; i <= 5; i++) {
            if(gameBoard.getPitValue(false, i) != 0){
                computersValidMoves.add(i);
            }
        }
        int value = (int)Math.random()*(computersValidMoves.size());
        int pitPressed = computersValidMoves.get(value);
        System.out.println(pitPressed);

        return pitPressed;
    }


    /**
     * Allows computer to make a move if valid.
     */
    public void computerMove() {
        validMove(gameBoard, false, getComputersMove(getComputersValidMoves()));
    }

    /**
     * Executes the algorithm for a players turn and two power-ups
     * boolean methods: Continue turn and double points. After a players turn
     * it will make a move for the computer.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */
    public void makeMove(Board gameBoard, Boolean player1Side, int pitPressed) {

        firstRound = true;
        stonesInHand = gameBoard.getPitValue(player1Side, pitPressed);
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        while(stonesInHand>0) {

            if (!firstRound) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                            System.out.println("FLAG B");
                        }
                    }
                }
            }

            else if (player1.isCurrentTurn && firstRound) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    System.out.println(i);
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    System.out.println("Stones in hand = " + stonesInHand);

                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && player1.isCurrentTurn) {
                if(doublePointsButton1) {
                    gameBoard.getPlayer1Store().incrementPitValue();
                    gameBoard.getPlayer1Store().incrementPitValue();
                    doublePointsButton1 = false;
                    doublePointsOn1.setStyle("-fx-border-color: red");
                }
                else{
                    gameBoard.getPlayer1Store().incrementPitValue();
                }
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }

            if (!firstRound && stonesInHand>0) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag F");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag G");
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stonesInHand>0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag H");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag I");
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && !player1.isCurrentTurn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag J");
                    return;
                }
            }
        }

        System.out.println("Flag K");
        player1.isCurrentTurn = !player1.isCurrentTurn;
        displayBoard();
        checkGameOver(gameBoard);
        System.out.println(player1Side);



        if(player1.isCurrentTurn){
            turnMessage.setText("It is " + player1.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else {
            turnMessage.setText("It is " + player2.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
        checkGameOver(gameBoard);
        return;
    }

    /**
     * Executes the algorithm for a players turn when reverse turn special stone
     * has been triggered. After a players turn, it will make a move for the computer.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */
    public void halfHand(Board gameBoard, boolean player1Side, int pitPressed) {

        firstRound = true;
        stonesInHand = gameBoard.getPitValue(player1Side, pitPressed) / 2;
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        while(stonesInHand>0) {

            if (!firstRound) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(true, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                            System.out.println("FLAG B");
                        }
                    }
                }
            }

            else if (player1.isCurrentTurn && firstRound) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    System.out.println(i);
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    System.out.println("Stones in hand = " + stonesInHand);

                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && player1.isCurrentTurn) {
                gameBoard.getPlayer1Store().incrementPitValue();
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }

            if (!firstRound && stonesInHand>0) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag F");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag G");
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stonesInHand>0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag H");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag I");
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && !player1.isCurrentTurn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag J");
                    return;
                }
            }
        }

        System.out.println("Flag K");
        player1.isCurrentTurn = !player1.isCurrentTurn;
        displayBoard();
        checkGameOver(gameBoard);
        System.out.println(player1Side);



        if(player1.isCurrentTurn){
            turnMessage.setText("It is " + player1.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else {
            turnMessage.setText("It is " + player2.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
        checkGameOver(gameBoard);
        return;
    }

    /**
     * Executes the algorithm for a players turn when reverse turn special stone
     * has been triggered. After a players turn, it will make a move for the computer.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */
    public void reverseTurn(Board gameBoard, boolean player1Side, int pitPressed) {

        firstRound = true;
        stonesInHand = gameBoard.getPitValue(player1Side, pitPressed);
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        while(stonesInHand>0) {

            if (!firstRound) {

                for (int i = 5; i >=0; i--) {
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(true, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                            System.out.println("FLAG B");
                        }
                    }
                }
            }

            else if (player1.isCurrentTurn && firstRound) {

                for (int i = 5; i <= pitPressed + 1; i--) {
                    System.out.println(i);
                    gameBoard.incrementPitValue(true, i);
                    stonesInHand--;
                    System.out.println("Stones in hand = " + stonesInHand);

                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stonesInHand = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && player1.isCurrentTurn) {
                gameBoard.getPlayer1Store().incrementPitValue();
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }

            if (!firstRound && stonesInHand>0) {

                for (int i = 5; i >= 0 ; i--) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag F");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag G");
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stonesInHand>0) {

                for (int i = 5 ; i >= pitPressed + 1; i--) {
                    gameBoard.incrementPitValue(false, i);
                    stonesInHand--;
                    if (stonesInHand == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag H");
                            break;
                        } else {
                            stonesInHand = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag I");
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stonesInHand > 0 && !player1.isCurrentTurn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                stonesInHand--;
                if (stonesInHand == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag J");
                    return;
                }
            }
        }

        System.out.println("Flag K");
        player1.isCurrentTurn = !player1.isCurrentTurn;
        displayBoard();
        checkGameOver(gameBoard);
        System.out.println(player1Side);



        if(player1.isCurrentTurn){
            turnMessage.setText("It is " + player1.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else {
            turnMessage.setText("It is " + player2.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
        checkGameOver(gameBoard);
        return;
    }
    /**
     * Executes the algorithm for a players turn after switch sides special
     * has been triggered. After a players turn it will make a move for the computer.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */

    //player return all current stone to the pit and take the opposite pit/position
    public void switchSides(Board gameBoard, boolean player1Side, int pitPressed) {

        firstRound = true;
        stones = gameBoard.getPitValue(player1Side, pitPressed);
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        while(stones>0) {

            if (!firstRound) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("FLAG B");
                        }
                    }
                }
            }

            else if (player1.isCurrentTurn && firstRound) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    System.out.println(i);
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    System.out.println("Stones in hand = " + stones);

                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && player1.isCurrentTurn) {
                gameBoard.getPlayer1Store().incrementPitValue();
                stones--;
                if (stones == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }

            if (!firstRound && stones>0) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("Flag F");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                            System.out.println("Flag G");
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stones>0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("Flag H");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
                            System.out.println("Flag I");
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && !player1.isCurrentTurn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                stones--;
                if (stones == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag J");
                    return;
                }
            }
        }

        System.out.println("Flag K");
        player1.isCurrentTurn = !player1.isCurrentTurn;
        displayBoard();
        checkGameOver(gameBoard);
        System.out.println(player1Side);



        if(player1.isCurrentTurn){
            turnMessage.setText("It is " + player1.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: green");
            buttonpit1.setStyle("-fx-border-color: green");
            buttonpit2.setStyle("-fx-border-color: green");
            buttonpit3.setStyle("-fx-border-color: green");
            buttonpit4.setStyle("-fx-border-color: green");
            buttonpit5.setStyle("-fx-border-color: green");
            labelpit6.setStyle("-fx-border-color: green");
            buttonpit7.setStyle("-fx-border-color: red");
            buttonpit8.setStyle("-fx-border-color: red");
            buttonpit9.setStyle("-fx-border-color: red");
            buttonpit10.setStyle("-fx-border-color: red");
            buttonpit11.setStyle("-fx-border-color: red");
            buttonpit12.setStyle("-fx-border-color: red");
            labelpit13.setStyle("-fx-border-color: red");
        }
        else {
            turnMessage.setText("It is " + player2.getFirstName() + "'s turn");
            buttonpit0.setStyle("-fx-border-color: red");
            buttonpit1.setStyle("-fx-border-color: red");
            buttonpit2.setStyle("-fx-border-color: red");
            buttonpit3.setStyle("-fx-border-color: red");
            buttonpit4.setStyle("-fx-border-color: red");
            buttonpit5.setStyle("-fx-border-color: red");
            labelpit6.setStyle("-fx-border-color: red");
            buttonpit7.setStyle("-fx-border-color: green");
            buttonpit8.setStyle("-fx-border-color: green");
            buttonpit9.setStyle("-fx-border-color: green");
            buttonpit10.setStyle("-fx-border-color: green");
            buttonpit11.setStyle("-fx-border-color: green");
            buttonpit12.setStyle("-fx-border-color: green");
            labelpit13.setStyle("-fx-border-color: green");
        }
        checkGameOver(gameBoard);
        return;
    }

    /**
     * method return frequency of power-ups and special stones used in arcade
     */
    public void incrementFrequencyValue(){
        this.frequencyOfPowerUpsAndSpecialStone += 1;
    }


    /**
     * Initialises the Game, selects a first player,
     * and then displays the updated GUI.
     */
    @FXML
    Label firstName1, firstName2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        User player1 = LoginControllerT2.getLoggedInPlayer(1);
        User player2 = LoginControllerT2.getComputer();
        ArcadeSinglePlayer arcadeSinglePlayer = new ArcadeSinglePlayer(4, player1, player2);
        resetBoard();
        displayBoard();

        firstName1.setText(player1.getFirstName());
        firstName2.setText(player2.getFirstName());
        username1.setText(player1.getUserName());
        username2.setText(player2.getUserName());
        gameover.setText("");

        this.player1 = player1;
        this.player2 = player2;
        this.computersValidMoves = new ArrayList<>();
        firstPlayer();

        invalidTurnMessage.setText("");
    }

    @FXML
    Button continueTurnOn1;
    @FXML
    Button continueTurnOn2;
    @FXML
    Button doublePointsOn1;
    @FXML
    Button doublePointsOn2;

    /**
     * Method to switch control of the GUI from GameSinglePlayer to Meny.
     * @param event - Action event to get JavaFXML resources
     */
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setWidth(900);
        stage.setHeight(500);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

        System.out.println("You are now viewing the Menu");
    }

    /**
     * Methods to activate power-up buttons.
     * Initiates JavaFXML resources
     * returns buttons to user and applies to player turn if selected.
     */
    @FXML
    public void continueTurnOn1(ActionEvent e) throws IOException {
        continueTurnButton1 = true;
        continueTurnOn1.setStyle("-fx-border-color: red");
    }

    public void continueTurnOn2(ActionEvent e) throws IOException {
        continueTurnButton2 = true;
        continueTurnOn2.setStyle("-fx-border-color: red");
    }

    public void doublePointsOn1(ActionEvent e) throws IOException{
        doublePointsButton1 = true;
        doublePointsOn1.setStyle("-fx-border-color: red");
    }

    public void doublePointsOn2(ActionEvent e) throws IOException{
        doublePointsButton2 = true;
        doublePointsOn2.setStyle("-fx-border-color: red");
    }
}

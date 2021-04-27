package sample;

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
import java.util.ResourceBundle;

/**
 * This class is used to run the Single player traditional mode.
 * @author Kieren;
 */

public class GameMultiPlayer implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Board gameBoard;
    private static User player1;
    private static User player2;
    public boolean firstRound;
    public int stones;

    /**
     * Default constructor, Constructs a GameMultiPlayer which can be called with no arguments.
     */
    public GameMultiPlayer() {
        gameBoard = new Board();
        player1 = new User();
        player2 = new User();
    }

    /**
     * Constructor for GameMultiPlayer that allows you to specify the
     * starting number of stones in each pit.
     * @param stonesPerPit Number of stones each player will start with in each pit.
     */
    public GameMultiPlayer(int stonesPerPit) {
        gameBoard = new Board(stonesPerPit);
        player1 = new User();
        player2 = new User();
    }

    /**
     * Constructor for gameSinglePlayer. Allows you to specify the 2 players
     * and the amount of starting stones per pit.
     * @param stonesPerPit Number of stones each player will start with in each pit.
     * @param player1 A User, player1
     * @param player2 A User, player2
     */
    public GameMultiPlayer(int stonesPerPit, User player1, User player2) {
        gameBoard = new Board(stonesPerPit);
        this.player1 = player1;
        this.player2 = player2;
    }
    /**
     * Resets the gameBoard. Puts 4 stones in each pit, and 0 in each
     * players store. Then displays the Board on the GUI.
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

    @FXML
    ImageView imageview2;

    @FXML
    Label username1;

    @FXML
    Label username2;

    /**
     * Method to display the GUI. Displays the gameBoard on the GUI, displays a
     * players profile picture and shows which players turn it is.
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
     * Method for player1's 4st pit button. Gets player input and starts a turn.
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
     * Method for player2's 4th pit button. Gets player input and starts a turn.
     */
    public void buttonpit10() {
        validMove(gameBoard, false, 3);
    }
    /**
     * Method for player2's 5th pit button. Gets player input and starts a turn.
     */
    public void buttonpit11() {
        validMove(gameBoard, false, 4);
    }
    /**
     * Method for player2's 6th pit button. Gets player input and starts a turn.
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
     * Checks if the game is over. If it is it ends the game, Updates the GUI and updates WinLossDraw.
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
     *Checks if a given move is valid on a given turn. If it is it will execute the
     * move starting from a particular pit. If not a relevant error message is displayed.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */


    public void validMove(Board gameBoard, Boolean player1Side, int pitPressed) {

        if (player1.isCurrentTurn) {
            if (player1Side && pitPressed <= 5) {
                if(gameBoard.getPitValue(true,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    makeMove(gameBoard, true, pitPressed);
                    invalidTurnMessage.setText("");
                }
            }
            else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
                invalidTurnMessage.setText("Pit is invalid. Please choose a pit on your side.");
            }
        }
        else{
            if ((!player1Side) && pitPressed <= 5) {
                if(gameBoard.getPitValue(false,pitPressed) == 0) {
                    invalidTurnMessage.setText("Please pick a pit with stones in!");
                }
                else{
                    makeMove(gameBoard, false, pitPressed);
                    invalidTurnMessage.setText("");
                }
            }
            else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
                invalidTurnMessage.setText("Pit is invalid. Please choose a pit on your side.");

            }
        }
    }

    /**
     * Selects a player to start the game. 50% chance.
     * Updates GUI to display whose turn.
     */
    public void firstPlayer(){
        if(Math.random()>0.5){
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

            //MAKE MOVE TODO
        }


    }


    /**
     * Executes the algorithm for a players turn. After a players turn
     * it will make a move for the computer.
     * @param gameBoard The current state of the board.
     * @param player1Side Boolean to check which side the pit was on.
     * @param pitPressed The starting pit.
     */
    public void makeMove(Board gameBoard, Boolean player1Side, int pitPressed) {

        firstRound = true;
        stones = gameBoard.getPitValue(player1Side, pitPressed);
        gameBoard.setPitValue(player1Side, pitPressed, 0);


        while(stones>0) {

            if (!firstRound) {

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
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
                    stones--;
                    System.out.println("Stones in hand = " + stones);

                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);
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
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag F");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                            System.out.println("Flag G");
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stones>0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {
                            System.out.println("Flag H");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
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
     * Initialises the Game, selects a first player,
     * and then displays the updated GUI.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User player1 = LoginControllerT2.getLoggedInPlayer(1);
        User player2 = LoginControllerT2.getLoggedInPlayer(2);
        GameMultiPlayer gameMultiPlayer = new GameMultiPlayer(4, player1, player2);
        resetBoard();

        Image profilepic1 = new Image(getClass().getResourceAsStream("/" + player1.getUserName() + ".jpg"),150,150,false,false);
        imageview1.setImage(profilepic1);

        Image profilepic2 = new Image(getClass().getResourceAsStream("/" + player2.getUserName() + ".jpg"),150,150,false,false);
        imageview2.setImage(profilepic2);

        username1.setText(player1.getFirstName());
        username2.setText(player2.getFirstName());
        gameover.setText("");

        this.player1 = player1;
        this.player2 = player2;

        firstPlayer();

        invalidTurnMessage.setText("");
    }
    /**
     * Method to switch control of the GUI from GameSinglePlayer to Meny.
     */
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setWidth(720);
        stage.setHeight(720);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        System.out.println("You are now viewing the Menu");
    }


}


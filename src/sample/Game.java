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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Game implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Board gameBoard;
//    private int playerNumber1;
//    private int playerNumber2;
//    public boolean player1Turn;
    public boolean firstRound;
    public int stones;
    
    //Declaration of player variables
    private Player player1;
    private Player player2;

    public Game() {
        gameBoard = new Board();
//        playerNumber1 = 1;
//        playerNumber2 = 2;
        
        //Initializing Player Objects
        player1 = new Player();
        player2 = new Player();
    }

    public Game(int stones) {
        gameBoard = new Board(stones);
//        playerNumber1 = 1;
//        playerNumber2 = 2;
        
      //Initializing Player Objects
        player1 = new Player();
        player2 = new Player();
    }

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

    }

    @FXML
    public Label labelpit0, labelpit1, labelpit2, labelpit3, labelpit4, labelpit5, labelpit6, labelpit7, labelpit8, labelpit9, labelpit10, labelpit11, labelpit12, labelpit13;

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
    }

    @FXML
    public Button buttonpit0, buttonpit1,buttonpit2,buttonpit3,buttonpit4,buttonpit5,buttonpit7,buttonpit8,buttonpit9,buttonpit10,buttonpit11,buttonpit12;

    public void buttonpit0() {
        validMove(gameBoard, true, 0);
    }
    public void buttonpit1() {
        validMove(gameBoard, true, 1);
    }
    public void buttonpit2() {
        validMove(gameBoard, true, 2);
    }
    public void buttonpit3() {
        validMove(gameBoard, true, 3);
    }
    public void buttonpit4() {
        validMove(gameBoard, true, 4);
    }
    public void buttonpit5() {
        validMove(gameBoard, true, 5);
    }
    public void buttonpit7() {
        validMove(gameBoard, false, 0);
    }
    public void buttonpit8() {
        validMove(gameBoard, false, 1 );
    }
    public void buttonpit9() {
        validMove(gameBoard, false, 2);
    }
    public void buttonpit10() {
        validMove(gameBoard, false, 3);
    }
    public void buttonpit11() {
        validMove(gameBoard, false, 4);
    }
    public void buttonpit12() {
        validMove(gameBoard, false, 5);
    }

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
        }
    }



    @FXML
    Label turnMessage;

    public void validMove(Board gameBoard, Boolean player1Side, int pitPressed) {

        if (player1.isCurrentTurn) {
            if (player1Side && pitPressed <= 5) {
                makeMove(gameBoard, true, pitPressed);
            }
            else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        }
        else if (!player1.isCurrentTurn){
            if (!player1Side && pitPressed <= 5) {
                makeMove(gameBoard, false, pitPressed);
            } else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        }
    }

    public void firstPlayer(){
        if(Math.random()>0.5){
        	player1.isCurrentTurn = true;
            turnMessage.setText("It is player 1s turn");
        }
        else{
            player1.isCurrentTurn = false;
            turnMessage.setText("It is player 2s turn");

        }


    }

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


            else if (player1.isCurrentTurn && firstRound && stones>0) {

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
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if(player1.isCurrentTurn){
            turnMessage.setText("It is player 1s turn");
        }
        else{
            turnMessage.setText("It is player 2s turn");
        }
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Game game = new Game(4);
        resetBoard();
        displayBoard();
        firstPlayer();
    }

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


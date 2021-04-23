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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Game implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Board gameBoard;
    private int playerNumber1;
    private int playerNumber2;
    public boolean currentPlayer1;
    private boolean running;



    public Game() {
        gameBoard = new Board();
        playerNumber1 = 1;
        playerNumber2 = 2;
        running = true;
    }

    public Game(int stones) {
        gameBoard = new Board(stones);
        playerNumber1 = 1;
        playerNumber2 = 2;
        running = true;

    }

    public void resetBoard() {

        gameBoard.getPlayer1Side().getHole(0).setPitValue(4);
        gameBoard.getPlayer1Side().getHole(1).setPitValue(4);
        gameBoard.getPlayer1Side().getHole(2).setPitValue(4);
        gameBoard.getPlayer1Side().getHole(3).setPitValue(4);
        gameBoard.getPlayer1Side().getHole(4).setPitValue(4);
        gameBoard.getPlayer1Side().getHole(5).setPitValue(4);
        gameBoard.getPlayer1Store().setPitValue(0);
        gameBoard.getPlayer2Side().getHole(0).setPitValue(4);
        gameBoard.getPlayer2Side().getHole(1).setPitValue(4);
        gameBoard.getPlayer2Side().getHole(2).setPitValue(4);
        gameBoard.getPlayer2Side().getHole(3).setPitValue(4);
        gameBoard.getPlayer2Side().getHole(4).setPitValue(4);
        gameBoard.getPlayer2Side().getHole(5).setPitValue(4);
        gameBoard.getPlayer2Store().setPitValue(0);

        displayBoard();

        System.out.println("You have displayed a new Board");

    }

    @FXML
    public Label pit1, pit2, pit3, pit4, pit5, pit6, pit7, pit8, pit9, pit10, pit11, pit12, pit13, pit14;

    public void displayBoard() {
        pit1.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(0).getPitValue()));
        pit2.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(1).getPitValue()));
        pit3.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(2).getPitValue()));
        pit4.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(3).getPitValue()));
        pit5.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(4).getPitValue()));
        pit6.setText(String.valueOf(gameBoard.getPlayer1Side().getHole(5).getPitValue()));
        pit7.setText(String.valueOf(gameBoard.getPlayer1Store().getPitValue()));
        pit8.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(0).getPitValue()));
        pit9.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(1).getPitValue()));
        pit10.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(2).getPitValue()));
        pit11.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(3).getPitValue()));
        pit12.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(4).getPitValue()));
        pit13.setText(String.valueOf(gameBoard.getPlayer2Side().getHole(5).getPitValue()));
        pit14.setText(String.valueOf(gameBoard.getPlayer2Store().getPitValue()));
        System.out.println("You have displayed a new Board");
    }

    @FXML
    public Button button1, button2, button3, button4, button5, button6, button7, button8,button9,button10,button11,button12,button13,button14;

    public void button1() {
        validMove(gameBoard, currentPlayer1, 1);
    }
    public void button2() {
        validMove(gameBoard, currentPlayer1, 2);
    }
    public void button3() {
        validMove(gameBoard, currentPlayer1, 3);
    }
    public void button4() {
        validMove(gameBoard, currentPlayer1, 4);
    }
    public void button5() {
        validMove(gameBoard, currentPlayer1, 5);
    }
    public void button6() {
        validMove(gameBoard, currentPlayer1, 6);
    }
    public void button7() {
        validMove(gameBoard, currentPlayer1, 7);
    }
    public void button8() {
        validMove(gameBoard, currentPlayer1, 8);
    }
    public void button9() {
        validMove(gameBoard, currentPlayer1, 9);
    }
    public void button10() {
        validMove(gameBoard, currentPlayer1, 10);
    }
    public void button11() {
        validMove(gameBoard, currentPlayer1, 11);
    }
    public void button12() {
        validMove(gameBoard, currentPlayer1, 12);
    }
    public void button13() {
        validMove(gameBoard, currentPlayer1, 13);
    }
    public void button14() {
        validMove(gameBoard, currentPlayer1, 14);
    }

    public void validMove(Board gameBoard, Boolean currentPlayer1, int buttonPressed) {

        if (currentPlayer1) {
            if (buttonPressed <= 6) {
                makeMove(gameBoard,currentPlayer1,buttonPressed);
            } else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        }
        else {
            if (buttonPressed > 7 && buttonPressed <=13) {
                makeMove(gameBoard, currentPlayer1,buttonPressed);
            } else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        }
    }

    public void checkGameOver(Board gameBoard){

        if (gameBoard.player1win()){
            System.out.println("Player 1 Wins");
        }
        if (gameBoard.player2win()){
            System.out.println("Player 2 Wins");
        }
        else return;
    }



    @FXML
    Label turnMessage;

    public void makeMove(Board gameBoard, boolean currentPlayer, int buttonPressed) {

        if(currentPlayer) {

            int stones = gameBoard.getPitValue(currentPlayer, buttonPressed);
            gameBoard.setPitValue(currentPlayer, buttonPressed, 0);
            System.out.println("Amount of stones = " + stones);

            if (stones > 0) {

                for (int i = buttonPressed + 1; i <= 6; i++) {
                    gameBoard.incrementPitValue(currentPlayer, i);
                    stones--;
                    if (stones == 0) {
                        stones = gameBoard.getPitValue(currentPlayer, i-1);
                        gameBoard.setPitValue(currentPlayer, i-1, 0);
                        if (stones == 0) {break;}
                    }
                }
                if (stones > 0) {
                    gameBoard.getPlayer1Store().incrementPitValue();
                    stones--;
                    if (stones == 0) {
                        checkGameOver(gameBoard);
                        displayBoard();
                        return;
                    }
                }
                if (stones > 0) {
                    for (int i = 0; i <= 6; i++) {
                        gameBoard.incrementPitValue(!currentPlayer, i+1);
                        stones--;
                        if (stones == 0) {
                            stones = gameBoard.getPitValue(!currentPlayer, i+1);
                            gameBoard.setPitValue(!currentPlayer, i+1, 0);

                            if (stones == 0) {break;}
                        }
                    }
                }
            }

        }
        else{

            int stones = gameBoard.getPitValue(!currentPlayer, buttonPressed-8);
            gameBoard.setPitValue(!currentPlayer, buttonPressed-8, 0);
            System.out.println("Amount of stones = " + stones);

            if (stones > 0) {
                for (int i = buttonPressed - 7; i <= 6; i++) {
                    gameBoard.incrementPitValue(!currentPlayer, i);
                    stones--;
                    if (stones == 0) {
                        stones = gameBoard.getPitValue(!currentPlayer, i-1);
                        gameBoard.setPitValue(!currentPlayer, i-1, 0);

                        if (stones == 0) {break;}
                    }
                }
                if (stones > 0) {
                    gameBoard.getPlayer2Store().incrementPitValue();
                    stones--;
                    if (stones == 0) {
                        checkGameOver(gameBoard);
                        displayBoard();
                        return;
                    }
                }

                if (stones > 0) {
                    for (int i = 0; i <= 6; i++) {
                        gameBoard.incrementPitValue(currentPlayer, i+1);
                        stones--;
                        if (stones == 0) {
                            stones = gameBoard.getPitValue(currentPlayer, i+1);
                            gameBoard.setPitValue(currentPlayer, i+1, 0);
                            if (stones == 0) {break;}
                        }
                    }
                }

            }
        }


        currentPlayer1 = !currentPlayer1;
        checkGameOver(gameBoard);
        System.out.println(currentPlayer1);
        displayBoard();



        if(currentPlayer1){
            turnMessage.setText("It is player 1s turn");
        }
        else{
            turnMessage.setText("It is player 2s turn");
        }
        return;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetBoard();
        displayBoard();
        Game game = new Game(4);
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


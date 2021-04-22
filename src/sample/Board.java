package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Board  {

    private Side Player1Side;
    private Side Player2Side;
    Hole Player1Store = new Hole();
    Hole Player2Store = new Hole();

    public Board() {
        Player1Side = new Side(1,4);
        Player2Side = new Side(2,4);
        Hole Player1Store = new Hole(0);
        Hole Player2Store = new Hole(0);
    }

    public Board(int stones) {
        Player1Side = new Side(1, stones);
        Player2Side = new Side(2, stones);
        Hole Player1Store = new Hole();
        Hole Player2Store = new Hole();
    }

    public int getPitValue(boolean currentPlayer1, int pitNumber){
        if(currentPlayer1){
            if (pitNumber<=6) {
                return Player1Side.getHole(pitNumber - 1).getPitValue();
            }
            else return Player1Store.getPitValue();
        }
        else{
             if (pitNumber <= 6) {
                 return Player2Side.getHole(pitNumber-1).getPitValue();
             }
             else return Player2Store.getPitValue();
            }
    }

    public void setPitValue(boolean currentPlayer1, int pitNumber, int value){
        if(currentPlayer1){
            if (pitNumber<7) {
                Player1Side.getHole(pitNumber - 1).setPitValue(value);
            }
            else Player1Store.setPitValue(value);
        }
        else{
            if (pitNumber<7) {
                Player2Side.getHole(pitNumber - 1).setPitValue(value);
            }
            else Player2Store.setPitValue(value);
        }
    }

    public void incrementPitValue(boolean currentPlayer1, int pitNumber){
        if(currentPlayer1){
            if (pitNumber<7) {
                Player1Side.getHole(pitNumber - 1).incrementPitValue();
            }
            else Player1Store.incrementPitValue();
        }
        else{
            if (pitNumber<7) {
                Player2Side.getHole(pitNumber - 1).incrementPitValue();
            }
            else Player2Store.incrementPitValue();
        }
    }

    public boolean player1win(){

        boolean flag = true;

        for (int i = 0; i < 6 ; i++) {
            if(getPlayer1Side().getHole(i).getPitValue() != 0){
                flag = false;
            }
        }
        return flag;
    }

    public boolean player2win(){

        boolean flag = true;

        for (int i = 0; i < 6 ; i++) {
            if(getPlayer2Side().getHole(i).getPitValue() != 0){
                flag = false;
            }
        }
        return flag;
    }

    public Side getPlayer1Side() {
        return Player1Side;
    }

    public Side getPlayer2Side() {
        return Player2Side;
    }

    public Hole getPlayer1Store() {
        return Player1Store;
    }

    public Hole getPlayer2Store() {
        return Player2Store;
    }
}

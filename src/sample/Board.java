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

    //field
    private Side Player1Side;
    private Side Player2Side;
    Hole Player1Store = new Hole();
    Hole Player2Store = new Hole();

    //constructor
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

    //methods
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

    public int getPitValue(boolean Player1, int pitNumber){
        if(Player1){
            if (pitNumber<=5) {
                return Player1Side.getPit(pitNumber).getPitValue();
            }
            else return Player1Store.getPitValue();
        }
        else{
             if (pitNumber <= 12) {
                 return Player2Side.getPit(pitNumber).getPitValue();
             }
             else return Player2Store.getPitValue();
            }
    }

    public void setPitValue(boolean Player1, int pitNumber, int value){
        if(Player1){
            if (pitNumber<= 5) {
                Player1Side.getPit(pitNumber).setPitValue(value);
            }
            else Player1Store.setPitValue(value);
        }
        else{
            if (pitNumber<=12) {
                Player2Side.getPit(pitNumber).setPitValue(value);
            }
            else Player2Store.setPitValue(value);
        }
    }

    public void incrementPitValue(boolean Player1, int pitNumber){
        if(Player1){
            if (pitNumber<=5) {
                Player1Side.getPit(pitNumber).incrementPitValue();
            }
            else Player1Store.incrementPitValue();
        }
        else{
            if (pitNumber<=5) {
                Player2Side.getPit(pitNumber).incrementPitValue();
            }
            else Player2Store.incrementPitValue();
        }
    }

    public boolean player1win(){

        boolean flag = true;

        for (int i = 0; i <= 5 ; i++) {
            if(getPlayer1Side().getPit(i).getPitValue() != 0){
                flag = false;
            }
        }
        return flag;
    }

    public boolean player2win(){

        boolean flag = true;

        for (int i = 0; i <= 5 ; i++) {
            if(getPlayer2Side().getPit(i).getPitValue() != 0){
                flag = false;
            }
        }
        return flag;
    }


}

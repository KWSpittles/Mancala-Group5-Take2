package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Arcade extends GameMultiPlayer {

    //field
    public boolean halfHandTri;
    public boolean reverseTurnTri;
    public boolean switchSideTri;
    public boolean continueTurnTri;
    public boolean doublePointTri;
    private int frequencyOfPowerUpsAndSpecialStone;

    // re-declaration of player variables
    private User player1;
    private User player2;


    public boolean player1ContinueTurn;
    public boolean player2ContinueTurn;
    public boolean player1DoublePoint;
    public boolean player2DoublePoint;

    private boolean player2PowerUps;
    private boolean moveAntiClockwise;
    private int stoneTriggerProb;


    //constructor
    public Arcade() {
        this.frequencyOfPowerUpsAndSpecialStone = 0;
        player1 = new User();
        player2 = new User();

    }



    //assets
    @FXML
    Label SpecialStoneMessage;
    Label PowerUpsMessage;


    //methods

    public void validMove(Board gameBoard, boolean player1Side, int pitPressed) {

        if (player1.isCurrentTurn) {
            if (player1Side && pitPressed <= 5 && Math.random() >= 0.1 && Math.random() <= 0.55) {
                continueTurn(gameBoard, true, pitPressed);
                continueTurnTri = true;
                PowerUpsMessage.setText("A continue turn power-ups has been used.");
            } else if (player1Side && pitPressed <= 5 && Math.random() > 0.55) {
                doublePointTri = true;
                PowerUpsMessage.setText("A continue turn power-ups has been used.");

            } else if (player1Side && pitPressed <= 5 && Math.random() <= 0.033) {
                halfHand(gameBoard, true, pitPressed);
                halfHandTri = true;
                SpecialStoneMessage.setText("A Half hand special stone has been triggered.");
            } else if (player1Side && pitPressed <= 5 && Math.random() > 0.033 && Math.random() <= 0.066) {
                reverseTurn(gameBoard, true, pitPressed);
                reverseTurnTri = true;
                SpecialStoneMessage.setText("A reverse turn special stone has been triggered.");
            } else if (player1Side && pitPressed <= 5 && Math.random() <= 0.99) {
                switchSideTri = true;
                SpecialStoneMessage.setText("A switch sides special stone has been triggered.");

            } else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        } else {
            if (pitPressed <= 5 && Math.random() >= 0.1 && Math.random() <= 0.55) {
                continueTurn(gameBoard, false, pitPressed);
                continueTurnTri = true;
                PowerUpsMessage.setText("A continue turn power-ups has been used.");
            } else if (pitPressed <= 5 && Math.random() > 0.55) {
                doublePointTri = true;
                PowerUpsMessage.setText("A continue turn power-ups has been used.");
            } else if (pitPressed <= 5 && Math.random() <= 0.033) {
                halfHand(gameBoard, false, pitPressed);
                halfHandTri = true;
                SpecialStoneMessage.setText("A Half hand special stone had been triggered.");
            } else if (pitPressed <= 5 && Math.random() > 0.033 && Math.random() <= 0.066) {
                reverseTurn(gameBoard, false, pitPressed);
                reverseTurnTri = true;
                SpecialStoneMessage.setText("A reverse turn special stone had been triggered.");
            } else if (pitPressed <= 5 && Math.random() <= 0.99) {
                switchSideTri = true;
                SpecialStoneMessage.setText("A switch sides special stone had been triggered.");

            } else {
                System.out.println("Pit is invalid. Please choose a pit on your side.");
            }
        }
    }


    // POWER-UPS

    //option for player to choose select power up
    public boolean playerPowerUps() {

        return false;
    }

    public void continueTurn(Board gameBoard, boolean player1Side, int pitPressed) {

        stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set
        firstRound = true;
        continueTurnTri = true;
        incrementFrequencyValue();


        while (stones > 0) {
            if (!firstRound && stones > 0) {                                    //... , player1SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 1) {
                            System.out.println("FLAG A");                                           //***
                            stones = gameBoard.getPitValue(true, i + 1) ;                   //continue chance
                            gameBoard.setPitValue(player1Side, i + 1, 0);
                            if (!firstRound && stones > 0) {
                                for (int j = 0; j <= 5; j++) {
                                    gameBoard.incrementPitValue(true, j);
                                    stones--;
                                    if (stones == 0) {
                                        if (gameBoard.getPlayer1Side().getPit(j).getPitValue() == 1) {
                                            System.out.println("FLAG A");
                                            break;                                                      //no more chance
                                        } else {
                                            stones = gameBoard.getPitValue(true, j);                  //get pit value
                                            gameBoard.setPitValue(player1Side, j, 0);                   //reset the pit to 0
                                            System.out.println("FLAG B");
                                        }
                                    }
                                }
                            }
                        } else {
                            stones = gameBoard.getPitValue(true, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                            System.out.println("FLAG B");
                        }
                    }
                }
            }

            else if (player1.isCurrentTurn && firstRound) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    System.out.println("Stones in hand = " + stones);

                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {              //next round *****
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1.isCurrentTurn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                stones--;
                if (stones == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }


            if (!firstRound && stones > 0) {                                    //... , player2SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 1) {
                            System.out.println("Flag F");
                            stones = gameBoard.getPitValue(false, i + 1);                       //continue chance
                            gameBoard.setPitValue(player1Side, i + 1, 0);
                            if (!firstRound && stones > 0) {
                                for (int j = 0; j <= 5; j++) {
                                    gameBoard.incrementPitValue(false, j);
                                    stones--;
                                    if (stones == 0) {
                                        if (gameBoard.getPlayer1Side().getPit(j).getPitValue() == 1) {
                                            System.out.println("Flag F");
                                            break;                                                      //no more chance
                                        } else {
                                            stones = gameBoard.getPitValue(false, j);                  //get pit value
                                            gameBoard.setPitValue(player1Side, j, 0);                   //reset the pit to 0
                                            System.out.println("Flag G");
                                        }
                                    }
                                }
                            }
                        } else {
                            stones = gameBoard.getPitValue(false, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                        }
                    }
                }
            }

            else if (!player1.isCurrentTurn && firstRound && stones>0) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 1) {              //next round
                            System.out.println("Flag H");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);           // auto-continue
                            System.out.println("Flag I");
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && !player1.isCurrentTurn) {                                  // stone -> P1 store
                gameBoard.getPlayer2Store().incrementPitValue();
                stones--;
                if (stones == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag J");
                    return;
                }
                //stone -1 to store, continue...
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

    //double all point for one single turn
    public void doublePoints(Board gameBoard, boolean player1Side, int pitPressed) {
        stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set

        firstRound = true;
        doublePointTri = true;
        incrementFrequencyValue();

        while (stones > 0) {
            if (!firstRound) {                                    //... , player1SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i)== 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                            System.out.println("FLAG B");
                        }
                    }
                }
            } else if (player1.isCurrentTurn && firstRound) {               //..., pitPressed

                for (int i = pitPressed + 1; i <= 5; i++) {
                    System.out.println(i);
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    System.out.println("Stones in hand = " + stones);

                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {              //next round
                            System.out.println("FLAG C");
                            break;
                        } else {
                            System.out.println("FLAG D");
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1.isCurrentTurn) {
                gameBoard.getPlayer1Store().incrementPitValue2();                   //*2
                stones--;
                if (stones == 0) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("Flag E");
                    return;
                }
            }

            if (!firstRound && stones > 0) {                                           //...player2SidePit-auto

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

            else if (!player1.isCurrentTurn && firstRound & stones > 0) {

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
                gameBoard.getPlayer2Store().incrementPitValue2();                           //*2
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


    // SPECIAL STONES

//    //notify and activate special stone by 3.33% each
//    public int stoneTriggerProb(){
//
//        if (Math.random() <= 0.033){
//            halfHandTri = true;
//            SpecialStoneMessage.setText("A Half hand special stone had been triggered.");
//        } else if (Math.random() > 0.033 && Math.random() <=0.066 ){
//            reverseTurnTri = true;
//            SpecialStoneMessage.setText("A reverse turn special stone had been triggered.");
//        }else if (Math.random() > 0.066 && Math.random() <=0.99 ){
//            switchSideTri = true;
//            SpecialStoneMessage.setText("A switch sides special stone had been triggered.");
//
//        }
//
//        return stoneTriggerProb() ;
//    }

    //reduce pick up point by half and round up if odd number
    public void halfHand(Board gameBoard, boolean player1Side, int pitPressed) {
        stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        firstRound = true;
        halfHandTri = true;
        incrementFrequencyValue();

        while (stones > 0) {
            stones = stones / 2;

            if (!firstRound) {                                              //...player1Turn

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 1) {
                            System.out.println("FLAG A");
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);    //get
                            gameBoard.setPitValue(player1Side, i, 0);                //set
                            System.out.println("FLAG B");
                        }
                    }
                }
            } else if (player1.isCurrentTurn && firstRound) {

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

            if (!firstRound && stones > 0) {

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
            } else if (!player1.isCurrentTurn && firstRound && stones > 0) {

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



    //player next move change to clockwise position
    public void reverseTurn(Board gameBoard, boolean player1Side, int pitPressed) {
        stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set
        reverseTurnTri = true;
        firstRound = true;
        incrementFrequencyValue();

        while (stones > 0) {
            if (!firstRound ) {
                for (int i = 5; i >= 0; i--) {
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);    //get
                            gameBoard.setPitValue(player1Side, i, 0);    //set
                        }
                    }
                }
            } else if (player1.isCurrentTurn && firstRound) {               //..., pitPressed
                for (int i = pitPressed + 6; i >= 0; i--) {
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

            if (!firstRound & stones > 0) {                                           //...player2SidePit-auto

                for (int i = 5; i >= 0; i--) {
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

                for (int i = pitPressed + 6; i >=0; i++) {
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

    //player return all current stone to the pit and take the opposite pit/position
    public void switchSides(Board gameBoard, boolean player1Side, int pitPressed) {
        int stones = gameBoard.getPitValue(player1Side, pitPressed);    //get

        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set

        firstRound = true;
        switchSideTri = true;
        incrementFrequencyValue();

//        player1Turn = !player1Turn;                                             //player1 turn goes to player2
        //player1 <-> player2


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


    //method return frequency of power-ups and special stones used in arcade
    public void incrementFrequencyValue(){
        this.frequencyOfPowerUpsAndSpecialStone += 1;
    }


}
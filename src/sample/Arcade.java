package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Arcade extends Game {

    //field
    public boolean halfHandTri;
    public boolean reverseTurnTri;
    public boolean switchSideTri;
    public boolean continueTurnTri;
    public boolean doublePointTri;
    private int frequencyOfPowerUpsAndSpecialStone;


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

    }

    //assets
    @FXML
    Label SpecialStoneMessage;
    Label PowerUpsMessage;


    //methods

    public void validMove(Board gameBoard, boolean player1Side, int pitPressed) {

        if (player1Turn) {
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

        int stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set
        firstRound = true;
        continueTurn();
        player2PowerUps = true;
        incrementFrequencyValue();


        while (stones > 0) {
            if (!firstRound && stones > 0) {                                    //... , player1SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            stones = gameBoard.getPitValue((true, i + 1))                         //continue chance
                            gameBoard.setPitValue(player1Side, i + 1, 0);
                            if (!firstRound && stones > 0) {
                                for (int i = 0; i <= 5; i++) {
                                    gameBoard.incrementPitValue(true, i);
                                    stones--;
                                    if (stones == 0) {
                                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                                            break;                                                      //no more chance
                                        } else {
                                            stones = gameBoard.getPitValue(true, i);                  //get pit value
                                            gameBoard.setPitValue(player1Side, i, 0);                   //reset the pit to 0
                                        }
                                    }
                                }
                            }
                        } else {
                            stones = gameBoard.getPitValue(true, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                        }
                    }
                }
            } else if (player1Turn && firstRound && stones > 0) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 0) {              //next round *****
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1Turn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                if (stones == 1) {                                                     //last 1 stone-> store
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;                                                   //stone -1 to store, continue...
            }
        }

        while (stones > 0) {
            if (!firstRound && stones > 0) {                                    //... , player2SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            stones = gameBoard.getPitValue((false, i + 1))                         //continue chance
                            gameBoard.setPitValue(player1Side, i + 1, 0);
                            if (!firstRound && stones > 0) {
                                for (int i = 0; i <= 5; i++) {
                                    gameBoard.incrementPitValue(false, i);
                                    stones--;
                                    if (stones == 0) {
                                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                                            break;                                                      //no more chance
                                        } else {
                                            stones = gameBoard.getPitValue(false, i);                  //get pit value
                                            gameBoard.setPitValue(player1Side, i, 0);                   //reset the pit to 0
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
            } else if (player1Turn && firstRound && stones > 0) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {              //next round
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1Turn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                if (stones == 1) {                                                     //last 1 stone-> store
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;                                                   //stone -1 to store, continue...
            }
        }
        player1Turn = !player1Turn;
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if (player1Turn) {
            turnMessage.setText("It is player 1s turn");
        } else {
            turnMessage.setText("It is player 2s turn");
        }
        return;
    }

    //double all point for one single turn
    public void doublePoints(Board gameBoard, boolean player1Side, int pitPressed) {
        int stones = gameBoard.getPitValue(player1Side, pitPressed);    //get

        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set

        firstRound = true;
        doublePointTri = true;
        incrementFrequencyValue();

        while (stones > 0) {
            if (!firstRound && stones > 0) {                                    //... , player1SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                        }
                    }
                }
            } else if (player1Turn && firstRound && stones > 0) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 0) {              //next round
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1Turn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue2();             // *2 point ***** ,score/ stone?
                if (stones == 1) {                                                     //last 1 stone-> store
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;                                                   //stone -1 to store, continue...
            }

            if (!firstRound & stones > 0) {                                           //...player2SidePit-auto

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
            } else if (!player1Turn && firstRound & stones > 0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && !player1Turn) {                                       // stone -> P1 store
                gameBoard.getPlayer2Store().incrementPitValue2();                   // *2 point ***** ,score/ stone?
                if (stones == 1) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;
            }
        }

        player1Turn = !player1Turn;
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if (player1Turn) {
            turnMessage.setText("It is player 1s turn");
        } else {
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
        int stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);

        firstRound = true;
        halfHandTri = true;
        incrementFrequencyValue();

        while (stones > 0 && halfHandTri) {
            stones = stones / 2;
            if (!firstRound && stones > 0) {                                   //...player1Turn
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);    //get
                            gameBoard.setPitValue(player1Side, i, 0);                //set
                        }
                    }
                }
            } else if (player1Turn && firstRound && stones > 0) {               //...
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 0) {     //next round
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);   // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && player1Turn) {                                  //last stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                if (stones == 1) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;
            }

            if (!firstRound && stones > 0 && halfHandTri) {                                           //...player2Turn

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
            } else if (!player1Turn && firstRound & stones > 0 && halfHandTri) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && !player1Turn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                if (stones == 1) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;
            }

        }

        player1Turn = !player1Turn;
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if (player1Turn) {
            turnMessage.setText("It is player 1s turn");
        } else {
            turnMessage.setText("It is player 2s turn");
        }
        return;
    }


    //player next move change to clockwise position
    public void reverseTurn(Board gameBoard, boolean player1Side, int pitPressed) {
        int stones = gameBoard.getPitValue(player1Side, pitPressed);    //get
        gameBoard.setPitValue(player1Side, pitPressed, 0);        //set
        reverseTurnTri = true;
        firstRound = true;
        incrementFrequencyValue();

        while (stones > 0 && reverseTurnTri) {
            if (!firstRound && stones > 0) {
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
            } else if (player1Turn && firstRound && stones > 0) {               //..., pitPressed
                for (int i = pitPressed + 5; i >= 0; i--) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 0) {          //next round
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);         //auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1Turn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                if (stones == 1) {                                                     //last 1 stone-> store
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;                                                   //stone -1 to store, continue...
            }

            if (!firstRound & stones > 0) {                                           //...player2SidePit-auto

                for (int i = 5; i >= 0; i--) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
            } else if (!player1Turn && firstRound & stones > 0) {

                for (int i = pitPressed + 6; i >= 0; i--) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && !player1Turn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                if (stones == 1) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if (player1Turn) {
                        turnMessage.setText("It is player 1s turn");
                    } else {
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;
            }
        }

        player1Turn = !player1Turn;
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if (player1Turn) {
            turnMessage.setText("It is player 1s turn");
        } else {
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

        player1Turn = !player1Turn;                                             //player1 turn goes to player2
                                                                                //player1 <-> player2


        while(stones > 0) {
            if (!firstRound && stones > 0) {                                    //... , player1SidePits-auto
                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPlayer1Side().getPit(i).getPitValue() == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);           //get pit value
                            gameBoard.setPitValue(player1Side, i, 0);           //reset the pit to 0
                        }
                    }
                }
            }

            else if (player1Turn && firstRound && stones > 0) {               //..., pitPressed
                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(true, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(true, i) == 0) {              //next round
                            break;
                        } else {
                            stones = gameBoard.getPitValue(true, i);
                            gameBoard.setPitValue(true, i, 0);           // auto-continue
                        }
                    }
                }
                firstRound = !firstRound;                                     //->start second round
            }

            if (stones > 0 && player1Turn) {                                  // stone -> P1 store
                gameBoard.getPlayer1Store().incrementPitValue();
                if (stones == 1) {                                                     //last 1 stone-> store
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if(player1Turn){
                        turnMessage.setText("It is player 1s turn");
                    }
                    else{
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;                                                   //stone -1 to store, continue...
            }

            if (!firstRound & stones > 0) {                                           //...player2SidePit-auto

                for (int i = 0; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
            }

            else if (!player1Turn && firstRound & stones > 0) {

                for (int i = pitPressed + 1; i <= 5; i++) {
                    gameBoard.incrementPitValue(false, i);
                    stones--;
                    if (stones == 0) {
                        if (gameBoard.getPitValue(false, i) == 0) {
                            break;
                        } else {
                            stones = gameBoard.getPitValue(false, i);
                            gameBoard.setPitValue(false, i, 0);
                        }
                    }
                }
                firstRound = !firstRound;
            }

            if (stones > 0 && !player1Turn) {
                gameBoard.getPlayer2Store().incrementPitValue();
                if (stones == 1) {
                    checkGameOver(gameBoard);
                    displayBoard();
                    System.out.println("You get another go! XXXX");
                    if(player1Turn){
                        turnMessage.setText("It is player 1s turn");
                    }
                    else{
                        turnMessage.setText("It is player 2s turn");
                    }
                    return;
                }
                stones--;
            }
        }

        player1Turn = !player1Turn;
        checkGameOver(gameBoard);
        System.out.println(player1Side);
        displayBoard();


        if(player1Turn){
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
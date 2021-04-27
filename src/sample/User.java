package sample;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class User {

    private Date firstDate;
    private String userName;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String password;
    private String playerID;
    private int[] winLossDraw = new int[3]; //@@@
    private static int userCount = 0; //counter to track the number of users
    private ArrayList<User> favUsers;
    public boolean isCurrentTurn;
    private int currentScore;
    private int playerNumber;




    //default no argument constructor
    public User () {userCount++;}

    public User (String userName, String first, String last){
        this.userName = userName;
        firstName = first;
        lastName = last;

        userCount++; //adds one to the user count each time the constructor is called
    }

    public User (String userName, String first, String last, String url){
        this.userName = userName;
        firstName = first;
        lastName = last;
        this.setprofilePicture("url");

        userCount++; //adds one to the user count each time the constructor is called
    }

    //hei correct me if wrong shouldnt it be something like this?
//    public User(int id, String userName, String password, String firstName, String lastName, boolean admin) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.admin = admin;
//    }

    //method to add favourite player to an ArrayList
    public void addfavUser(User user) {
        favUsers.add(user);
    }

    //method to remove favourite player to an ArrayList
    public void removefavUser(User user) {
        favUsers.remove(user);
    }

    //Setters
    public void setUserName(String UserName) {
        this.userName = UserName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    //sets the password
    public void setPassword(String password) {
        this.password = password;
    }
    //set setPlayerID
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setprofilePicture(String profilePicture) {
        this.profilePicture = profilePicture ;
    }

    //@@@
    public void setWinLossDraw(String w, String l, String d) {
        int tw =  Integer.parseInt(w);
        int tl =  Integer.parseInt(l);
        int td =  Integer.parseInt(d);
        this.winLossDraw[0] = tw;
        this.winLossDraw[1] = tl;
        this.winLossDraw[2] = td;
    }

    //getters
    //get user name
    public String getUserName() {
        return userName;
    }

    //get first name
    public String getFirstName() {
        return firstName;
    }

    //get last name
    public String getLastName() {
        return lastName;
    }

    //get password
    public String getPassword() {
        return password;
    }
    //get playerID
    public String getplayerID() {
        return playerID;
    }

    //needs to retrieve this info from login system
    public Date getFirstDate() {
        return firstDate;
    }

    //gets the number of current users
    public static int getUserCount() {
        return userCount;
    }

    //@@@ gets setWinLossDraw
    public int getsWinLossDraw(int i) {
        return winLossDraw[i];
    }


    //toString method
    public String toString() {
        return ("Username :" + userName + "\nFirst name :" + firstName + "\nLast name :" + lastName + "\nWins/Losses/Draws :"
                + Arrays.toString(winLossDraw));
    }
}

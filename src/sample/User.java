package sample;

import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * The class illustrates a User profile
 * @author Julian Chan
 */
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

    /**
     * Create a no-argument constructor
     */
    public User () {userCount++;}
       
    /**
     * Create a User object with specified arguments
     * @param userName
     * @param first name
     * @param last name
     */
    public User (String userName, String first, String last){
        this.userName = userName;
        firstName = first;
        lastName = last;
        userCount++; //adds one to the user count each time the constructor is called
    }
        
    /**
     * Create a User object with specified arguments
     * @param username
     * @param first name
     * @param last name
     * @param URL of profile picture
     */
    public User (String userName, String first, String last, String url){
        this.userName = userName;
        firstName = first;
        lastName = last;
        this.setprofilePicture("url");

        userCount++; //adds one to the user count each time the constructor is called
    }

    /**
     * Method to add favourite players
     * @param User
     */
    public void addfavUser(User user) {
        favUsers.add(user);
    }

    /**
     * Method to remove favourite players
     * @param User
     */
    public void removefavUser(User user) {
        favUsers.remove(user);
    }

    /**
     * Set the username
     * @param username
     */
    public void setUserName(String UserName) {
        this.userName = UserName;
    }
    
    /**
     * Set the first name
     * @param first name
     */
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    /**
     * Set the last name
     * @param first name
     */
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the last name
     * @param first name
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Set the unique player ID
     * @param player ID
     */
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    
    /**
     * Set the URL String of the profile picture
     * @param URL String of the profile picture
     */
    public void setprofilePicture(String profilePicture) {
        this.profilePicture = profilePicture ;
    }

    /**
     * Set the wins,losses and draws for a user
     * @param wins
     * @param losses
     * @param draws
     */
    public void setWinLossDraw(String w, String l, String d) {
        int tw =  Integer.parseInt(w);
        int tl =  Integer.parseInt(l);
        int td =  Integer.parseInt(d);
        this.winLossDraw[0] = tw;
        this.winLossDraw[1] = tl;
        this.winLossDraw[2] = td;
    }
    
    /**
     * Method to add and update the wins, losses and
     * draws for users
     * @param integer representing wins,losses or draws
     */
    public void addWinLossDraw(int i) {
    	int[] temp = getWinLossDraw();
    	temp[i] += 1;
    	winLossDraw = temp;
    }
    
    /**
     * Get the wins,losses and draws of a user
     * draws for players
     * @return wins,losses,draws of a user
     */
    public int[] getWinLossDraw() {
    	return winLossDraw;
    }
    
    /**
     * Get the wins/losses/draws
     * @param integer representing wins/losses/draws
     * @return wins/losses/draws of a user
     */
    public int getsWinLossDraw(int i) {
        return winLossDraw[i];
    }

    /**
     * Get the username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the password
     * @return password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Get the unique player ID
     * @return player ID
     */
    public String getplayerID() {
        return playerID;
    }
    
    //needs to retrieve this info from login system
    public Date getFirstDate() {
        return firstDate;
    }
    
    /**
     * Get the number of users in the system
     * @return number of users
     */
    public static int getUserCount() {
        return userCount;
    }

    //toString method
    public String toString() {
        return ("Username :" + userName + "\nFirst name :" + firstName + "\nLast name :" + lastName + "\nWins/Losses/Draws :"
                + Arrays.toString(winLossDraw));
    }
}

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
     * @param userName String variable
     * @param first String for user first name
     * @param last String for user last name
     * @param url A String to show the URL of the profile image
     */
    public User (String userName, String first, String last, String url){
        this.userName = userName;
        firstName = first;
        lastName = last;
        this.setprofilePicture("url");

        userCount++; //adds one to the user count each time the constructor is called
    }

    /**
     * Method allows adding user to favourites.
     * @param user Takes a user type
     */
    public void addfavUser(User user) {
        favUsers.add(user);
    }

    /**
     * Method allows to remove favourite players
     * @param user Takes a user type
     */
    public void removefavUser(User user) {
        favUsers.remove(user);
    }

    /**
     * Sets the username of the player from user data
     * @param UserName - String to store variable username
     */
    public void setUserName(String UserName) {
        this.userName = UserName;
    }
    
    /**
     * Sets the first name of the player from user data
     * @param firstName String type
     */
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    /**
     * Sets the last name of the player from the user data
     * @param lastName String type
     */
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the password for the user from the user data
     * @param password String type
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Sets a unique player ID to the user data to determine when player one or player 2
     * and aacts as user primary key
     * @param playerID is a string type
     */
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    
    /**
     * Method to set the profile picture of a user to connect with the user data.
     * Stores as a url to the image in the CSV file.
     * @param profilePicture string type stores from the URL
     */
    public void setprofilePicture(String profilePicture) {
        this.profilePicture = profilePicture ;
    }

    /**
     * Set the wins,losses and draws for a user based on game outcome
     * @param w - 'Wins' String type
     * @param l - 'Loss' String type
     * @param d - 'draws' String type
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
     * draws for users based on gameplay results.
     * @param i Integer type stores wins, losses and draws with user data.
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
     * @param i - Integer representing wins/losses/draws
     * @return integer for wins/losses/draws of a user
     */
    public int getsWinLossDraw(int i) {
        return winLossDraw[i];
    }

    /**
     * Gets the username for current user
     * @return String - username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the first name for the user
     * @return String - first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name for the user
     * @return String - last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the password for the user
     * @return password as String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Get the unique player ID for the user
     * @return String value player ID
     */
    public String getplayerID() {
        return playerID;
    }
    
    //needs to retrieve this info from login system
    public Date getFirstDate() {
        return firstDate;
    }
    
    /**
     * Get the amount of current users
     * @return number of users as an integer
     */
    public static int getUserCount() {
        return userCount;
    }

    /**
     * Method to write strings ot array
     * @return String of all player data as array
     */
    public String toString() {
        return ("Username :" + userName + "\nFirst name :" + firstName + "\nLast name :" + lastName + "\nWins/Losses/Draws :"
                + Arrays.toString(winLossDraw));
    }
}

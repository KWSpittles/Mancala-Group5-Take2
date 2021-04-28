package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class to apply log in methods for all gamemodes
 *
 * @author Beth Pawlin
 * @author Julian Chan
 * @author Hei
 */
public class LoginControllerT2 {

	/**
	 * String variable to hold the user password
	 */
	public String inputPassword;
	/**
	 * String variable stores user Id
	 */
	public String inputId;
	/**
	 * Image variable to store the user's profile picture
	 */
	private BufferedImage profilePic;
	/**
	 * Variable to store url of user profile image
	 */
	private URL imageURL;
	/**
	 * String variable to store output of image URL
	 */
	private String outputURL;

	/**
	 * Storing the user info to a CSV file
	 */
	public static final String USERS_CSV_FILE = "src" + File.separator + "UserInfo.csv";

	/**
	 * Storing the user info as a hashmap
	 */
	public static HashMap<String, String> userLoginInfo = new HashMap<String, String>();
	/**
	 * String to store the user details
	 */
	public static String userInfo = "";

	/**
	 * Users loggeed in are stored as an array list
	 * New array is initiated to create list
	 */
	private static final ArrayList<User> loggedInUsers = new ArrayList<>();
	/**
	 * All registered users are stored as an array list
	 */
	private static ArrayList<User> registeredUsers;

	/**
	 * Computer type user is stored
	 * Hardcoded user details for single player mode
	 */
	private static final User computer = new User("Computer","Computer","Computer","src/Computer.jpg");

	@FXML
	PasswordField pwField = new PasswordField();
	@FXML 
	TextField idField = new TextField();
	@FXML
	TextField passwordField = new TextField();
	@FXML
	TextField userNameField = new TextField();
	@FXML
	TextField firstNameField = new TextField();
	@FXML
	TextField lastNameField = new TextField();
	@FXML
	Label subtitle = new Label();

	/**
	 * FXML
	 */
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Takes new username and password as String types
	 * Uses getters to take and store text fields
	 * @param e - Action event test if login successful
	 * @return loginAttempt
	 */
	public boolean readIdPassWord (ActionEvent e) {
		String username = String.valueOf(idField.getText());
		String password = String.valueOf(pwField.getText());

		return loginAttempt(username, password, subtitle);
	}

	/**
	 * Method to test auth of user when logging in
	 * Takes username and tests if previous user
	 * 		If username is not found in csv then error is returned
	 *
	 * If password field is left blank an error is returned
	 *
	 * Tests if password field matches user password in csv
	 * 		If password matches - login is successful - Else an error is returned
	 *
	 * Checks login status of user logging in - Error is returned if user is already logged into game
	 *
	 * If all checks pass then log in is successful and the username is added to the loggedInUsers array
	 * @param username - String type username field
	 * @param password - String type from password field
	 * @param errorLabel - Label - Presents error message to the user
	 * @return - Return false if login attempts fail above method - Presents error to the user.
	 * @return - Returns true if all checks pass - User added to logged in array
	 */
	public static boolean loginAttempt(String username, String password, Label errorLabel) {
		User userAccount = getUserByUsername(username);

		// If user not found, login failed
		if(userAccount == null || username.equals("Username")) {
			System.err.println("Login attempt failed: user not found");
			errorLabel.setText("User account not found");
			return false;
		}

		// If no password entered, login failed
		if(password.isBlank()) {
			System.err.println("Login attempt failed: no password entered");
			errorLabel.setText("Please enter your password");
			return false;
		}

		// If password does not match, login failed
		if(!userAccount.getPassword().equals(password)) {
			System.err.println("Login attempt failed: incorrect password");
			errorLabel.setText("Incorrect password");
			return false;
		}

		// Check if they're already signed in
		for(User user : loggedInUsers) {
			if(user.getUserName().equals(username)) {
				System.err.println("Login attempt failed: this user is already signed in");
				errorLabel.setText("This account is already signed in");
				return false;
			}
		}

		// Otherwise, login successful
		System.out.println("User " + username + " logged in");
		userInfo = username;

		// Add user to logged in users ArrayList
		addToLoggedInUsersList(userAccount);
		return true;
	}

	/**
	 * Method to create a new user account
	 * Takes in string getter variables from user input
	 * Method checks user data is not already stored in csv file
	 * Method doesn't allow for blank fields
	 * All user info is written to file and presented as successful to the user
	 * User is then redirected to the game
	 * @param e - ActionEvent - Storing user profile image to file
	 * @throws IOException
	 * @return - Error returned if checks not met
	 */
	public void createAccount(ActionEvent e) throws IOException {
		
//		String epw = String.valueOf(pwFieldc.getText());
//		String eid = String.valueOf(idFieldc.getText());

		// Get input from sign-up form to save in CSV file
		String firstName = firstNameField.getText();
		String lastName  = lastNameField.getText();
		String username  = userNameField.getText();
		String password  = passwordField.getText();
		uploadImage(e, username);
		String profilePic = outputURL;

		// Check that data was entered
		if(username.isBlank() || password.isBlank()) {
			// Username or password was not supplied
			subtitle.setText("You must choose a username and password");
			return;
		}

		// Check if user with same username already exists
		User userCheck = getUserByUsername(username);
		if(userCheck != null) {
			// User with this username already exists, so don't
			// create a new account with same username.
			subtitle.setText("An account with this username already exists");
			return;
		}

		System.out.println("--- Creating Account ---");
		System.out.println("First name: " + firstName);
		System.out.println("Last name: " + lastName);
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println();

		try {
			System.out.print("Appending new user to CSV file... ");

			File file = new File(USERS_CSV_FILE);
			String data = "";

			if(!file.exists()) {
				// If users CSV file does not already exist, create it and append column headings
				var newFileCreated = file.createNewFile();
				if(newFileCreated) {
					data += "First name,Last Name,Username,Password\n";
				}
			}

			FileWriter writer = new FileWriter(file, true);
			BufferedWriter buffer = new BufferedWriter(writer);

			data += firstName + "," +
					lastName + "," +
					username + "," +
					password + "," +
					profilePic + "\n";

			buffer.write(data);
			buffer.close();

			System.out.println("done!");
			switchToLoginPage(e);
		}
		catch(Exception ignored) {
			System.err.println("Error creating new user");
		}
	}

	/**
	 * Processing player login information
	 * Reads username and password and redirects to the main menu if matches data in CSV
	 * @param event - Checks String variables set for username and password
	 * @throws IOException
	 */
	//Action event for login form submit
	public void processPlayerLogin(ActionEvent event) throws IOException {
		if(readIdPassWord(event)) {
			switchToPlayerMenu(event);
		}
	}

	/**
	 * Switches to main menu after processPlayerLogin is successful
	 * @param event - Initialises FXML resources for main menu
	 * @throws IOException
	 */
	//Action event to switch to player menu
	public void switchToPlayerMenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.centerOnScreen();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * Method to switch to sign up page
	 * @param event - Initiates FXML resources for the sign up pages
	 * @throws IOException
	 */
	//Action event to switch to sign up page
	public void switchToSignUpPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.centerOnScreen();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println("Showing create account page");
	}

	/**
	 * Method to switch to login page at game start
	 * Also initiated after player 1 logout
	 * @param event - Initiates FXML resources
	 * @throws IOException
	 */
	//Action event to switch to login page
	public void switchToLoginPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.centerOnScreen();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println("Showing login page");
	}

	/**
	 * This method loads the stored user information from the CSV file
	 * All users in CSV are stored in the array list registeredUsers
	 * The method returns all fields from the array list
	 * An error is returned if data cannot be accessed.
	 */
	public static void loadUserInfoFromCsv() {
		System.out.print("Loading user data from CSV file...");
		registeredUsers = new ArrayList<>();
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(USERS_CSV_FILE));
			while((line=reader.readLine())!=null) {
				String[] row = line.split(",");
				ArrayList<String> data = new ArrayList<>(Arrays.asList(row));
				User user = new User();
				user.setfirstName(data.get(0));
				user.setlastName(data.get(1));
				user.setUserName(data.get(2));
				user.setPassword(data.get(3));
				user.setprofilePicture(data.get(4));
				registeredUsers.add(user);
			}
			System.out.println(" done!");
		} catch(Exception error) {
			System.out.println(" error.");
			System.err.println(error.getMessage());
		}
	}

	/**
	 * This method adds the user to the logged in users array after sign in
	 * Checks if user is already logged in and doesn't duplicate in array if they are
	 * @param user -Takes the String parameter user
	 */
	public static void addToLoggedInUsersList(User user) {
		// If already in logged in users list, do nothing
		for(User x : loggedInUsers) {
			if(x.getUserName().equals(user.getUserName())) {
				return;
			}
		}
		// Add to logged in users list
		loggedInUsers.add(user);
	}

	/**
	 * This method clears all users from loggedInUsers array list after signing out of game
	 */
	public static void signOutAllUsers() {
		loggedInUsers.clear();
	}

	/**
	 * This method takes a String 'username' and checks if it is a registered user in the CSV file
	 * @param username - String type variable
	 * @return
	 */
	public static User getUserByUsername(String username) {
		loadUserInfoFromCsv();

		for(User user : registeredUsers) {
			if(user.getUserName().equals(username)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Method to get player ID number from position in array list
	 * Checks array index from the current logged in users
	 * @param playerNumber - Integer type to apply if player 1 or 2
	 * @return integer based on position in array index
	 * 			Player1 or player2
	 */
	public static User getLoggedInPlayer(int playerNumber) {
		if(loggedInUsers.size() >= playerNumber) {
			int arrayIndex = playerNumber - 1;
			return loggedInUsers.get(arrayIndex);
		}
		return null;
	}

	/**
	 * Method to get computer user for single player mode
	 * @return - the computer user with hardcoded player data
	 */
	public static User getComputer(){
		return computer;
	}

	/**
	 * Method to upload a profile image when creating a new account
	 * This accesses player's user files and allows for a PNG or JPG file input
	 * Stored with the user information in the CSV file
	 * @param event - ActionEvent - Choose image from file
	 * @param userName - Takes string type 'username' to store profile image with in CSV
	 */
	//Method upload profile picture from directory, and stores images in a chosen directory
    public void uploadImage(ActionEvent event, String userName) {

        FileChooser fileChooser = new FileChooser();
        //Extension of image filters
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog to choose picture
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
			try {
				profilePic = ImageIO.read(file.toURI().toURL());
				imageURL = file.toURI().toURL();
				System.out.println(file.toURI());
				File outputfile = new File("src\\" + userName + ".jpg");
				//outputURL = new File("src\\" + userName + ".jpg").toURI().toURL();
				outputURL = "src\\" + userName + ".jpg";
				ImageIO.write(profilePic, "jpg", outputfile);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
}
	

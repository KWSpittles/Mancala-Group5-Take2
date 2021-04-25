package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;


import javafx.stage.Stage;


import java.awt.image.BufferedImage;


import java.io.*;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;

import javax.imageio.ImageIO;

public class LoginControllerT2 {

	public String inputPassword;
	public String inputId;
	private BufferedImage profilePic;
	private URL imageURL;

	public static final String USERS_CSV_FILE = "src" + File.separator + "UserInfo.csv";


	public static HashMap<String, String> userLoginInfo = new HashMap<String, String>();
	public static String userInfo = ""; // TODO what is this for?

	private static final ArrayList<User> loggedInUsers = new ArrayList<>();
	private static ArrayList<User> registeredUsers;

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

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public boolean readIdPassWord (ActionEvent e) {
		String username = String.valueOf(idField.getText());
		String password = String.valueOf(pwField.getText());

		return loginAttempt(username, password, subtitle);
	}

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
		userInfo = username; // TODO what is this for?

		// Add user to logged in users ArrayList
		addToLoggedInUsersList(userAccount);
		return true;
	}

	public void createAccount(ActionEvent e) throws IOException {
		
//		String epw = String.valueOf(pwFieldc.getText());
//		String eid = String.valueOf(idFieldc.getText());

		// Get input from sign-up form to save in CSV file
		String firstName = firstNameField.getText();
		String lastName  = lastNameField.getText();
		String username  = userNameField.getText();
		String password  = passwordField.getText();
		uploadImage(e);
		URL profilePic = imageURL;

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
					profilePic.toString() + "\n";

			buffer.write(data);
			buffer.close();

			System.out.println("done!");
			switchToLoginPage(e);
		}
		catch(Exception ignored) {
			System.err.println("Error creating new user");
		}
	}
	

	//Action event to switch to player menu
	public void switchToPlayerMenu(ActionEvent event) throws IOException {
		if(readIdPassWord(event)){
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.centerOnScreen();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
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

	public static void signOutAllUsers() {
		loggedInUsers.clear();
	}

	public static User getUserByUsername(String username) {
		loadUserInfoFromCsv();

		for(User user : registeredUsers) {
			if(user.getUserName().equals(username)) {
				return user;
			}
		}

		return null;
	}

	public static User getLoggedInPlayer(int playerNumber) {
		if(loggedInUsers.size() >= playerNumber) {
			int arrayIndex = playerNumber - 1;
			return loggedInUsers.get(arrayIndex);
		}
		return null;
	}
	
	
    public void uploadImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
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
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {

			try {
				profilePic = ImageIO.read(file.toURI().toURL());
				imageURL = file.toURI().toURL();
				System.out.println(file.toURI());
				File outputfile = new File("src\\ProfilePic\\profilepic.jpg");
				ImageIO.write(profilePic, "jpg", outputfile);

			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        
    }
	
		
}
	

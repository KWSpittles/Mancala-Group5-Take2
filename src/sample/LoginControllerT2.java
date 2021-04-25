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
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginControllerT2 {
	public String inputPassword;
	public String inputId;

	public static HashMap<String, String> userLoginInfo = new HashMap<String, String>();
	public static String userInfo = "";
	private User newUser;
	private ArrayList<User> userList;
	
	@FXML 
	PasswordField pwField = new PasswordField();
	@FXML 
	TextField idField = new TextField();
	@FXML
	TextField password = new TextField();
	@FXML
	TextField userName = new TextField();
	@FXML
	TextField firstName = new TextField();
	@FXML
	TextField lastName = new TextField();
	@FXML
	Label passwordLabel = new Label();
	@FXML
	Label subtitle = new Label();

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void loadLoginInfo (ActionEvent e) {
		userLoginInfo.put("id1", "p1");
		System.out.println("loaded LoginInfo");
		
		BufferedReader reader = null;
		String file = "src\\loginInfo.csv";
		String line = "";
		String[] idPw = new String[2];
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null) {
				String[] row = line.split(",");
				int i = 0;
				for (String index : row) {
					//System.out.printf("%-10s",index);
					idPw[i]=index;
					i++;
				}
				userLoginInfo.put(idPw[0], idPw[1]);
			}
		} catch(Exception error) {
			System.err.println(error.getMessage());
		}
	}
	
	public void guestLogin (ActionEvent e) {
		subtitle.setText("Logged in as Guest");
	}
	
	public boolean readIdPassWord (ActionEvent e) {
		String epw = String.valueOf(pwField.getText());
		String eid = String.valueOf(idField.getText());
		
		loadLoginInfo(e);
		
		if(idField.getText().isBlank()) {
			System.out.println("User ID is blank");
			subtitle.setText("Please enter your username");
		}
		else if (userLoginInfo.containsKey(eid)) {
			System.out.println("User with this user ID exists: " + eid);
			if(pwField.getText().isBlank()) {
				subtitle.setText("Please enter your password");
				return false;
			}
			else if (userLoginInfo.get(eid).equals(epw)) {
				System.out.println("User " + eid + " logged in");
				subtitle.setText("Logged in");
				userInfo = eid;
				return true;
			}
		}
		System.out.println("Login attempt failed");
		subtitle.setText("Incorrect username or password");
		return false;
	}

	public void createAccount(ActionEvent e) {
		//Initializing User object
		newUser = new User();
		
		//Setting the user fields 
		newUser.setUserName(userName.getText());
		newUser.setPassword(password.getText());
		newUser.setfirstName(firstName.getText());
		newUser.setlastName(lastName.getText());
		//newUser.setprofilePicture();

		//Applying getter methods to store variables
		String firstname = newUser.getfirstName();
		String lastname = newUser.getlastName();
		String eid = newUser.getUserName();		
		String epw = newUser.getPassword();
		
		
//		String epw = String.valueOf(pwFieldc.getText());
//		String eid = String.valueOf(idFieldc.getText());
		try {
			//loadLoginInfo (e);
			String loginFile = "src\\loginInfo.csv";
			String userFile = "src\\UserInfo.csv";
			
			//Writer for login information
			FileWriter fw = new FileWriter(loginFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pwr = new PrintWriter(bw);
			
			//Writer for user information
			FileWriter fw1 = new FileWriter(userFile,true);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			PrintWriter pwr1 = new PrintWriter(bw1);
			
			//Writing to csv
			pwr.println(eid + "," + epw);
			pwr.flush();
			pwr.close();
			
			pwr1.println(firstname + "," + lastname + "," + eid + "," + epw);
			pwr1.flush();
			pwr1.close();
			//System.out.println(eid+","+epw);
			//System.out.println(userLoginInfo);
			switchToLoginPage(e);
		}
		catch(Exception ignored) { }
	}
	
	//Action event to switch to player menu
	public void switchToPlayerMenu(ActionEvent event) throws IOException {
		System.out.println(readIdPassWord(event));
		if(readIdPassWord(event)){
			root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.centerOnScreen();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			//Game game = new Game(4);
			System.out.println("You are now playing a game of Mancala! Enjoy");
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
		//Game game = new Game(4);
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
		//Game game = new Game(4);
		System.out.println("Showing login page");
	}
}
	

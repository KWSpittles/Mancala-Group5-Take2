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
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.util.ArrayList;

public class LoginControllerT2 {
	public String inputPassword;
	public String inputId;
	private BufferedImage profilePic;
	private URL imageURL;

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
		}catch(Exception error) {
			System.out.println(error);
		}
	}
	
	public void guestLogin (ActionEvent e) {
		subtitle.setText("meow~~you login as Guest");
	}
	
	public boolean readIdPassWord (ActionEvent e) {
		String epw = String.valueOf(pwField.getText());
		String eid = String.valueOf(idField.getText());
		
		loadLoginInfo(e);
		
		if(idField.getText().isBlank()) {
			System.out.println("please enter userId");
			subtitle.setText("please enter userId");
		}
		else if (userLoginInfo.containsKey(eid)) {
			userInfo = eid;
			System.out.println("yeaaaaa ID correct "+"as "+userInfo);
			if(pwField.getText().isBlank()) {
				System.out.println("please enter password");
				subtitle.setText("please enter password");
			}
			else if (userLoginInfo.get(eid).equals(epw)) {
				System.out.println("yeaaaaa boi login success");
				subtitle.setText("yeaaaaa boi login success");
				return true;
			}
			else if (userLoginInfo.get(eid).equals(epw)==false){
				System.out.println("please enter correct password");
				subtitle.setText("please enter correct password");
			}
		}
		else if (userLoginInfo.containsKey(eid)==false){
			System.out.println("please enter correct userId");
			subtitle.setText("");
		}
		else {
			System.out.println("you are shitty code mockey");
		}
		return false;
	}

	public void createAccount(ActionEvent e) throws IOException {
		//Initializing User object
		newUser = new User();
		
		//Setting the user fields 
		newUser.setUserName(userName.getText());
		newUser.setPassword(password.getText());
		newUser.setfirstName(firstName.getText());
		newUser.setlastName(lastName.getText());
		uploadImage(e);
		
		
		//Applying getter methods to store variables
		String firstname = newUser.getFirstName();
		String lastname = newUser.getLastName();
		String eid = newUser.getUserName();		
		String epw = newUser.getPassword();
		URL profilePic = imageURL;
		
		
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
		catch(Exception Error2) {
			
		}
		
		
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
		System.out.println("You are now playing a game of Mancala! Enjoy");
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
		System.out.println("You are now playing a game of Mancala! Enjoy");
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
	

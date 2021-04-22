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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.*;
import java.util.HashMap;

public class LoginControllerT2 {
	public String inputPassword;
	public String inputId;

	public static HashMap<String, String> userLoginInfo = new HashMap<String, String>();
	
	@FXML 
	PasswordField pwField = new PasswordField();
	@FXML 
	TextField idField = new TextField();
	@FXML
	PasswordField pwFieldc = new PasswordField();
	@FXML
	TextField idFieldc = new TextField();
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
	
	public void readIdPassWord (ActionEvent e) throws IOException {
		String epw = String.valueOf(pwField.getText());
		String eid = String.valueOf(idField.getText());
		
		loadLoginInfo(e);
		
		if(idField.getText().isBlank()) {
			System.out.println("please enter userId");
			subtitle.setText("please enter userId");
		}
		else if (userLoginInfo.containsKey(eid)) {
			System.out.println("yeaaaaa ID correct");
			if(pwField.getText().isBlank()) {
				System.out.println("please enter password");
				subtitle.setText("please enter password");
			}
			else if (userLoginInfo.get(eid).equals(epw)) {
				System.out.println("yeaaaaa boi login success");
				subtitle.setText("yeaaaaa boi login success");

				switchToPlayerMenu(e);


			}
			else if (userLoginInfo.get(eid).equals(epw)==false){
				System.out.println("please enter correct password");
				subtitle.setText("please enter correct password");
			}
		}
		else if (idField.getText().equals(eid)==false){
			System.out.println("please enter correct userId");
			subtitle.setText("");
		}
		else {
			System.out.println("you are shitty code mockey");
		}
		
	}
	
	public void createAccount(ActionEvent e) {
		String epw = String.valueOf(pwFieldc.getText());
		String eid = String.valueOf(idFieldc.getText());
		try {
			loadLoginInfo (e);
			String file = "src\\loginInfo.csv";
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pwr = new PrintWriter(bw);

			pwr.println(eid+","+epw);
			pwr.flush();
			pwr.close();
			System.out.println(epw+","+eid);
			System.out.println(userLoginInfo);
		}
		catch(Exception Error2) {
			
		}
	}

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

	public void switchToPlayerMenu(ActionEvent event) throws IOException {
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
	

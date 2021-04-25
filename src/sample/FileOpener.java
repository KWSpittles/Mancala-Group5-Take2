package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileOpener{
	private BufferedImage profilePic;
	private URL imageURL;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//empty constructor for non-static references
	public FileOpener(){}
		
	public void pictureSelector() {
		stage.setTitle("File Opener");
		
		//filter to select the type of files to be viewed
		FileChooser.ExtensionFilter imageFilter
        = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(imageFilter);

		final Button openButton = new Button("Select Profile Picture");
		openButton.setOnAction((final ActionEvent e) -> {
			File file = fileChooser.showOpenDialog(stage);
			if (file != null) {

				try {
					profilePic = ImageIO.read(file.toURI().toURL());
					imageURL = file.toURI().toURL();
					System.out.println(file.toURI());
					File outputfile = new File("src\\profilepic.jpg");
					ImageIO.write(profilePic, "jpg", outputfile);

				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//	                ImageView ip = new ImageView(image1);
//	                BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
//	                BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			}
		});
		final StackPane stac = new StackPane();
		stac.getChildren().add(openButton);
		stage.setScene(new Scene(stac, 250, 250));
		stage.show();
	}
	
	//getter method for public access
	public BufferedImage getprofilePic() {
	    return profilePic;
	} 
	
	//getter for image URL
	public URL getimageURL() {
		return imageURL;
	}
	
	
//	public static void main(String[] args) {
//		Application.launch(args);
//		
//		
//	}
}

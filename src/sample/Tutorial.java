package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Tutorial{

    int currentImage = 1;

    @FXML
    ImageView myImageView;
    Button myButton;
    Button myButton1;

    public void nextImage(){
        currentImage++;

        if (currentImage == 10){
            currentImage = 1;
        }

        Image myImage = new Image(getClass().getResourceAsStream((currentImage) + ".png"));
        myImageView.setImage(myImage);
        System.out.println("current = " + currentImage);

    }

    public void prevImage() {
        currentImage--;

        if (currentImage == 0) {
            currentImage = 9;
        }

        Image myImage = new Image(getClass().getResourceAsStream((currentImage) + ".png"));
        myImageView.setImage(myImage);
        System.out.println("current = " + currentImage);

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setWidth(720);
        stage.setHeight(720);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        System.out.println("You are now viewing the Menu");
    }
}

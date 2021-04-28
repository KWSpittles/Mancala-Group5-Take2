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

/**
 * Class to implement additional feature of tutorial images
 * @author Kieran Spittles
 * @author Beth Pawlin - Digital drawings
 */
public class Tutorial{

    int currentImage = 1;

    @FXML
    ImageView myImageView;
    Button myButton;
    Button myButton1;

    /**
     * Method switches to next image in tutorial.
     * Switches to first image if the final image is reached and
     * prints the current image for the user to view.
     */
    public void nextImage(){
        currentImage++;

        if (currentImage == 10){
            currentImage = 1;
        }
        Image myImage = new Image(getClass().getResourceAsStream((currentImage) + ".png"));
        myImageView.setImage(myImage);
        System.out.println("current = " + currentImage);
    }

    /**
     * Method to switch to the previous image in the tutorial.
     * Displays current image to the user, returns to last image if
     * selected from the first.
     */
    public void prevImage() {
        currentImage--;

        if (currentImage == 0) {
            currentImage = 9;
        }
        Image myImage = new Image(getClass().getResourceAsStream((currentImage) + ".png"));
        myImageView.setImage(myImage);
        System.out.println("current = " + currentImage);
    }

    /**
     * Variables to hold JavaFXML resources
     */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Method switches user back to main menu from tutorial page.
     * Triggers after button press and initiates appropriate JavaFXML resources.
     * Alerts user to location in game at the end of the method.
     * @param event - Action Event - Initialises JavaFXML resources
     * @throws IOException
     */
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setWidth(900);
        stage.setHeight(500);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        System.out.println("You are now viewing the Menu");
    }
}

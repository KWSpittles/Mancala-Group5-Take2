package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane scenePane;

    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        scene.getStylesheets().add("sample/game.css");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        System.out.println("You are now playing a game of Mancala! Enjoy");
    }

    public void setUpMultiplayerGame(ActionEvent event) throws IOException {
        // Prompt player 2 to log in


        // Then start multiplayer game


        System.out.println("You are now playing a multiplayer game of Mancala! Enjoy");
    }

    public void switchToLeaderboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are logging out");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
            System.out.println("You have logged out successfully");
        }
    }

    public void switchToTutorial(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Tutorial.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setWidth(1220);
        stage.setHeight(1080);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setScene(scene);
        Tutorial tutorial = new Tutorial();
        stage.show();
        System.out.println("You are now in Tutorial mode! Enjoy");
    }

    public void switchToSignInPage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging out");
        alert.setHeaderText("You are logging out!");
        alert.setContentText("You'll be taken to Sign In window, are you sure?");

        LoginControllerT2.signOutAllUsers();

        if (alert.showAndWait().get() == ButtonType.OK) {
            root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.centerOnScreen();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}

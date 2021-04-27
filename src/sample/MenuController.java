package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public Label subtitle;
    public TextField usernameField;
    public PasswordField passwordField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane scenePane;

    public void switchToMultiPlayerGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameMultiPlayer.fxml"));
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

    public void switchToArcadeMultiPlayerGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ArcadeMultiPlayer.fxml"));
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


    public void switchToSinglePlayerGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameSinglePlayer.fxml"));
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

//    public void switchToArcadeSinglePlayerGame(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("ArcadeSinglePlayer.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.centerOnScreen();
//        scene = new Scene(root);
//        scene.getStylesheets().add("sample/game.css");
//        stage.setScene(scene);
//        stage.setWidth(1920);
//        stage.setHeight(1080);
//        stage.centerOnScreen();
//        stage.setResizable(false);
//        stage.show();
//        System.out.println("You are now playing a game of Mancala! Enjoy");
//    }

    public void setUpMultiplayerGame(ActionEvent event) throws IOException {
        // Check if there is already a player 2 logged in
        User player2 = LoginControllerT2.getLoggedInPlayer(2);

        if(player2 == null) {
            // Prompt player 2 to log in. On successful login, run
            // this method again and player2 won't be null anymore
            // and the game will start.
            player2SignInPage(event);
        } else {
            // Player 2 is already logged in, start the game
            switchToMultiPlayerGame(event);
        }
    }

    public void setUpArcadeMultiplayerGame(ActionEvent event) throws IOException {
        // Check if there is already a player 2 logged in
        User player2 = LoginControllerT2.getLoggedInPlayer(2);

        if(player2 == null) {
            // Prompt player 2 to log in. On successful login, run
            // this method again and player2 won't be null anymore
            // and the game will start.
            player2ArcadeSignInPage(event);
        } else {
            // Player 2 is already logged in, start the game
            switchToArcadeMultiPlayerGame(event);
        }
    }

    public void setUpSingleplayerGame(ActionEvent event) throws IOException {
        // Check if there is already a player 2 logged in

        switchToSinglePlayerGame(event);

    }

    public void switchToLeaderboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
        LeaderBoardController.loadData(event);
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

        if (alert.showAndWait().get() == ButtonType.OK) {
            LoginControllerT2.signOutAllUsers();

            root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.centerOnScreen();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void player2SignInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("player2SignInPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void player2ArcadeSignInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("player2ArcadeSignInPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void player2SignInSubmit(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loggedIn = LoginControllerT2.loginAttempt(username, password, subtitle);
        if(loggedIn) {
            setUpMultiplayerGame(event);
        }
    }
    public void player2ArcadeSignInSubmit(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loggedIn = LoginControllerT2.loginAttempt(username, password, subtitle);
        if(loggedIn) {
            setUpArcadeMultiplayerGame(event);
        }
    }

    public void switchToSignUpPage(ActionEvent event) throws IOException {
        new LoginControllerT2().switchToSignUpPage(event);
    }

    public void switchToPlayerMenu(ActionEvent event) throws IOException {
        new LoginControllerT2().switchToPlayerMenu(event);
    }
}

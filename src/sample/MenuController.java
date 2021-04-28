package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class to control game navigation from main menu.
 * Implements initialization of various game modes and access to sign-in pages
 * applies set FXML resources
 *
 * @author Beth Pawlin
 * @author Julian
 */
public class MenuController implements Initializable {

    /**
     * Setting variables for FXML resources
     */
    public Label subtitle;
    public TextField usernameField;
    public PasswordField passwordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane scenePane;

    /**
     * Switches to the multiplayer mode from main menu.
     * Applies FXML for multiplayer mode
     * @param event - Initialising FXML resources
     * @throws IOException
     */
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

    /**
     * Switches to multiplayer game in arcade mode
     * Applies FXML
     * @param event - Initialising FXML resources for arcade multiplayer
     * @throws IOException
     */
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

    /**
     * Switches to single player gamemode in traditional mode
     * Applies FXML
     * @param event - Initialising FXML resources for single player traditional
     * @throws IOException
     */
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

    /**
     * Switches to arcade gamemode in single player version
     * Applies FXML
     * @param event - Initialises and applies FXML resources
     * @throws IOException
     */
    public void switchToArcadeSinglePlayerGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ArcadeSinglePlayer.fxml"));
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

    /**
     * Prompts second user login at start of multiplayer mode
     * Detects if player 2 is logged in
     * Switches to signup page if player 2 login is null
     * Method runs again and initialises game if no longer null
     * @param event - Initialise FXML content and disply sign up pages or the game
     * @throws IOException
     */
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

    /**
     * Prompts second user login at initialising of arcade multiplayer mode
     * Detects if player 2 is logged in
     * If player 2 signed in is null then prompted to log in/sign up
     * Method runs again and initialises game if no longer null
     * @param event - Initialise FXML resource for sign in pages or game
     * @throws IOException
     */
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

    /**
     * Initiate the single player game
     * Player 2 detected as AI component
     * @param event - Switch to single mode game with FXML resources
     * @throws IOException
     */
    public void setUpSingleplayerGame(ActionEvent event) throws IOException {
        // Check if there is already a player 2 logged in
        switchToSinglePlayerGame(event);
    }

    /**
     * Initiate single player game for arcade mode
     * Player 2 is AI component
     * @param event - Switch to arcade game in single mode with FXML resources
     * @throws IOException
     */
    public void setUpArcadeSingleplayerGame(ActionEvent event) throws IOException {
        // Check if there is already a player 2 logged in
        switchToArcadeSinglePlayerGame(event);
    }

    /**
     * Switches to the leaderboard
     * @param event - Initiates FXML resources for the leaderboard
     * @throws IOException
     */
    public void switchToLeaderboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the tutorial demonstration
     * Alerts user of tutorial mode
     * @param event - Initiates tutorial FXML resources
     * @throws IOException
     */
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

    /**
     * Switches back to initial sign up page after log out
     * Alerts the user they are logging out and waits for response
     * If button selected then user is signed out and FXML resources applied to return to sign in
     * @param event - If sign out selected from log out function then user re-directed to sign in page
     *              FXML resources applied
     * @throws IOException
     *
     */
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

    /**
     * Initialising FXML resources for player 2 sign in
     * @param event - Get FXML resources for player 2 sign in
     *              Applies to switching to sign up in multiplayer modes
     * @throws IOException
     */
    public void player2SignInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("player2SignInPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialising FXML resources for player 2 sign in for arcade
     * @param event - Get FXML resources for player 2 sign in
     *              Applied when switching to arcade mode multiplayer
     * @throws IOException
     */
    public void player2ArcadeSignInPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("player2ArcadeSignInPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Detects if player 2 is signed in for single player traditional mode
     * Detects a boolean value for AI user login settings
     * Applied to player 2, and does not redirect to login page
     * Game initiates if returned true
     * @param event - If boolean returns true from LoginControllerT2 for player 2 log in status:
     *              Applies FXML resources for single player gamemode
     * @throws IOException
     */
    public void player2SignInSubmitTraditional(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loggedIn = LoginControllerT2.loginAttempt(username, password, subtitle);
        if(loggedIn) {
            setUpMultiplayerGame(event);
        }
    }

    /**
     * Detects if player 2 is signed in for single player arcade mode
     * Detects a boolean value for AI user login settings
     * Applied to player 2 and does not redirect to login page
     * Multiplayer can initiate
     * @param event - If boolean returns true from LoginControllerT2 for player 2 log in status:
     *              Applies FXML resources for single player arcade gamemode
     * @throws IOException
     */
    public void player2SignInSubmitArcade(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean loggedIn = LoginControllerT2.loginAttempt(username, password, subtitle);
        if(loggedIn) {
            setUpArcadeMultiplayerGame(event);
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

    /**
     * Method to switch to initial sign in for player one
     * Initiated before access to main menu
     * @param event - Initiating resources for FXML sign in page; player one.
     * @throws IOException
     */
    public void switchToSignUpPage(ActionEvent event) throws IOException {
        new LoginControllerT2().switchToSignUpPage(event);
    }

    /**
     * Method to switch to initial menu controller page
     * Switches to player menu if player one logs in successfully
     * @param event - Initiates FXML resources for menu controller if successful login attempt
     * @throws IOException
     */
    public void switchToPlayerMenu(ActionEvent event) throws IOException {
        new LoginControllerT2().switchToPlayerMenu(event);
    }



    /**
     * Initialising menu controls and initial GUI at startup.
     * @param url
     * @param resourceBundle - Extending FXML resources
     */
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

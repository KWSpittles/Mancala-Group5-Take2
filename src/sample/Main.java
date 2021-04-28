package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is used to Run the program.
 * Launches the GUI.
 * @author Kieren Spittles
 *
 */
public class Main extends Application {

    /**
     * Main method. Launches program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launches and customises the GUI, then displays the log in page.
     */
    @Override
    public void start(Stage stage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("main.css").toExternalForm();
            stage.setTitle("Mancala");
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image("https://cdn.asp.events/CLIENT_CL_Gamin_A45C4908_5056_B725_6B2249A7AD85625A/sites/iGB-Live-2020/media/libraries/exhibitors/3315D8BD-C9E8-F899-A2A622859897DC57-logo.jpg.png"));
            stage.setWidth(900);
            stage.setHeight(500);
            stage.setResizable(true);
            stage.centerOnScreen();
            //      stage.setFullScreen(true);
            //      stage.setFullScreenExitHint("Press 1 to escape :)");
            //      stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("1"));
            stage.setScene(scene);
            stage.show();

            System.out.println("Mancala is running");

            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows you to log out after displaying a confirmation box.
     * @param stage The GUI.
     */
    public void logout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Closing Mancala");
        alert.setContentText("Are you sure you want to exit the game?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }
}

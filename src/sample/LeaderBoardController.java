package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/***
 * This class controls the leaderboard functions
 * @author Hei
 * @version 1.0LeaderBoardController for read userInfo fom file & display it
 */
public class LeaderBoardController implements Initializable {

    public static ArrayList<ArrayList<String>> userData = new ArrayList<>(1);
    public static ObservableList<User> list = null;
    // TextArea leaderBoardText are for showing data for leaderboard
    @FXML
    TextArea leaderBoardText = new TextArea();

    /**
     * Method directs user back to menu page
     * @param event Action Event to set resources for FXML
     */
    public void switchBackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method reads user data from the csv file to display.
     * Text is returned to user.
     * @return table text.
     */
    public String test() throws IOException { //!! it has to be test i finding why
        list = getUserList();
        String lText = new String();
        for (int i=0; i< list.size()-1;i++){
            lText=lText+"\t"+list.get(i).getplayerID();
            lText=lText+"\t\t"+list.get(i).getUserName();
            if(list.get(i).getUserName().length()<5){
                lText=lText+"\t";
            }
            lText=lText+"\t\t"+list.get(i).getFirstName();
            if(list.get(i).getFirstName().length()<5){
                lText=lText+"\t";
            }
            lText=lText+"\t"+list.get(i).getLastName();
            if(list.get(i).getLastName().length()<5){
                lText=lText+"\t";
            }
            int w=list.get(i).getsWinLossDraw(0);
            int l=list.get(i).getsWinLossDraw(1);
            int d=list.get(i).getsWinLossDraw(2);
            lText=lText+"\t\t\t"+w;
            lText=lText+"\t\t\t"+l;
            lText=lText+"\t\t\t"+d;
            if(w!=0||l+d!=0){ //cuz cant divide 0
                double ans = w/(w+l+d);
                double rem = (w%(w+l+d))*0.1;
                lText=lText+"\t\t\t"+(String.format("%.2f", ans+rem));
            }
            else{
                lText=lText+"\t\t\t"+0;
            }
            lText=lText+"\n";
        }
        leaderBoardText.setText(lText);
        return lText;
    }

    /**
     * Method loads the user information from CSV file.
     * Loads the relevant game data to the correct user data.
     */
    public static void loadInfo (ActionEvent e) throws IOException {
        BufferedReader reader = null;
        String file = "src\\LeaderBoard.csv";
        String line = "";
        int o = 0;
        try {
            if(userData.size()!=0){
                userData.clear();
            }
            reader = new BufferedReader(new FileReader(file));
            while((line=reader.readLine())!=null) {
                String[] row = line.split(",");
                userData.add(new ArrayList());
                for (String index : row) {
                    //System.out.printf("%-10s",index);
                    userData.get(o).add(index);
                }
                o++;
            }
            System.out.println("loaded userInfo");
            searchUser(LoginControllerT2.userInfo);
        }catch(Exception error) {
            System.out.println(error);
        }
    }

    /**
     * Method searches the array list via username to return the user.
     * @param uid - String type type given as userID.
     * @return scanner as row of user data.
     */
    public static ArrayList searchUser(String uid){
        ArrayList scanner = new ArrayList();
        for (int i=0; i<userData.size()-1;i++){
            scanner = userData.get(i);
            if(scanner.get(1).equals(LoginControllerT2.userInfo)){
                return scanner;
            }
        }
        return scanner;
    }

    /**
     * Method to search the array list for relevant user data.
     * Returns user based on the userID.
     * @param uid - String as userID.
     * @return scanner as row of user data.
     */
    public static ArrayList getUser(String uid){
        ArrayList scanner = new ArrayList();
        for (int i=0; i<userData.size()-1;i++){
            scanner = userData.get(i);
            if(scanner.get(0).equals(uid)){
                return scanner;
            }
        }
        return scanner;
    }


    /**
     * Method to load the user and set appropriate data to the object user.
     * @param uid - String as userID.
     * @return obj user in which it can fill with the data
     */
    public static User loadUser (String uid){
        ArrayList tem = getUser(uid);
        User newUser = new User();
        newUser.setPlayerID((String) tem.get(0));
        newUser.setUserName((String) tem.get(1));
        newUser.setPassword((String) tem.get(2));
        newUser.setfirstName((String) tem.get(3));
        newUser.setlastName((String) tem.get(4));
        newUser.setWinLossDraw((String) tem.get(5),(String) tem.get(6),(String) tem.get(7));
        return newUser;
    }

    /**
     * Method fills all users in the array list from the userData
     * @return list that stores all of said users
     */
    public static ObservableList getUserList() throws IOException {
        ObservableList<User> list = FXCollections.observableArrayList();

        ActionEvent e = new ActionEvent();
        loadInfo(e);
        for (int i=0; i<userData.size()-1;i++){
            list.add(loadUser(String.valueOf(i)));
//            System.out.println(loadUser(String.valueOf(i)));
        }
        return list;
    }

    /**
     * Method initialises action including relevant JavaFXML resources.
     * Method executes whilst leaderboard page is in loading process.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LeaderBoardController {

    //public static ArrayList<String> userData = new ArrayList<>();

    //public static ArrayList[][] userData = new ArrayList[10][];
    public static ArrayList<ArrayList<String>> userData = new ArrayList<>(1);
    //stuff for tableView

    TableView<User> leaderboard = new TableView<User>();
    // Create column UserName (Data type of String).
    TableColumn<User, String> playerIdCol = new TableColumn<User, String>("Player ID");
    TableColumn<User, String> userNameCol = new TableColumn<User, String>("User Name");
    TableColumn<User, String> totalWinsCol = new TableColumn<User, String>("Total Wins");
    TableColumn<User, String> WinPercentageCol = new TableColumn<User, String>("Win Percentage");
    TableColumn<User, String> rankCol = new TableColumn<User, String>("Rank");
    TableColumn<User, String> favourateCol = new TableColumn<User, String>("Favourite");






    public void switchBackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void test(ActionEvent event) throws IOException {
        //System.out.println(LoginControllerT2.userInfo);
        // Defines how to fill data for each cell.
        // Get value from property of UserAccount. .
        playerIdCol.setCellValueFactory(new PropertyValueFactory<>("Player ID"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("User Name"));
        totalWinsCol.setCellValueFactory(new PropertyValueFactory<>("Total Wins"));
        WinPercentageCol.setCellValueFactory(new PropertyValueFactory<>("Win Percentage"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        favourateCol.setCellValueFactory(new PropertyValueFactory<>("Favourite"));
        // Display row data
        ObservableList<User> list = getUserList();
        leaderboard.setItems(list);

        leaderboard.getColumns().addAll(playerIdCol,userNameCol,totalWinsCol,WinPercentageCol,rankCol,favourateCol);
    }

    public static void loadInfo (ActionEvent e) throws IOException {
        BufferedReader reader = null;
        String file = "src\\LeaderBoard.csv";
        String line = "";
        int o = 0;
        try {
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

    public static ArrayList searchUser(String uid){
        ArrayList scanner = new ArrayList();
        for (int i=0; i<userData.size()-1;i++){
            scanner = userData.get(i);
            if(scanner.get(1).equals(LoginControllerT2.userInfo)){
                System.out.println("found "+scanner.get(1));
                System.out.println(scanner);
                return scanner;
            }
        }
        return scanner;
    }

    public static ArrayList getUser(String uid){
        ArrayList scanner = new ArrayList();
        for (int i=0; i<userData.size()-1;i++){
            scanner = userData.get(i);
            if(scanner.get(0).equals(uid)){
                System.out.println("@@@@@");
                System.out.println("found uID"+scanner.get(0));
                System.out.println(scanner);
                return scanner;
            }
        }
        return scanner;
    }


    public static User loadUser (String uid){
        ArrayList tem = getUser(uid);
        User newUser = new User();
        newUser.setUserName((String) tem.get(1));
        newUser.setPassword((String) tem.get(2));
        newUser.setfirstName((String) tem.get(4));
        newUser.setlastName((String) tem.get(5));
        return newUser;
    }

    public static ObservableList getUserList() throws IOException {
        ObservableList<User> list = FXCollections.observableArrayList();
        ActionEvent e = new ActionEvent();
        loadInfo(e);
        for (int i=0; i<userData.size()-1;i++){
            list.add(loadUser(String.valueOf(i)));
            System.out.println(loadUser(String.valueOf(i)));
            System.out.println("moew");
        }
        return list;
    }


//    public static User getUserList(){
//        ArrayList tem = searchUser(LoginControllerT2.userInfo);
//        User newUser = new User();
//        newUser.setUserName((String) tem.get(1));
//        newUser.setPassword((String) tem.get(2));
//        newUser.setfirstName((String) tem.get(4));
//        newUser.setlastName((String) tem.get(5));
//        return newUser;
//    }
}



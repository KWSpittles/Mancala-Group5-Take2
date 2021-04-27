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

public class LeaderBoardController implements Initializable {
    //public static ArrayList<String> userData = new ArrayList<>();
    public static ArrayList<ArrayList<String>> userData = new ArrayList<>(1);
    public static ObservableList<User> list = null;
    @FXML
    TextArea leaderBoardText = new TextArea();


    //stuff for tableView

    TableView<User> leaderboard = new TableView<User>();
    // Create column UserName (Data type of String).
    TableColumn<User, String> playerIdCol = new TableColumn<User, String>("Player ID");
    TableColumn<User, String> userNameCol = new TableColumn<User, String>("User Name");
    TableColumn<User, String> totalWinsCol = new TableColumn<User, String>("Total Wins");
    TableColumn<User, String> WinPercentageCol = new TableColumn<User, String>("Win Percentage");
    TableColumn<User, String> rankCol = new TableColumn<User, String>("Rank");
    TableColumn<User, String> favouriteCol = new TableColumn<User, String>("Favourite");

    public void switchBackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }


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
            lText=lText+"\t\t"+list.get(i).getLastName();
            int w=list.get(i).getsWinLossDraw(0);
            int l=list.get(i).getsWinLossDraw(1);
            int d=list.get(i).getsWinLossDraw(2);
            lText=lText+"\t\t\t"+w;
            lText=lText+"\t\t\t"+l;
            lText=lText+"\t\t\t"+d;
            if(w!=0||l+d!=0){ //cant divide 0
                double ans = w/(w+l+d);
                double rem = (w%(w+l+d))*0.1;
                lText=lText+"\t\t\t"+(ans+rem);
            }
            else{
                lText=lText+"\t\t\t"+0;
            }
            lText=lText+"\n";
        }
        leaderBoardText.setText(lText);
        return lText;
    }

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

    public static ArrayList searchUser(String uid){
        ArrayList scanner = new ArrayList();
        for (int i=0; i<userData.size()-1;i++){
            scanner = userData.get(i);
            if(scanner.get(1).equals(LoginControllerT2.userInfo)){
//                System.out.println("found "+scanner.get(1));
//                System.out.println(scanner);
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
                return scanner;
            }
        }
        return scanner;
    }


    //@@@
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



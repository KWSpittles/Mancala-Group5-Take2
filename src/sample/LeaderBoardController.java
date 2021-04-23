package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LeaderBoardController {

    //public static ArrayList<String> userData = new ArrayList<>();

    //public static ArrayList[][] userData = new ArrayList[10][];
    public static ArrayList<ArrayList<String>> userData = new ArrayList<>(1);

    public void switchBackToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void test(ActionEvent event) throws IOException {
        System.out.println(LoginControllerT2.userInfo);

    }

    public void loadInfo (ActionEvent e) throws IOException {
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

    public static Player setPlayer(String uid){
        ArrayList tem = searchUser(LoginControllerT2.userInfo);
        Player t = new Player();
        t.setUserName((String) tem.get(1));
        t.setPassword((String) tem.get(2));
        t.setfirstName((String) tem.get(4));
        t.setlastName((String) tem.get(5));
        return t;
    }
}



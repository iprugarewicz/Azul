package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Network.Serwer;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private String host;
    private int port;
    private boolean online;

    private int playerCount;
    private boolean isHosting;

    @FXML
    private Pane BGPane;

    @FXML
    private ImageView Logo;

    @FXML
    private Pane MainPane;

    @FXML
    private ImageView menuImageView;

    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Button playButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button settingsButton;
    @FXML
    private ImageView rulesViewImage;

    @FXML
    void btnPlayOnClick(ActionEvent event) {
        if(online){
            try {
                Stage mainWindow = (Stage) playButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("game_network-view.fxml"));

                Scene scene = new Scene(loader.load());
                mainWindow.setTitle("Azul");
                mainWindow.setScene(scene);
                mainWindow.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{

        }

        event.consume();
    }

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Stage mainWindow = (Stage) quitButton.getScene().getWindow();
        mainWindow.close();
        event.consume();


    }

    @FXML
    void btnRulesOnClick(ActionEvent event) {
        event.consume();

    }

    @FXML
    void btnSettingsOnClick(ActionEvent event) {
        Stage settingsWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            settingsWindow.setTitle("Settings");
            settingsWindow.setScene(scene);
            settingsWindow.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        event.consume();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            backgroundImageView.setImage(new Image(new FileInputStream("src/main/resources/images/menu_bg.png")));
            menuImageView.setImage(new Image(new FileInputStream("src/main/resources/images/menu_menu.png")));
            Logo.setImage(new Image(new FileInputStream("src/main/resources/images/logo.png")));
            loadSettings();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadSettings() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("src/main/resources/Settings/settings.txt"));
            String[] line;
            try {
                line = br.readLine().split("= ");
                host = line[1];
            } catch (Exception e) {
                System.out.println("host unreadable, setting default");
                host = "localhost";
            }
            try {
                line = br.readLine().split("= ");
                port = Integer.parseInt(line[1]);
            } catch (Exception e) {
                System.out.println("port unreadable, setting default");
                port = 12300;
            }
            try{
                line = br.readLine().split("= ");
                online = Boolean.parseBoolean(line[1]);
            } catch (Exception e) {
                System.out.println("online state unreadable, setting default");
                online = false;
            }
            try {
                line = br.readLine().split("= ");
                isHosting = Boolean.parseBoolean(line[1]);
            } catch (Exception e) {
                System.out.println("ishosting unreadable, setting default");
                isHosting = false;
            }
            try {
                line = br.readLine().split("= ");
                if (Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 4) {
                    throw new Exception();
                }
                playerCount = Integer.parseInt(line[1]);
            } catch (Exception e) {
                System.out.println("playerCount unreadable, setting default");
                playerCount = 2;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, setting defaults");

        }

        System.out.println("settings read");
    }
}

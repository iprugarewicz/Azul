package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.Network.Move;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {
    private String host;
    private int port;
    private boolean online;

    private int playerCount;
    private boolean isHosting;

    @FXML
    private Button backButton;

    @FXML
    private CheckBox hostBox;

    @FXML
    private TextField hostTextField;

    @FXML
    private CheckBox onlineBox;

    @FXML
    private ChoiceBox<String > playerCountChoice;

    @FXML
    private TextField portTextField;

    @FXML
    private Button saveButton;

    @FXML
    void hostMarked(ActionEvent event) {
        settingsSavedText.setVisible(false);
        isHosting = !isHosting;


    }

    @FXML
    void hostTyped(ActionEvent event) {
        settingsSavedText.setVisible(false);
        host = hostTextField.getText();
        System.out.println(host);

    }

    @FXML
    void onlineMarked(ActionEvent event) {
        settingsSavedText.setVisible(false);
        if(online){
            isHosting = false;
            hostBox.setSelected(false);
        }
        online=!online;

        setOnlineVisibility();

    }

    @FXML
    void portTyped(ActionEvent event) {
        settingsSavedText.setVisible(false);
        port = Integer.parseInt(portTextField.getText());

    }

    @FXML
    void quitPressed(ActionEvent event) {
        Stage mainWindow = (Stage) backButton.getScene().getWindow();
        mainWindow.close();

    }
    @FXML
    private Text settingsSavedText;


    @FXML
    void savePressed(ActionEvent event) {
        saveSettings();
        settingsSavedText.setVisible(true);
        event.consume();
    }

    void setPlayerCountChosen(ActionEvent event){
        System.out.println("xD");
        playerCount = parse(playerCountChoice.getValue());
        event.consume();

    }
    private int parse(String s){return s.charAt(0)-48;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingsSavedText.setVisible(false);
        loadSettings();
        choicesInit();


    }

    private void loadSettings()  {
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
    private void choicesInit(){
        playerCountChoice.getItems().add("2 players");
        playerCountChoice.getItems().add("3 players");
        playerCountChoice.getItems().add("4 players");
        playerCountChoice.setValue(playerCount+" players");
        playerCountChoice.setOnAction(this::setPlayerCountChosen);
        hostBox.setSelected(isHosting);
        onlineBox.setSelected(online);
        portTextField.setText(String.valueOf(port));
        hostTextField.setText(host);
        setOnlineVisibility();
    }
    private void saveSettings(){
        try {
            File file = new File("src/main/resources/Settings/settings.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("host= "+host+"\nport= "+port+"\nonline= "+online+"\nisHosting= "+ isHosting +"\nplayerCount= "+playerCount);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void setOnlineVisibility(){
        if(online){
            playerCountChoice.setVisible(false);
            hostBox.setVisible(true);
        }else{
            playerCountChoice.setVisible(true);
            hostBox.setVisible(false);
        }
    }

}

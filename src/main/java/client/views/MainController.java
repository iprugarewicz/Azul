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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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

    }

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Stage mainWindow = (Stage) quitButton.getScene().getWindow();
        mainWindow.close();


    }

    @FXML
    void btnRulesOnClick(ActionEvent event) {

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





    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            backgroundImageView.setImage(new Image(new FileInputStream("src/main/resources/images/menu_bg.png")));
            menuImageView.setImage(new Image(new FileInputStream("src/main/resources/images/menu_menu.png")));
            Logo.setImage(new Image(new FileInputStream("src/main/resources/images/logo.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

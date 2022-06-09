package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Pane BGPane;

    @FXML
    private Pane MainPane;

    @FXML
    private Button playButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button settingsButton;

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

}

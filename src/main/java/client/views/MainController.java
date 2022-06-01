package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

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

    }



}

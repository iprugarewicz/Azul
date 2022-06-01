package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Test {

    @FXML
    private TextField textTitle;

    @FXML
    void btnOKClicked(ActionEvent event) {
        Stage mainWindow = (Stage) textTitle.getScene().getWindow();
        String title = textTitle.getText();
        mainWindow.setTitle(title);

    }


}

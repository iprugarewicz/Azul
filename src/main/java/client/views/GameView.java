package client.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView {

    @FXML
    private Rectangle pLine00;

    @FXML
    private Rectangle pLine10;

    @FXML
    private Rectangle pLine11;

    @FXML
    private Rectangle pLine20;

    @FXML
    private Rectangle pLine21;

    @FXML
    private Rectangle pLine22;

    @FXML
    private Rectangle pLine30;

    @FXML
    private Rectangle pLine31;

    @FXML
    private Rectangle pLine32;

    @FXML
    private Rectangle pLine33;

    @FXML
    private Rectangle pLine41;

    @FXML
    private Rectangle pLine42;

    @FXML
    private Rectangle pLine43;

    @FXML
    private Rectangle pLine44;

    @FXML
    private GridPane patternLines;

    @FXML
    private Rectangle pline40;

    @FXML
    private Rectangle wall00;

    @FXML
    private Rectangle wall01;

    @FXML
    private Rectangle wall02;

    @FXML
    private Rectangle wall03;

    @FXML
    private Rectangle wall04;

    @FXML
    private Rectangle wall10;

    @FXML
    private Rectangle wall11;

    @FXML
    private Rectangle wall12;

    @FXML
    private Rectangle wall13;

    @FXML
    private Rectangle wall14;

    @FXML
    private Rectangle wall20;

    @FXML
    private Rectangle wall21;

    @FXML
    private Rectangle wall22;

    @FXML
    private Rectangle wall23;

    @FXML
    private Rectangle wall24;

    @FXML
    private Rectangle wall30;

    @FXML
    private Rectangle wall31;

    @FXML
    private Rectangle wall32;

    @FXML
    private Rectangle wall33;

    @FXML
    private Rectangle wall34;

    @FXML
    private Rectangle wall40;

    @FXML
    private Rectangle wall41;

    @FXML
    private Rectangle wall42;

    @FXML
    private Rectangle wall43;

    @FXML
    private Rectangle wall44;

    @FXML
    private GridPane wallBoard;

    @FXML
    void testClick(ActionEvent event) throws FileNotFoundException {
        pLine00.setFill(Color.YELLOW);
        Image img = new Image(new FileInputStream("src/main/resources/images/test.png"));
        pLine10.setFill(new ImagePattern(img));

    }

}

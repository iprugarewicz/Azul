package client.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameView extends Initializable {

    @FXML
    private Rectangle floor0;

    @FXML
    private Rectangle floor1;

    @FXML
    private Rectangle floor2;

    @FXML
    private Rectangle floor3;

    @FXML
    private Rectangle floor4;

    @FXML
    private Rectangle floor5;

    @FXML
    private Rectangle floor6;

    private Rectangle floor[] = {floor0,floor1,floor2,floor3,floor4,floor5,floor6};

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
    private Rectangle pline40;


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
    private Rectangle w1Tile0;

    @FXML
    private Rectangle w1Tile1;

    @FXML
    private Rectangle w1Tile2;

    @FXML
    private Rectangle w1Tile3;

    private Rectangle[] w1= {w1Tile0,w1Tile1,w1Tile2,w1Tile3};
    @FXML
    private Rectangle w2Tile0;

    @FXML
    private Rectangle w2Tile1;

    @FXML
    private Rectangle w2Tile2;

    @FXML
    private Rectangle w2Tile3;

    @FXML
    private Rectangle w3Tile0;

    @FXML
    private Rectangle w3Tile1;

    @FXML
    private Rectangle w3Tile2;

    @FXML
    private Rectangle w3Tile3;

    @FXML
    private Rectangle w4Tile0;

    @FXML
    private Rectangle w4Tile1;

    @FXML
    private Rectangle w4Tile2;

    @FXML
    private Rectangle w4Tile3;

    @FXML
    private Rectangle w5Tile0;

    @FXML
    private Rectangle w5Tile1;

    @FXML
    private Rectangle w5Tile2;

    @FXML
    private Rectangle w5Tile3;

    @FXML
    private Rectangle w6Tile0;

    @FXML
    private Rectangle w6Tile1;

    @FXML
    private Rectangle w6Tile2;

    @FXML
    private Rectangle w6Tile3;

    @FXML
    private Rectangle w7Tile0;

    @FXML
    private Rectangle w7Tile1;

    @FXML
    private Rectangle w7Tile2;

    @FXML
    private Rectangle w7Tile3;

    @FXML
    private Rectangle w8Tile0;

    @FXML
    private Rectangle w8Tile1;

    @FXML
    private Rectangle w8Tile2;

    @FXML
    private Rectangle w8Tile3;

    @FXML
    private Rectangle w9Tile0;

    @FXML
    private Rectangle w9Tile1;

    @FXML
    private Rectangle w9Tile2;

    @FXML
    private Rectangle w9Tile3;

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
    private Circle workshop1;

    @FXML
    private Circle workshop2;

    @FXML
    private Circle workshop3;

    @FXML
    private Circle workshop4;

    @FXML
    private Circle workshop5;

    @FXML
    private Circle workshop6;

    @FXML
    private Circle workshop7;

    @FXML
    private Circle workshop8;

    @FXML
    private Circle workshop9;

    @FXML
    void dragIn(DragEvent event) {

    }

    @FXML
    void testClick(MouseEvent event) throws FileNotFoundException {
        pLine00.setFill(Color.YELLOW);
        Image img = new Image(new FileInputStream("src/main/resources/images/test.png"));
        pLine10.setFill(new ImagePattern(img));
        workshop1.setFill(new ImagePattern(img));

    }


    public Rectangle[] getW1() {
        return w1;
    }
    private double startX;
    private double startY;
    double X;
    double Y;
    private void makeDraggable(Node node) {

        // a = b - c
        // a + c = b
        // c = b - a

        node.setOnMousePressed(e -> {
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
            X = node.getTranslateX();
            Y = node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - startX);
            node.setTranslateY(e.getSceneY() - startY);
        });
        node.setOnMouseReleased(e ->{
            node.setTranslateX(X);
            node.setTranslateY(Y);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

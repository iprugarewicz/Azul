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

public class GameController implements Initializable {

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

    private  Rectangle[] floor = {floor0,floor1,floor2,floor3,floor4,floor5,floor6};

    @FXML
    private Rectangle pLine00;

    @FXML
    private Rectangle pLine10;

    @FXML
    private Rectangle pLine11;

    private  Rectangle[] pLine1={pLine10,pLine11};
    @FXML
    private Rectangle pLine20;

    @FXML
    private Rectangle pLine21;

    @FXML
    private Rectangle pLine22;

    private  Rectangle[] pLine2={pLine20,pLine21,pLine22};
    @FXML
    private Rectangle pLine30;

    @FXML
    private Rectangle pLine31;

    @FXML
    private Rectangle pLine32;

    @FXML
    private Rectangle pLine33;

    private  Rectangle[] pLine3 = {pLine30,pLine31,pLine32,pLine33};
    @FXML
    private Rectangle pLine40;


    @FXML
    private Rectangle pLine41;

    @FXML
    private Rectangle pLine42;

    @FXML
    private Rectangle pLine43;

    @FXML
    private Rectangle pLine44;

    private  Rectangle[] pLine4 = {pLine40,pLine41,pLine42,pLine43,pLine44};
    @FXML
    private GridPane patternLines;

    @FXML
    private Rectangle w0Tile0;

    @FXML
    private Rectangle w0Tile1;

    @FXML
    private Rectangle w0Tile2;

    @FXML
    private Rectangle w0Tile3;

    private  Rectangle[] w0;
    @FXML
    private Rectangle w1Tile0;

    @FXML
    private Rectangle w1Tile1;

    @FXML
    private Rectangle w1Tile2;

    @FXML
    private Rectangle w1Tile3;

    private  Rectangle[] w1;
    @FXML
    private Rectangle w2Tile0;

    @FXML
    private Rectangle w2Tile1;

    @FXML
    private Rectangle w2Tile2;

    @FXML
    private Rectangle w2Tile3;

    private  Rectangle[] w2 ;

    @FXML
    private Rectangle w3Tile0;

    @FXML
    private Rectangle w3Tile1;

    @FXML
    private Rectangle w3Tile2;

    @FXML
    private Rectangle w3Tile3;

    private  Rectangle[] w3;

    @FXML
    private Rectangle w4Tile0;

    @FXML
    private Rectangle w4Tile1;

    @FXML
    private Rectangle w4Tile2;

    @FXML
    private Rectangle w4Tile3;

    private  Rectangle[] w4;

    @FXML
    private Rectangle w5Tile0;

    @FXML
    private Rectangle w5Tile1;

    @FXML
    private Rectangle w5Tile2;

    @FXML
    private Rectangle w5Tile3;

    private  Rectangle[] w5;

    @FXML
    private Rectangle w6Tile0;

    @FXML
    private Rectangle w6Tile1;

    @FXML
    private Rectangle w6Tile2;

    @FXML
    private Rectangle w6Tile3;

    private  Rectangle[] w6;

    @FXML
    private Rectangle w7Tile0;

    @FXML
    private Rectangle w7Tile1;

    @FXML
    private Rectangle w7Tile2;

    @FXML
    private Rectangle w7Tile3;

    private  Rectangle[] w7;

    @FXML
    private Rectangle w8Tile0;

    @FXML
    private Rectangle w8Tile1;

    @FXML
    private Rectangle w8Tile2;

    @FXML
    private Rectangle w8Tile3;

    private  Rectangle[] w8;



    private  Rectangle[][] workshops;

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
    private Rectangle yellowTileCounter;

    @FXML
    private Rectangle greenTileCounter;

    @FXML
    private Rectangle pinkTileCount;

    @FXML
    private Rectangle purpleTileCount;

    @FXML
    private Rectangle blueTileCounter;

    @FXML
    private Rectangle firstPlayerTile;

    private  Rectangle[] counters;



    @FXML
    void dragIn(DragEvent event) {
        System.out.println("ez");

    }

    @FXML
    void testClick(MouseEvent event) throws FileNotFoundException {
        pLine00.setFill(Color.YELLOW);
        Image img = new Image(new FileInputStream("src/main/resources/images/test.png"));
        pLine10.setFill(new ImagePattern(img));
        workshop1.setFill(new ImagePattern(img));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        w0 = new Rectangle[]{w0Tile0, w0Tile1, w0Tile2, w0Tile3};
        w1 = new Rectangle[]{w1Tile0, w1Tile1, w1Tile2, w1Tile3};
        w2 = new Rectangle[]{w2Tile0, w2Tile1, w2Tile2, w2Tile3};
        w3 = new Rectangle[]{w3Tile0, w3Tile1, w3Tile2, w3Tile3};
        w4 = new Rectangle[]{w4Tile0, w4Tile1, w4Tile2, w4Tile3};
        w5 = new Rectangle[]{w5Tile0, w5Tile1, w5Tile2, w5Tile3};
        w6 = new Rectangle[]{w6Tile0, w6Tile1, w6Tile2, w6Tile3};
        w7 = new Rectangle[]{w7Tile0, w7Tile1, w7Tile2, w7Tile3};
        w8 = new Rectangle[]{w8Tile0, w8Tile1, w8Tile2, w8Tile3};
        workshops = new Rectangle[][]{w0, w1, w2, w3, w4, w5, w6, w7, w8};

        counters = new Rectangle[]{blueTileCounter, greenTileCounter, pinkTileCount, purpleTileCount, yellowTileCounter};


        try {
            firstPlayerTile.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/images/1stplayertile.png"))));

            Image[] images ={new Image(new FileInputStream("src/main/resources/images/blue.png")),
                    new Image(new FileInputStream("src/main/resources/images/green.png")),
                    new Image(new FileInputStream("src/main/resources/images/pink.png")),
                    new Image(new FileInputStream("src/main/resources/images/purple.png")),
                    new Image(new FileInputStream("src/main/resources/images/yellow.png"))
            };
            DraggableMaker draggableMaker = new DraggableMaker();
            int i = 0;
            for (Rectangle counter : counters) {
                counter.setFill(new ImagePattern(images[i]));
                draggableMaker.makeDraggable(counter);
                i++;
            }
             i = 0;

            for (Rectangle[] w:
            workshops){
                for (Rectangle Tile :
                        w) {
                    Tile.setFill(new ImagePattern(images[i%5]));
                    draggableMaker.makeDraggable(Tile);
                    i++;
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public class DraggableMaker {

        private double startX;
        private double startY;
        double X;
        double Y;

        public void makeDraggable(Node node) {
            node.setOnMousePressed(mouseEvent -> {
                startX = mouseEvent.getSceneX() - node.getTranslateX();
                startY = mouseEvent.getSceneY() - node.getTranslateY();
                X = node.getTranslateX();
                Y = node.getTranslateY();
            });

            node.setOnMouseDragged(mouseEvent -> {
                node.setTranslateX(mouseEvent.getSceneX() - startX);
                node.setTranslateY(mouseEvent.getSceneY() - startY);
            });
            node.setOnMouseReleased(e ->{
                node.setTranslateX(X);
                node.setTranslateY(Y);
            });
        }
    }
}

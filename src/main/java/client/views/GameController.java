package client.views;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
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

    private  Rectangle[] floor;

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
    private Rectangle pLine40;


    @FXML
    private Rectangle pLine41;

    @FXML
    private Rectangle pLine42;

    @FXML
    private Rectangle pLine43;

    @FXML
    private Rectangle pLine44;

    private  Rectangle[][] patternLines;

    @FXML
    private Rectangle w0Tile0;

    @FXML
    private Rectangle w0Tile1;

    @FXML
    private Rectangle w0Tile2;

    @FXML
    private Rectangle w0Tile3;

    @FXML
    private Rectangle w1Tile0;

    @FXML
    private Rectangle w1Tile1;

    @FXML
    private Rectangle w1Tile2;

    @FXML
    private Rectangle w1Tile3;

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


    private  Rectangle[][] workshopTiles;

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
    private Circle workshop0;

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



    private Circle[] workshops;
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

    @FXML
    private Button homeButton;

    private  Rectangle[] counters;
    private ImagePattern[] images;

    boolean draggableLock = false;
    private DraggableMaker draggableMaker = new DraggableMaker();


    @FXML
    void homeBtnClick(ActionEvent event){
        if(draggableLock){

            System.out.println("dragging locked from now");
            draggableMaker.lockDragging();
            draggableLock= false;
        }else{

            System.out.println("dragging unlocked from now");
            draggableMaker.unlockDragging();
            draggableLock = true;
        }

    }
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
    private void variablesInit(){
        Rectangle[] w0 = new Rectangle[]{w0Tile0, w0Tile1, w0Tile2, w0Tile3};
        Rectangle[] w1 = new Rectangle[]{w1Tile0, w1Tile1, w1Tile2, w1Tile3};
        Rectangle[] w2 = new Rectangle[]{w2Tile0, w2Tile1, w2Tile2, w2Tile3};
        Rectangle[] w3 = new Rectangle[]{w3Tile0, w3Tile1, w3Tile2, w3Tile3};
        Rectangle[] w4 = new Rectangle[]{w4Tile0, w4Tile1, w4Tile2, w4Tile3};
        Rectangle[] w5 = new Rectangle[]{w5Tile0, w5Tile1, w5Tile2, w5Tile3};
        Rectangle[] w6 = new Rectangle[]{w6Tile0, w6Tile1, w6Tile2, w6Tile3};
        Rectangle[] w7 = new Rectangle[]{w7Tile0, w7Tile1, w7Tile2, w7Tile3};
        Rectangle[] w8 = new Rectangle[]{w8Tile0, w8Tile1, w8Tile2, w8Tile3};
        workshopTiles = new Rectangle[][]{w0, w1, w2, w3, w4, w5, w6, w7, w8};
        workshops = new Circle[]{workshop0,workshop2,workshop3,workshop4,workshop5,workshop6,workshop7,workshop8};
        Rectangle[] pLine0 = new Rectangle[]{pLine00};
        Rectangle[] pLine1 = new Rectangle[]{pLine10, pLine11};
        Rectangle[] pLine2 = new Rectangle[]{pLine20, pLine21, pLine22};
        Rectangle[] pLine3 = new Rectangle[]{pLine30, pLine31, pLine32, pLine33};
        Rectangle[] pLine4 = new Rectangle[]{pLine40, pLine41, pLine42, pLine43, pLine44};
        patternLines = new Rectangle[][]{pLine0, pLine1, pLine2, pLine3, pLine4};
        counters = new Rectangle[]{blueTileCounter, greenTileCounter, pinkTileCount, purpleTileCount, yellowTileCounter};


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        variablesInit();


        try {


             images = new ImagePattern[]{new ImagePattern(new Image(new FileInputStream("src/main/resources/images/blue.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/green.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/pink.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/purple.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/yellow.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/1stplayertile.png"))),
                     new ImagePattern((new Image(new FileInputStream("src/main/resources/images/empty.png"))))
             };

            firstPlayerTile.setFill(images[5]);
            int i = 0;
            for (Rectangle counter : counters) {
                counter.setFill(images[i]);
                draggableMaker.makeDragSource(counter);
                i++;
            }
             i = 0;

            for (Rectangle[] w:
                    workshopTiles){
                for (Rectangle Tile :
                        w) {
                    Tile.setFill(images[i%5]);
                    draggableMaker.makeDragSource(Tile);
                    i++;
                }

            }
            for (Rectangle[] p:
                    patternLines){
                for (Rectangle Tile :
                        p) {
                    Tile.setFill(images[6]);
                    draggableMaker.makeDragTarget(Tile);

                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




    }
    public static class DraggableMaker {
        /*
        Działanie jest proste, są dwa rodzaje elementów source i target,
        metody przyjmują element i sprawiają ze staje się source i target (można oba chyba, nie testowałem)
        zachowania w konkretnych sytuacjach opisane są w poszczególnych lambdach, da się ogarnąć co i jak
        nie jest najgorzej xD

         */
        TransferMode locker = null;
        Rectangle dragged; // przechowuje ostatni wzięty element
        boolean draggableLock = true;
        public void makeDragTarget(Rectangle target){

            //co się dzieje z targetem jak jakiś source przejeżdża nad nim
            target.setOnDragOver(event -> {

                /* data is dragged over the target */
                //System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            });

            // co się dzieje z targetem, jak cos na niego wjeżdża, tylko w momencie wjechania
            target.setOnDragEntered(event -> {

                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {

                }

                event.consume();

            });


            // co się dzieje jak upuści się dowolny source na ten target
            target.setOnDragDropped(event -> {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on drag board, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    target.setFill(Color.YELLOW);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */

                event.setDropCompleted(success);
                target.setFill(dragged.getFill());

                event.consume();
            });

        }

        public void makeDragSource(Node source) {
            //co się dzieje jak się upuści source
            source.setOnDragDone(event -> {
                try {
                    if (draggableLock) {

                    }

                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == locker) {
                        //source.setText("");

                    }

                    event.consume();
                }catch (NullPointerException e){
                    System.out.println("dragging locked");
                }
            });


            //co się dzieje jak się podniesie source
            source.setOnDragDetected((EventHandler<Event>) event -> {
                System.out.println("onDragDetected");
                try {
                    Dragboard db = source.startDragAndDrop(locker);
                    dragged = (Rectangle) source;
                    ClipboardContent content = new ClipboardContent();
                    content.putString("Hello!");
                    db.setContent(content);
                    event.consume();
                }catch(NullPointerException e){
                    System.out.println("dragging locked");
                }
            });



        }
        public void unlockDragging(){
            locker = TransferMode.MOVE;
        }
        public void lockDragging(){
            locker = null;
        }
    }

}

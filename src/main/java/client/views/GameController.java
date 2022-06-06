package client.views;

import client.logic.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import server.Logic.CenterOfWorkshop;
import server.Logic.Game;
import server.Logic.Tile;
import server.Logic.Workshop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public static Workshop[] workshopsFromGame;
    public static CenterOfWorkshop centerOfWorkshop = new CenterOfWorkshop();
    public static ArrayList<Player> playerList=new ArrayList<>();
    public ArrayList<Integer> possibleActions = new ArrayList<>();// tutaj trzeba ściągnąć possibleMoves z gry



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

    private Rectangle[] wall0;

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

    private Rectangle[] wall1;

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

    private Rectangle[] wall2;

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

    private Rectangle[] wall3;

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

    private Rectangle[] wall4;

    private Rectangle[][] wall;

    @FXML
    private GridPane wallBoard;

    @FXML
    private Circle workshopPlate0;

    @FXML
    private Circle workshopPlate1;

    @FXML
    private Circle workshopPlate2;

    @FXML
    private Circle workshopPlate3;

    @FXML
    private Circle workshopPlate4;

    @FXML
    private Circle workshopPlate5;

    @FXML
    private Circle workshopPlate6;

    @FXML
    private Circle workshopPlate7;

    @FXML
    private Circle workshopPlate8;



    private Circle[] workshopPlates;

    private Node[] workshop0;
    private Node[] workshop1;
    private Node[] workshop2;
    private Node[] workshop3;
    private Node[] workshop4;
    private Node[] workshop5;
    private Node[] workshop6;
    private Node[] workshop7;
    private Node[] workshop8;
    private Node[][] workshops;
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

    @FXML
    private Text textCounter0;

    @FXML
    private Text textCounter1;

    @FXML
    private Text textCounter2;

    @FXML
    private Text textCounter3;

    @FXML
    private Text textCounter4;

    @FXML
    private ImageView menuBackground;

    @FXML
    private ImageView gameBackground;

    @FXML
    private Polygon triangle1;

    @FXML
    private Polygon triangle2;

    @FXML
    private Polygon triangle3;

    @FXML
    private Polygon triangle4;

    @FXML
    private Polygon triangle5;

    private Polygon[] triangles;

    private Text[] counterTexts;

    private  Rectangle[] countersTiles;
    private int[] counters = {0,0,0,0,0};
    private ImagePattern[] images;

    boolean draggableLock = false;
    
    private int playerCount;


    @FXML
    void homeBtnClick(ActionEvent event){
        if(draggableLock){

            System.out.println("dragging locked from now");
            lockDragging();
            draggableLock= false;
        }else{

            System.out.println("dragging unlocked from now");
            unlockDragging();
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
        workshopPlate1.setFill(new ImagePattern(img));

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
        workshopPlates = new Circle[]{workshopPlate0,workshopPlate1, workshopPlate2,workshopPlate3,workshopPlate4,workshopPlate5,workshopPlate6,workshopPlate7,workshopPlate8};
        workshop0 = new Node[]{w0Tile0, w0Tile1, w0Tile2, w0Tile3,workshopPlate0};
        workshop1 = new Node[]{w1Tile0, w1Tile1, w1Tile2, w1Tile3,workshopPlate1};
        workshop2 = new Node[]{w2Tile0, w2Tile1, w2Tile2, w2Tile3,workshopPlate2};
        workshop3 = new Node[]{w3Tile0, w3Tile1, w3Tile2, w3Tile3,workshopPlate3};
        workshop4 = new Node[]{w4Tile0, w4Tile1, w4Tile2, w4Tile3,workshopPlate4};
        workshop5 = new Node[]{w5Tile0, w5Tile1, w5Tile2, w5Tile3,workshopPlate5};
        workshop6 = new Node[]{w6Tile0, w6Tile1, w6Tile2, w6Tile3,workshopPlate6};
        workshop7 = new Node[]{w7Tile0, w7Tile1, w7Tile2, w7Tile3,workshopPlate7};
        workshop8 = new Node[]{w8Tile0, w8Tile1, w8Tile2, w8Tile3,workshopPlate8};
        workshops = new Node[][]{workshop0,workshop1,workshop2,workshop3,workshop4,workshop5,workshop6,workshop7,workshop8};
        wall0 = new Rectangle[]{wall00,wall01,wall02,wall03,wall04};
        wall1 = new Rectangle[]{wall10,wall11,wall12,wall13,wall14};
        wall2 = new Rectangle[]{wall20,wall21,wall22,wall23,wall24};
        wall3 = new Rectangle[]{wall30,wall31,wall32,wall33,wall34};
        wall4 = new Rectangle[]{wall40,wall41,wall42,wall43,wall44};
        wall = new Rectangle[][]{wall0,wall1,wall2,wall3,wall4};
        triangles = new Polygon[]{triangle1,triangle2,triangle3,triangle4,triangle5};
        Rectangle[] pLine0 = new Rectangle[]{pLine00};
        Rectangle[] pLine1 = new Rectangle[]{pLine10, pLine11};
        Rectangle[] pLine2 = new Rectangle[]{pLine20, pLine21, pLine22};
        Rectangle[] pLine3 = new Rectangle[]{pLine30, pLine31, pLine32, pLine33};
        Rectangle[] pLine4 = new Rectangle[]{pLine40, pLine41, pLine42, pLine43, pLine44};
        patternLines = new Rectangle[][]{pLine0, pLine1, pLine2, pLine3, pLine4};
        countersTiles = new Rectangle[]{blueTileCounter, greenTileCounter, pinkTileCount, purpleTileCount, yellowTileCounter};
        playerCount = playerList.size();
        counterTexts = new Text[]{textCounter0,textCounter1,textCounter2,textCounter3,textCounter4};
        floor = new Rectangle[]{floor0,floor1,floor2,floor3,floor4,floor5,floor6};

        possibleActions.add(1);
        possibleActions.add(3);
        possibleActions.add(5);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game game = new Game(3);
        playerList = game.getPlayersList();
        workshopsFromGame = game.getWorkshops();
        centerOfWorkshop = game.getCenterOfWorkshop();


        variablesInit();
        setWorkshopVisbility();


        try {
             images = new ImagePattern[]{new ImagePattern(new Image(new FileInputStream("src/main/resources/images/yellow.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/blue.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/green.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/pink.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/purple.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/1stplayertile.png"))),
                     new ImagePattern((new Image(new FileInputStream("src/main/resources/images/empty.png")))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/dim_yellow.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/dim_blue.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/dim_green.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/dim_pink.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/dim_purple.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/workshop.png"))),
                     new ImagePattern(new Image(new FileInputStream("src/main/resources/images/triangle.png")))

             };
            gameBackground.setImage(new Image(new FileInputStream("src/main/resources/images/bg_game.png")));
            menuBackground.setImage(new Image(new FileInputStream("src/main/resources/images/bg_menu.png")));
        } catch (FileNotFoundException e) {
            System.out.println("bg not working");
        }
        firstPlayerTile.setFill(images[5]);
        int i = 0;
        for (Rectangle counter : countersTiles) {
            counter.setFill(images[i]);
            makeDragSource(counter);
            i++;
        }
         i = 0;

        for (Rectangle[] w:
                workshopTiles){
            for (Rectangle Tile :
                    w) {
                makeDragSource(Tile);
                i++;
            }

        }
        for (Rectangle[] p:
                patternLines){
            for (Rectangle Tile :
                    p) {
                Tile.setFill(images[6]);
                makeDragTarget(Tile);

            }

        }

        for(Circle c : workshopPlates){
            c.setFill(images[12]);
        }

        for(Polygon t: triangles){
            t.setFill(images[13]);
        }




        updateGraphics();

    }
    void setVisbility(Node[][] nodes,boolean state){
        for (Node[] row : nodes)
            for (Node node :
                    row)
                node.setVisible(state);
        ;
    }
    void setVisbility(Node[] nodes,boolean state){
        for (Node node : nodes) node.setVisible(state);
    }
    void setVisbility(Node node,boolean state){
        node.setVisible(state);
    }
    public void setWorkshopVisbility(){
        if (playerCount>4){
            System.out.println("max 4 players, changing playerCount to 4");
            playerCount = 4;

        }
        if (playerCount == 3){
            setVisbility(workshop8,false);
            setVisbility(workshop7,false);

        }
        if(playerCount == 2){
            setVisbility(workshop8,false);
            setVisbility(workshop7,false);
            setVisbility(workshop6,false);
            setVisbility(workshop5,false);

        }


    }


        /*
        Działanie jest proste, są dwa rodzaje elementów source i target,
        metody przyjmują element i sprawiają ze staje się source i target (można oba chyba, nie testowałem)
        zachowania w konkretnych sytuacjach opisane są w poszczególnych lambdach, da się ogarnąć co i jak
        nie jest najgorzej xD

         */
        TransferMode locker = null;
        Rectangle dragged; // przechowuje ostatni wzięty element
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
                    event.acceptTransferModes(TransferMode.MOVE);
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
                boolean success = false;

                /* let the source know whether the string was successfully
                 * transferred and used */

                event.setDropCompleted(success);

                int[] targetIndex = getIndexes(patternLines,target);
                int[] draggedIndex = getIndexes(workshopTiles,dragged);

                if (possibleActions.contains(targetIndex[0]+1)) {
                    choseMove(targetIndex[0],draggedIndex[0],draggedIndex[1]);

                    target.setFill(dragged.getFill());
                }
                System.out.println("target  r="+targetIndex[0]+" , c="+targetIndex[1]+"| dragged  workshop="+draggedIndex[0]+" , tile="+draggedIndex[1]);
                updateGraphics();

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

        int[] getIndexes(Node[][] array, Node node){
            int i =0;
            int j ;
            for (Node[] row : array) {
                j = 0;
                for (Node elem : row ) {
                    if(elem == node){
                        return  new int[]{i,j};
                    }
                    j++;
                }
                i++;
            }
            throw new RuntimeException("given node not in an array");
        }
    int[] getIndexes(Node[] array, Node node){
        int i =0;
        for (Node elem : array) {
                if(elem == node) {
                    return new int[]{i};
                }

            i++;
        }
        throw new RuntimeException("given node not in an array");
    }

    void choseMove(int patternLineIndex,int workshopIndex,int tileIndex){

    }
    void updateGraphics(){
        //workshops update
        int i = 0;
        int j ;
        for (Workshop workshop : workshopsFromGame) {
            j = 0;
            for (Tile tile : workshop.getTiles()) {
                workshopTiles[i][j].setFill(images[tile.getColorID()]);
                System.out.println(tile.getColor()+" "+tile.getColorID());
                j++;
            }
            i++;
        }
        i = 0;
        //center of workshops update
        for (Tile tile : centerOfWorkshop.getCenterOfWorkshop()) {
            counters[tile.getColorID()]+=1;
        }
        for (Text counter : counterTexts) {
            counter.setText(String.valueOf(counters[i]));
            i++;
        }
        //wall update - zrobic zaleznosc od playera
        i=0;
        for (Rectangle[] rect : wall) {
            j = 0;
            for (Rectangle tile : rect) {
                wall[i][j].setFill(images[(j+ 5 - i) % 5 + 7]);
                j++;
            }
            i++;
        }

        //patternLine - zrobic zaleznsosc od playera
        /*i=0;
        for (Rectangle[] rect : patternLines) {
            j = 0;
            for (Rectangle tile : rect) {
                i
                tile.setFill(images[6]);
                j++;
            }
            i++;
        }*/

        //floor - zrobic zaleznsosc od playera
        for (Rectangle rect : floor) {
            rect.setFill(images[6]);

        }

    }
    void resetTiles(){
            int i =0;
            int j = 0;
        for (Rectangle[] p:
                patternLines){
            for (Rectangle Tile :
                    p) {
                Tile.setFill(images[6]);


            }

        }
        i=0;
        for (Rectangle[] rect : wall) {
            j = 0;
            for (Rectangle tile : rect) {
                wall[i][j].setFill(images[(j+ 5 - i) % 5 + 7]);
                j++;
            }
            i++;
        }

    for (Rectangle rect : floor) {
        rect.setFill(images[6]);
    }

    }



}

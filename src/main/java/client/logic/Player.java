package client.logic;


import client.views.GameController;
import server.Logic.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player implements Serializable {
    private int progress = 0;
    private final int id;
    private ArrayList<Tile> roundsTiles = new ArrayList<>();
    private PlayersBoard playersBoard = new PlayersBoard();
    private Floor floor = new Floor();
    private String chosenColor;

    public PlayersBoard getPlayersBoard() {
        return playersBoard;
    }

    public Player(int id) {
        this.id = id;
    }

    public ArrayList<Tile> getRoundsTiles() {
        return roundsTiles;
    }

    public boolean[][] getMatchedTiles() {
        return playersBoard.getMatchedTiles();
    }

    public int getId() {
        return id;
    }

    public void calculateRoundPoints() {
        this.progress = GameStatus.updateProgress(progress, playersBoard, floor);
        this.floor = new Floor();
    }



    /**
     * @param row rzad
     *
     *
     * sprawdzamy czy gracz juz polozyl kafelek danego koloru w danym rzedzie
     * */

    private boolean isAlreadyPut(int row) {
        for (int i = 0; i < 5; i++) {
            if(GameController.gameMode==2) {
                if (GUIGame.getBoard()[row][i].getColor().equals(this.roundsTiles.get(0).getColor()) && this.playersBoard.getMatchedTiles()[row][i]) {
                    return true;
                }
            }
            else{
                if (Game.getBoard()[row][i].getColor().equals(this.roundsTiles.get(0).getColor()) && this.playersBoard.getMatchedTiles()[row][i]) {
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * sprawdzamy czy dana linia wzoru jest zapelniona, jesli jest zapelniona w pewnej czesci sprawdzamy kafelki jakego koloru tam sa
     * @param row dany rzad
     * @return true/false
     */
    private boolean isThatPatternLineFilled(int row) {
        if (this.playersBoard.getPatternLine().get(row)[row] != null) {
            if (this.playersBoard.getPatternLine().get(row)[row].getColor().equals(this.roundsTiles.get(0).getColor()) && this.playersBoard.getPatternLine().get(row)[0] == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }



    /**
     * metoda zwraca liste liczb, ktore reprezentuja rzedy na ktore gracz moze wlozyc kafelki z round tiles
     * @return lista
     */
    public ArrayList<Integer> possibleActions() {
        ArrayList<Integer> possibleActions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.isAlreadyPut(i) || this.isThatPatternLineFilled(i)) {
                    break;
                }
                if (j == 4) {
                    possibleActions.add(i);
                }
            }
        }
        return possibleActions;
    }

    public Floor getFloor() {
        return floor;
    }



    /**
     *
     * @return metoda pobiera od gracza infofrmacje gdzie chce on polozyc kafelki wartsoci <0,4>, jesli nie ma mozliowsci zeby polozyl je gdziekolowiek, to metoda zwraca 5
     */
    public int chooseAction() {
        boolean doIHave1stplayertile = false;
        for (Tile roundsTile : this.roundsTiles) {
            if (roundsTile.getColor().equals("1st player tile")) {
                doIHave1stplayertile = true;
                break;
            }
        }
        if (!doIHave1stplayertile) {
            System.out.println("Masz " + this.roundsTiles.size() + " kafelki koloru " + this.roundsTiles.get(0));
        } else {
            System.out.println("Masz " + (this.roundsTiles.size() - 1) + " kafelki koloru " + this.roundsTiles.get(1) + " oraz kafelek: " + this.roundsTiles.get(0));
        }
        System.out.println("twoja tablica zapelnienia");
        for (boolean[] tab : this.playersBoard.getMatchedTiles()) {
            for (boolean b : tab) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("pelna tablica");
        for (Tile[] tab : GUIGame.getBoard()) {
            for (Tile tile : tab) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("twoje linie wzorow: ");
        for (Tile[] tiles : this.playersBoard.getPatternLine()) {
            for (Tile t : tiles) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Masz: " + progress + " punktow");
        System.out.println();
        System.out.println("Mozesz wybrac akcje sposrod: ");
        ArrayList<Integer> actions = this.possibleActions();
        if (actions.size() != 0) {
            for (int i : actions) {
                System.out.println(i + 1);
            }
            System.out.println("akcja: ");
            if(GameController.gameMode==1){
                Scanner sc3=new Scanner(System.in);
                return sc3.nextInt();
            }
            return GameController.choosenAction;
        } else {
            System.out.println("niestety nic nie mozesz zrobic, wszystkie twoje kafelki z tej rundy laduja na podlodze");
            return 5;
        }
    }



    /**
     * metoda przechodzi po wszystkich liniach wzoru i jesli ktoras jest pelna to odpowiedni kafelek zostanie wypelniony w tablicy matched tiles
     */
    public void moveTiles() {
        for (int i = 0; i < 5; i++) {
            int temp = 0;
            for (Tile t : this.playersBoard.getPatternLine().get(i)) {
                if (t != null) {
                    temp++;
                }
            }
            if (temp == i + 1) {
                for (int j = 0; j < 5; j++) {
                    if(GameController.gameMode==2) {
                        if (GUIGame.getBoard()[i][j].getColor().equals(this.chosenColor)) {
                            this.playersBoard.getMatchedTiles()[i][j] = true;
                            break;
                        }
                    }
                    else{
                        if (Game.getBoard()[i][j].getColor().equals(this.chosenColor)) {
                            this.playersBoard.getMatchedTiles()[i][j] = true;
                            break;
                        }
                    }
                }
                Arrays.fill(this.playersBoard.getPatternLine().get(i), null);
            }
        }
    }



    /**
     * przenoszenie z tablicy round tiles na odpowiednia linie wzorow
     * @param action akcja zwrocona przez metode chooseAction
     */
    public void putTilesToPatternLine(int action) {
        if (action < 5) {
            for (int i = 0; i < this.roundsTiles.size(); i++) {
                if (this.roundsTiles.get(i).getColor().equals("1st player tile")) {
                    this.floor.getFloor().add(this.roundsTiles.get(i));
                    this.roundsTiles.remove(i);
                    break;
                }
            }
            this.chosenColor = this.roundsTiles.get(0).getColor();
            for (int i = action; i >= 0; i--) {
                if (this.playersBoard.getPatternLine().get(action)[i] == null) {
                    if (this.roundsTiles.size() != 0) {
                        this.playersBoard.getPatternLine().get(action)[i] = this.roundsTiles.get(0);
                    } else {
                        break;
                    }
                    this.roundsTiles.remove(this.roundsTiles.size() - 1);
                }
            }
        }
        while (this.roundsTiles.size() != 0) {
            this.floor.getFloor().add(this.roundsTiles.get(0));
            this.roundsTiles.remove(0);
        }
    }
    public void resetRoundTiles(){
        this.roundsTiles=new ArrayList<>();
    }
    public int getProgress() {
        return progress;
    }

    public PatternLine getPatternLine() {
        return playersBoard.getPatternLineObject();
    }
}

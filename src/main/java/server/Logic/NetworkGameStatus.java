package server.Logic;

import client.logic.NetworkPlayer;
import server.Configuration.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class NetworkGameStatus implements Serializable {
    private static Tile[][] board = new Tile[5][5];
    private final int whoseTurnIsIt; //id gracza którego jest tura
    private final ArrayList<NetworkPlayer> playersList; //lista graczy
    private final Workshop[] workshops; //tablica workshopów
    private final CenterOfWorkshop centerOfWorkshop; //Środek workshopów
    private final int[] tilesAmounts; //pozostała ilość poszczególnych kolrów kafelków(yellow blue green pink purple)
    private final int round; //kolejka w rundzie
    private final boolean is1stplayerstileatthecenter; //czy 1playerstile jest na środku workshopów
    private final boolean isGameFinished; //czy gra dobiegła do końca

    /**
     * Klasa reprezentująca status rozgrywki, na jej podstawie gracze sieciowi odczytują dane rozgrywki
     * @param playersList lista graczy
     * @param workshops tablica warsztatów
     * @param centerOfWorkshop środek warszatów
     * @param tilesAmounts ile kafelkow każdego koloru zostało
     * @param round kolejka
     * @param is1stplayerstileatthecenter czy kafelek 1playerstile znajduje sie w srodku warszatatów
     * @param whoseTurnIsIt numer gracza którego kolej na ruch jest teraz
     * @param isGameFinished  czy gra już się skończyła
     * @param board
     */
    public NetworkGameStatus(ArrayList<NetworkPlayer> playersList, Workshop[] workshops, CenterOfWorkshop centerOfWorkshop, int[] tilesAmounts, int round, boolean is1stplayerstileatthecenter,int whoseTurnIsIt,boolean isGameFinished,Tile[][] board) {
        this.playersList = playersList;
        this.workshops = workshops;
        this.centerOfWorkshop = centerOfWorkshop;
        this.tilesAmounts = tilesAmounts;
        this.round = round;
        this.is1stplayerstileatthecenter = is1stplayerstileatthecenter;
        this.whoseTurnIsIt=whoseTurnIsIt;
        this.isGameFinished = isGameFinished;
        this.board = board;
    }

    //

    /**
     * Update liczby punktów
     * @param progress liczba punktów gracza
     * @param playersBoard plansza gracza
     * @param floor podłoga na której znajdują się kafelki
     * @return
     */
    public static int updateProgress(int progress, PlayersBoard playersBoard, Floor floor){
        //horizontal check
        for(boolean[] tab : playersBoard.getMatchedTiles()){
            int tempPoints=0;
            for(boolean tile : tab){
                if(tile){
                    tempPoints++;
                }
                else{
                    progress+=tempPoints;
                    tempPoints=0;
                }
                if(tempPoints==5){
                    progress+=2;
                }
            }
        }
        //vertical check
        for(int i=0;i<5;i++){
            int tempPoints=0;
            for(int j=0;j<5;j++){
                if(playersBoard.getMatchedTiles()[j][i]){
                    tempPoints++;
                }
                else{
                    if(tempPoints>1) {
                        progress += tempPoints;
                    }
                    tempPoints=0;
                }
                if(tempPoints==5){
                    progress+=7;
                }
            }
        }

        //colors check
        for(int i=0;i<5;i++){
            if(playersBoard.getMatchedTiles()[0][i] && playersBoard.getMatchedTiles()[1][(i+1)%5] && playersBoard.getMatchedTiles()[2][(i+2)%5] && playersBoard.getMatchedTiles()[3][(i+3)%5] && playersBoard.getMatchedTiles()[4][(i+4)%5]){
                progress+=10;
            }
        }

        //floor check
        int x=floor.getFloor().size();
        progress -= Config.getFloorPoints(x);
        progress = progress < 0 ? 0 : progress;
        return progress;
    }



    //Gettery + tostring

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public int getWhoseTurnIsIt() {
        return whoseTurnIsIt;
    }

    public ArrayList<NetworkPlayer> getPlayersList() {
        return playersList;
    }

    public Workshop[] getWorkshops() {
        return workshops;
    }

    public CenterOfWorkshop getCenterOfWorkshop() {
        return centerOfWorkshop;
    }

    public int[] getTilesAmounts() {
        return tilesAmounts;
    }

    public int getRound() {
        return round;
    }

    public boolean isIs1stplayerstileatthecenter() {
        return is1stplayerstileatthecenter;
    }

    public static Tile[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "NetworkGameStatus{" +
                "whoseTurnIsIt=" + whoseTurnIsIt +
                ", playersList=" + playersList +
                ", workshops=" + Arrays.toString(workshops) +
                ", centerOfWorkshop=" + centerOfWorkshop +
                ", tilesAmounts=" + Arrays.toString(tilesAmounts) +
                ", round=" + round +
                ", is1stplayerstileatthecenter=" + is1stplayerstileatthecenter +
                ", isGameFinished=" + isGameFinished +
                '}';
    }
}
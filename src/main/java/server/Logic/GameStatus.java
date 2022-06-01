package server.Logic;

import client.logic.Player;
import server.Configuration.Config;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStatus implements Serializable {
    private final ArrayList<Player> playersList;
    private final Workshop[] workshops;
    private final CenterOfWorkshop centerOfWorkshop;
    private final int[] tilesAmounts; //yellow blue green pink purple
    private final int round;
    private final boolean is1stplayerstileatthecenter;


    public GameStatus(ArrayList<Player> playersList, Workshop[] workshops, CenterOfWorkshop centerOfWorkshop, int[] tilesAmounts, int round, boolean is1stplayerstileatthecenter) {
        this.playersList = playersList;
        this.workshops = workshops;
        this.centerOfWorkshop = centerOfWorkshop;
        this.tilesAmounts = tilesAmounts;
        this.round = round;
        this.is1stplayerstileatthecenter = is1stplayerstileatthecenter;
    }


    public ArrayList<Player> getPlayersList() {
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

    public boolean is1stplayerstileatthecenter() {
        return is1stplayerstileatthecenter;
    }

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


}

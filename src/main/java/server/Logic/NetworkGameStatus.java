package server.Logic;

import client.logic.NetworkPlayer;

import java.io.Serializable;
import java.util.ArrayList;

public class NetworkGameStatus implements Serializable {
    private final int whoseTurnIsIt;
    private final ArrayList<NetworkPlayer> playersList;
    private final Workshop[] workshops;
    private final CenterOfWorkshop centerOfWorkshop;
    private final int[] tilesAmounts; //yellow blue green pink purple
    private final int round;
    private final boolean is1stplayerstileatthecenter;
    private final boolean isGameFinished;

    public NetworkGameStatus(ArrayList<NetworkPlayer> playersList, Workshop[] workshops, CenterOfWorkshop centerOfWorkshop, int[] tilesAmounts, int round, boolean is1stplayerstileatthecenter,int whoseTurnIsIt,boolean isGameFinished) {
        this.playersList = playersList;
        this.workshops = workshops;
        this.centerOfWorkshop = centerOfWorkshop;
        this.tilesAmounts = tilesAmounts;
        this.round = round;
        this.is1stplayerstileatthecenter = is1stplayerstileatthecenter;
        this.whoseTurnIsIt=whoseTurnIsIt;
        this.isGameFinished = isGameFinished;
    }

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
}
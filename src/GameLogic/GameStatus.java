package GameLogic;

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

    public boolean isIs1stplayerstileatthecenter() {
        return is1stplayerstileatthecenter;
    }


}

package server.Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class CenterOfWorkshop implements Serializable {
    private ArrayList<Tile> centerOfWorkshop = new ArrayList<>();

    public ArrayList<Tile> getCenterOfWorkshop() {
        return centerOfWorkshop;
    }

    @Override
    public String toString() {
        return "CenterOfWorkshop{" +
                "centerOfWorkshop=" + centerOfWorkshop +
                '}';
    }
}

package GameLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class Floor implements Serializable {
    private ArrayList<Tile> floor=new ArrayList<>();

    public ArrayList<Tile> getFloor() {
        return floor;
    }
}
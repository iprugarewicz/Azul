package server.Logic;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Klasa warszatu w którym przechowujemy kafelki
 */
public class Workshop implements Serializable {
    private Tile[] tiles;
    public Workshop(){
        this.tiles=new Tile[4];
    }
    public Tile[] getTiles(){
        return this.tiles;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }
}

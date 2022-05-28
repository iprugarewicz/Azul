package server;

import java.io.Serializable;

public class Workshop implements Serializable {
    private Tile[] tiles;
    public Workshop(){
        this.tiles=new Tile[4];
    }
    public Tile[] getTiles(){
        return this.tiles;
    }
}

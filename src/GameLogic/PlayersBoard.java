package GameLogic;


import java.io.Serializable;

public class PlayersBoard  implements Serializable {
    private boolean[][] matchedTiles=new boolean[5][5];

    public boolean[][] getMatchedTiles(){
        return this.matchedTiles;
    }
}

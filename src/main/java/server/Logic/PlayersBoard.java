package server.Logic;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayersBoard  implements Serializable {
    private boolean[][] matchedTiles=new boolean[5][5];
    private PatternLine patternLine = new PatternLine();

    public boolean[][] getMatchedTiles(){
        return this.matchedTiles;
    }

    public ArrayList<Tile[]> getPatternLine(){
        return this.patternLine.getPatternLine();
    }

    public PatternLine getPatternLineObject() {return this.patternLine; }

    public void setPatternLine(PatternLine patternLine) {
        this.patternLine = patternLine;
    }

    @Override
    public String toString() {
        return "PlayersBoard{" +
                "matchedTiles=" + Arrays.deepToString(matchedTiles) +
                ", patternLine=" + patternLine +
                '}';
    }
}

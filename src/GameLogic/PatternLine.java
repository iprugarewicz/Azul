package GameLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class PatternLine implements Serializable {
    private ArrayList<Tile[]> patternLine=new ArrayList<>();
    public PatternLine(){
        for(int i=1;i<=5;i++){
            patternLine.add(new Tile[i]);
        }
    }
    public ArrayList<Tile[]> getPatternLine(){
        return this.patternLine;
    }


}

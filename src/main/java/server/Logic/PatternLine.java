package server.Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * PatternLine to lewa część planszy każdego gracza. Kładziemy tam kafelki z warsztatów
 */
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

    @Override
    public String toString() {
        String s = "";
         s+= "PatternLine{" + "patternLine=";
        for (Tile[] t: patternLine) {
            s += Arrays.toString(t);
        }
        s += "}";
        return s;
    }
}

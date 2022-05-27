package GameLogic;

import java.io.Serializable;
import java.util.Objects;

public class Tile implements Serializable {
    private final String color;
    private final int x;
    private final int y;
    private final String[] colors = {"yellow", "blue", "green", "pink", "purple","1st player tile"};
    public Tile(int color,int x, int y){

        this.color= colors[color];
        this.x=x;
        this.y=y;
    }
    public int getColorNumber(){
        for(int i=0;i<5;i++) {
            if (this.color.equals(colors[i])){
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString(){
        return this.color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y && Objects.equals(color, tile.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, x, y);
    }
}

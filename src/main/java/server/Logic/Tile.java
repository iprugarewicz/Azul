package server.Logic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Tile implements Serializable {
    private final String color;
    private final int colorID;
    private final String[] colors = {"yellow", "blue", "green", "pink", "purple","1st player tile"};

    public Tile(int color){
        this.color= colors[color];
        this.colorID=color;
    }

    public int getColorNumber(){
        for(int i=0;i<5;i++) {
            if (this.color.equals(colors[i])){
                return i;
            }
        }
        return -1;
    }

    public int getColorID() {
        return colorID;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(color, tile.color) && Arrays.equals(colors, tile.colors);
    }

    @Override
    public String toString(){
        return this.color;
    }


}

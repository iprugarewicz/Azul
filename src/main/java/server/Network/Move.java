package server.Network;


import java.io.Serializable;

//this class describes user's move
public class Move implements Serializable {
    private int workshop;
    private int move;
    private String color;
    public Move(int workshop,int move,String color){
        this.workshop=workshop;
        this.move=move;
        this.color=color;
    }

    public int getWorkshop() {
        return workshop;
    }

    public int getMove() {
        return move;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Move{" +
                "workshop=" + workshop +
                ", move=" + move +
                ", color='" + color + '\'' +
                '}';
    }
}

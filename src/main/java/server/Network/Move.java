package server.Network;

public class Move {
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
}
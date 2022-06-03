package server.Network;


import java.io.Serializable;

//Klasa opisuje ruch gracza i jest wysyłana do serwera
public class Move implements Serializable {
    private int workshop;
    private int move;
    private String color;

    public Move(int workshop,int move,String color){
        this.workshop=workshop; // z którego warsztatu bierzemy
        this.move=move; // na które miejsce wkładamy kafelki
        this.color=color; // kolor pobranych kafelków
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

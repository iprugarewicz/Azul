package server.Logic;

import java.io.Serializable;

public class IsyourTurn implements Serializable {
    boolean isYourTurn;

    public IsyourTurn(boolean isYourTurn) {
        this.isYourTurn = isYourTurn;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }
}

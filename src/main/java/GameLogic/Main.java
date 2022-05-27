package GameLogic;

import ObjectsProcessing.ObjectsSerializer;
import ObjectsProcessing.ReadGameStatusFromFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
	   //Game game=new Game(2);
       //game.letsplay();
        Game game2= ReadGameStatusFromFile.readGameStatus(ObjectsSerializer.deserializeGameStatus("pierwszy.ser"));
        game2.letsplay();
    }
}

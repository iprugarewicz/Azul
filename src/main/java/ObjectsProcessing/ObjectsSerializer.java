package ObjectsProcessing;

import GameLogic.GameStatus;
import GameLogic.PatternLine;
import GameLogic.Player;
import GameLogic.Tile;

import java.io.*;
import java.util.ArrayList;

public class ObjectsSerializer implements Serializable {
    public static void serializePatterLines(ArrayList<Player> ar) throws IOException {
        for(Player p : ar) {
            FileOutputStream fos = new FileOutputStream("patternline.ser"+p.getId());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p.getPatternLine());
            fos.close();
            oos.close();
        }
    }

    public static void deserializePatterLines(ArrayList<Player> ar) throws IOException, ClassNotFoundException {
        for(Player p : ar) {
            FileInputStream fis = new FileInputStream("patternline.ser"+p.getId());
            ObjectInputStream ois = new ObjectInputStream(fis);
            PatternLine pl=(PatternLine) ois.readObject();
            for(Tile[] tab : pl.getPatternLine()){
                for(Tile t : tab){
                    System.out.print(t+" ");
                }
                System.out.println();
            }
            fis.close();
            ois.close();
        }
    }

    public static void serializeGameStatus(GameStatus gs,String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gs);
        fos.close();
        oos.close();
    }

    public static GameStatus deserializeGameStatus(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GameStatus gs=(GameStatus) ois.readObject();
        fis.close();
        ois.close();
        return gs;
    }

}

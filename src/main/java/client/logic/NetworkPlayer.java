package client.logic;

import client.network.Klient;
import server.Logic.*;
import server.Network.Move;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NetworkPlayer implements Serializable {
    private int progress = 0;
    private final int id;
    private ArrayList<Tile> roundsTiles = new ArrayList<>();
    private PlayersBoard playersBoard = new PlayersBoard();
    private Floor floor = new Floor();
    private String chosenColor;

    public NetworkPlayer(int id) {
        this.id = id;
    }

    public void playGame(Klient klient) throws IOException, ClassNotFoundException {
        OutputStream os = klient.getSocket().getOutputStream();
        InputStream is = klient.getSocket().getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        boolean on = true;

        while(on) {
            NetworkGameStatus gS = (NetworkGameStatus) ois.readObject();
            System.out.println("Odebtano Gamestatus");
            if(gS.isGameFinished()){break;}
            System.out.println(Arrays.toString(gS.getWorkshops()));
            System.out.println(gS.getCenterOfWorkshop());
            System.out.println();
            System.out.println(gS.getPatternLines().get(id-1));

            this.playersBoard.setPatternLine(gS.getPatternLines().get(id-1));
            this.roundsTiles = gS.getPlayersList().get(id-1).getRoundsTiles();
            //tu trzeba przypisac dane do tego networkplayera

            //tu gui wyswietla stan gry

            if(gS.getWhoseTurnIsIt() == this.id){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Podaj numer warsztatu");
                int a = Integer.parseInt(scanner.nextLine()); // workshop
                System.out.println("Podaj kolor");
                String b = scanner.nextLine(); // color
                System.out.println("Podaj gdzie chcesz to wlozyc");
                int c = Integer.parseInt(scanner.nextLine()); // move
                Move mv = new Move(a, c ,b);
                oos.writeObject(mv);
            }
           // ArrayList<NetworkPlayer> playerArrayList = (ArrayList<NetworkPlayer>) ois.readObject();
           // for (NetworkPlayer p : playerArrayList) {
           //     System.out.println("Gracz: " + p.getId() + " zdobył" + p.getProgress() + " pnuktów.");
           // }
        }


    }

    public ArrayList<Tile> getRoundsTiles() {
        return roundsTiles;
    }

    public boolean[][] getMatchedTiles() {
        return playersBoard.getMatchedTiles();
    }

    public int getId() {
        return id;
    }

    public void calculateRoundPoints() {
        this.progress = NetworkGameStatus.updateProgress(progress, playersBoard, floor);
        this.floor = new Floor();
    }

    private boolean isAlreadyPut(int row) {
        if(this.roundsTiles.size()==0){
            return false;
        }
        for (int i = 0; i < 5; i++) {
            if (NetworkGame.getBoard()[row][i].getColor().equals(this.roundsTiles.get(0).getColor()) && this.playersBoard.getMatchedTiles()[row][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean isThatPatternLineFilled(int row) {
        if (this.playersBoard.getPatternLine().get(row)[row] != null) {
            if (this.playersBoard.getPatternLine().get(row)[row].getColor().equals(this.roundsTiles.get(0).getColor()) && this.playersBoard.getPatternLine().get(row)[0] == null) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Integer> possibleActions() {
        ArrayList<Integer> possibleActions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.isAlreadyPut(i) || this.isThatPatternLineFilled(i)) {
                    break;
                }
                if (j == 4) {
                    possibleActions.add(i);
                }
            }
        }
        return possibleActions;
    }

    public int chooseAction() {
        boolean doIHave1stplayertile = false;
        for (Tile roundsTile : this.roundsTiles) {
            if (roundsTile.getColor().equals("1st player tile")) {
                doIHave1stplayertile = true;
                break;
            }
        }
        if (!doIHave1stplayertile) {
            System.out.println("Masz " + this.roundsTiles.size() + " kafelki koloru " + this.roundsTiles.get(0));
        } else {
            System.out.println("Masz " + (this.roundsTiles.size() - 1) + " kafelki koloru " + this.roundsTiles.get(1) + " oraz kafelek: " + this.roundsTiles.get(0));
        }
        System.out.println("twoja tablica zapelnienia");
        for (boolean[] tab : this.playersBoard.getMatchedTiles()) {
            for (boolean b : tab) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("pelna tablica");
        for (Tile[] tab : Game.getBoard()) {
            for (Tile tile : tab) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("twoje linie wzorow: ");
        for (Tile[] tiles : this.playersBoard.getPatternLine()) {
            for (Tile t : tiles) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Masz: " + progress + " punktow");
        System.out.println();
        System.out.println("Mozesz wybrac akcje sposrod: ");
        ArrayList<Integer> actions = this.possibleActions();
        if (actions.size() != 0) {
            for (int i : actions) {
                System.out.println(i + 1);
            }
            System.out.println("akcja: ");
            Scanner sc = new Scanner(System.in);
            return sc.nextInt() - 1;
        } else {
            System.out.println("niestety nic nie mozesz zrobic, wszystkie twoje kafelki z tej rundy laduja na podlodze");
            return 5;
        }
    }

    public void moveTiles() {
        for (int i = 0; i < 5; i++) {
            int temp = 0;
            for (Tile t : this.playersBoard.getPatternLine().get(i)) {
                if (t != null) {
                    temp++;
                }
            }
            if (temp == i + 1) {
                for (int j = 0; j < 5; j++) {
                    if (NetworkGame.getBoard()[i][j].getColor().equals(this.chosenColor)) {
                        this.playersBoard.getMatchedTiles()[i][j] = true;
                        break;
                    }
                }
                Arrays.fill(this.playersBoard.getPatternLine().get(i), null);
            }
        }
    }

    public void putTilesToPatternLine(int action) {
        if (action < 5) {
            for (int i = 0; i < this.roundsTiles.size(); i++) {
                if (this.roundsTiles.get(i).getColor().equals("1st player tile")) {
                    this.floor.getFloor().add(this.roundsTiles.get(i));
                    this.roundsTiles.remove(i);
                    break;
                }
            }
            this.chosenColor = this.roundsTiles.get(0).getColor();
            for (int i = action; i >= 0; i--) {
                if (this.playersBoard.getPatternLine().get(action)[i] == null) {
                    if (this.roundsTiles.size() != 0) {
                        this.playersBoard.getPatternLine().get(action)[i] = this.roundsTiles.get(0);
                    } else {
                        break;
                    }
                    this.roundsTiles.remove(this.roundsTiles.size() - 1);
                }
            }
        }
        while (this.roundsTiles.size() != 0) {
            this.floor.getFloor().add(this.roundsTiles.get(0));
            this.roundsTiles.remove(0);
        }
    }

    public int getProgress() {
        return progress;
    }

    public PatternLine getPatternLine() {
        return playersBoard.getPatternLineObject();
    }

    public PlayersBoard getPlayersBoard() {
        return playersBoard;
    }

    public Floor getFloor() {
        return floor;
    }

    public String getChosenColor() {
        return chosenColor;
    }

}

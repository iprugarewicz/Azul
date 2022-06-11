package server.Logic;

import client.logic.Player;
import client.views.GameController;
import server.ObjectsProcessing.ObjectsSerializer;
import server.Configuration.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GUIGame {
    //tablica ktora reprezentuje zapelniona plansze, jest ona porownywana z tablica ktora gracz zapelnia booleanami
    private static final Tile[][] board = new Tile[5][5];
    private ArrayList<Player> playersList = new ArrayList<>();
    private Workshop[] workshops;
    private CenterOfWorkshop centerOfWorkshop = new CenterOfWorkshop();
    private int[] tilesAmounts = {20, 20, 20, 20, 20}; //yellow blue green pink purple
    private int round=1;
    private boolean is1stplayerstileatthecenter=false;
    private boolean wasGameStatesReadFromaFile=false;
    private GameStatus gameStatus;

    public GUIGame(int players) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Tile((j + 5 - i) % 5);
            }
        }

        if(players > 4 || players < 2){
            throw new IllegalArgumentException("Not allowed number of players, try 2-4 instead.");
        }

        for (int i = 0; i < players; i++) {
            playersList.add(new Player(i + 1));
        }

        this.workshops = new Workshop[Config.getWorkshopsAmount(playersList.size())];
        for(int i=0;i<Config.getWorkshopsAmount(playersList.size());i++){
            this.workshops[i]=new Workshop();
        }
        generateWorkshops();
    }

    //metoda generuje losowo zawartosc warsztatow (w kazdym warsztacie zawsze sa 4 kafelki)
    private void generateWorkshops() {
        Random r = new Random();
        for (int i = 0; i < workshops.length; i++) {
            for (int j = 0; j < 4; j++) {
                int ran = Math.abs(r.nextInt()) % 5;
                if (this.tilesAmounts[ran] != 0) {
                    workshops[i].getTiles()[j] = new Tile(ran);
                    tilesAmounts[ran]--;
                } else {
                    j--;
                }
            }
        }
    }

    public static Tile[][] getBoard() {
        return board;
    }



    public void setWasGameStatesReadFromaFile(boolean wasGameStatesReadFromaFile) {
        this.wasGameStatesReadFromaFile = wasGameStatesReadFromaFile;
    }


    //glowna metoda w niej odbywa sie rozgrywka
    public void letsplay() throws IOException {
        GameController.gameMode=2;
        //jesli gra zostala wczytana z pliku nie musimy generowac warsztatow
        if(!this.wasGameStatesReadFromaFile) {
            this.generateWorkshops();
        }
        boolean hasSomeBodyFinished = false;
        //petla ktora wykonuje sie dopoki, ktorys z graczy nie wypelni w calosci calego wiersza
        while (!hasSomeBodyFinished) {
            for (Player p : this.playersList) {

                GameController.choosenColor=null;
                GameController.choosenAction=-1;
                GameController.workshopsFromGame=this.workshops;
                GameController.hasUserMadeCorrectmove=false;
                GameController.playerList=this.playersList;
                GameController.currentPlayersBoard=p.getPlayersBoard();
                GameController.currentPlayersFloor=p.getFloor();
                GameController.updateGraphics();
                boolean areWorkshopsEmpty=false;

                //pokazanie graczowi zawartosci warsztatow oraz centrum planszy
                for (int i = 0; i < workshops.length; i++) {
                    System.out.println(i + 1 + ". workshop: ");
                    for (int j = 0; j < 4; j++) {
                        if (workshops[i].getTiles()[j] != null) {
                            System.out.println("   " + workshops[i].getTiles()[j]);
                        }
                    }
                }
                if (this.centerOfWorkshop.getCenterOfWorkshop().size() != 0) {
                    System.out.print(workshops.length+1+". centrum planszy: ");
                    System.out.println();
                    for (Tile t : this.centerOfWorkshop.getCenterOfWorkshop()) {
                        System.out.println("   " + t);
                    }
                }
                System.out.println();
                System.out.println("teraz jest  tura gracza: "+p.getId());
                System.out.println("Wybierz warsztat oraz podaj, ktore kolory chcesz zabrac:");
                System.out.println("warsztat: ");
                int ws = GameController.choosenWorkshop;
                String c = GameController.choosenColor;
                while(!GameController.hasUserMadeCorrectmove){
                    ws = GameController.choosenWorkshop;
                    c = GameController.choosenColor;
                    if(ws !=0 && c!=null && ws-1!=workshops.length){
                        for(Tile t: workshops[ws-1].getTiles()){
                            if(t.getColor().equals(c)){
                                p.getRoundsTiles().add(new Tile(t.getColorID()));
                            }
                        }

                        GameController.possibleActions=p.possibleActions();
                    }
                    if(c != null && ws - 1 == workshops.length){
                        for(Tile t: centerOfWorkshop.getCenterOfWorkshop()){
                            if(t.getColor().equals(c)){
                                p.getRoundsTiles().add(new Tile(t.getColorID()));

                            }
                        }

                        GameController.possibleActions=p.possibleActions();

                    }
                    p.resetRoundTiles();

                }
                GameController.hasUserMadeCorrectmove=false;
                //pobranie od gracza informacji o warsztacie ktory sobie wybral oraz informacji o kolorze kafelkow ktore chce z tego warsztatu zabrac
                if (ws == workshops.length+1) {
                    // srodek planszy
                    //zabranie ze srodka planszy kafelkow ktore gracz chce zabrac, oraz w przypadku gdy na srodku jest kafelek pierwszego gracza, przekazanie go graczowi
                    ArrayList<Integer> indexesToRemove=new ArrayList<>();
                    if(is1stplayerstileatthecenter){
                        for(int i=0;i< centerOfWorkshop.getCenterOfWorkshop().size();i++){
                            if(centerOfWorkshop.getCenterOfWorkshop().get(i).getColor().equals("1st player tile")){
                                p.getRoundsTiles().add(centerOfWorkshop.getCenterOfWorkshop().get(i));
                                centerOfWorkshop.getCenterOfWorkshop().remove(i);
                                GameController.counters[5]++;
                                break;
                            }
                        }
                    }
                    for (int i = 0; i <centerOfWorkshop.getCenterOfWorkshop().size(); i++) {
                        if (this.centerOfWorkshop.getCenterOfWorkshop().get(i).getColor().equals(c)) {
                            switch (c) {
                                case "yellow":
                                    p.getRoundsTiles().add(new Tile(0));
                                    break;
                                case "blue":
                                    p.getRoundsTiles().add(new Tile(1));
                                    break;
                                case "green":
                                    p.getRoundsTiles().add(new Tile(2));
                                    break;
                                case "pink":
                                    p.getRoundsTiles().add(new Tile(3));
                                    break;
                                case "purple":
                                    p.getRoundsTiles().add(new Tile(4));
                                    break;
                            }
                            indexesToRemove.add(i);
                        }
                    }
                    GameController.counters[centerOfWorkshop.getCenterOfWorkshop().get(indexesToRemove.get(0)).getColorID()]=0;
                    for(int i=0;i<indexesToRemove.size();i++){
                        centerOfWorkshop.getCenterOfWorkshop().remove(indexesToRemove.get(i)-i);
                    }
                } else {
                    //przejscie po warsztacie ktory wybral gracz i zabranie odpowiednich kafelkow
                    for (int i = 0; i < 4; i++) {
                        if (workshops[ws - 1].getTiles()[i].getColor().equals(c)) {
                            switch (c) {
                                case "yellow":
                                    p.getRoundsTiles().add(new Tile(0));
                                    break;
                                case "blue":
                                    p.getRoundsTiles().add(new Tile(1));
                                    break;
                                case "green":
                                    p.getRoundsTiles().add(new Tile(2));
                                    break;
                                case "pink":
                                    p.getRoundsTiles().add(new Tile(3));
                                    break;
                                case "purple":
                                    p.getRoundsTiles().add(new Tile(4));
                                    break;
                            }

                        } else {
                            this.centerOfWorkshop.getCenterOfWorkshop().add(new Tile(workshops[ws - 1].getTiles()[i].getColorNumber()));
                            GameController.counters[workshops[ws - 1].getTiles()[i].getColorNumber()]++;

                        }
                        workshops[ws - 1].getTiles()[i] = null;
                    }

                }
                GameController.workshopsFromGame=this.workshops;
                GameController.centerOfWorkshop=this.centerOfWorkshop;
                System.out.println(p.getRoundsTiles());
                //przenisienie kafelkow gracza z tablicy na linie wzorow (metoda, w ktorej gracz wybiera linie wzorow jest w klasie player (chooseAction()))
                p.putTilesToPatternLine(p.chooseAction());
                //przeniesienie kafelkow z linii wzorow no plansze gracza
                p.moveTiles();
                //przeliczenie puntkow po ruchu gracza
                p.calculateRoundPoints();

                //sprawdzenie czy warsztaty sa puste, jesli tak to je uzupelnoamy
                for(int i=0;i<workshops.length;i++){
                    if(centerOfWorkshop.getCenterOfWorkshop().size()!=0){
                        break;
                    }
                    boolean haveWefoundNonEmptyIndex=false;
                    for(Tile t : workshops[i].getTiles()){
                        if(t!=null){
                            haveWefoundNonEmptyIndex=true;
                            break;
                        }
                    }
                    if (haveWefoundNonEmptyIndex){
                        break;
                    }
                    if (i + 1 == workshops.length) {
                        areWorkshopsEmpty = true;
                        break;
                    }


                }
                GameController.centerOfWorkshop=this.centerOfWorkshop;
                GameController.workshopsFromGame=this.workshops;
                if(areWorkshopsEmpty) {
                    this.generateWorkshops();
                }

                if(round==1){
                    centerOfWorkshop.getCenterOfWorkshop().add(new Tile(5));
                    this.is1stplayerstileatthecenter=true;
                }
                GameController.centerOfWorkshop=this.centerOfWorkshop;
                GameController.workshopsFromGame=this.workshops;
                round++;
            }

            //sprawdzenie czy ktos skonczyl gre
            for(Player p : playersList){
                for(int i=0;i<5;i++){
                    for(int j=0;j<5;j++) {
                        if (!p.getMatchedTiles()[i][j]) {
                            break;
                        }
                        if (j == 4) {
                            hasSomeBodyFinished = true;
                        }
                    }
                }
            }

        }
        endGame();
    }
    //metoda zapisuje serializowany obiekt typu game status
    public void saveGameStatusToFile(String fileName) throws IOException {
        ObjectsSerializer.serializeGameStatus(new GameStatus(this.playersList,this.workshops,this.centerOfWorkshop,this.tilesAmounts,this.round,this.is1stplayerstileatthecenter),fileName);
    }
    //posortowanie graczy w zaleznosci od wynikow, pokazanie wynikow
    public void endGame(){
        for(int i=0;i<playersList.size();i++){
            int biggest=playersList.get(i).getProgress();
            for(int j=i+1;j<playersList.size();j++){
                if(playersList.get(j).getProgress()>biggest){
                    Player temp=playersList.get(j);
                    playersList.set(j,playersList.get(i));
                    playersList.set(i,temp);
                    biggest=playersList.get(i).getProgress();
                }
            }
        }

        //printing results
        for(int i=0;i<playersList.size();i++){
            System.out.println(i+1+". place: player "+playersList.get(i).getId()+" result "+playersList.get(i).getProgress()+" points");
        }
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public void setWorkshops(Workshop[] workshops) {
        this.workshops = workshops;
    }

    public void setCenterOfWorkshop(CenterOfWorkshop centerOfWorkshop) {
        this.centerOfWorkshop = centerOfWorkshop;
    }

    public void setTilesAmounts(int[] tilesAmounts) {
        this.tilesAmounts = tilesAmounts;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setIs1stplayerstileatthecenter(boolean is1stplayerstileatthecenter) {
        this.is1stplayerstileatthecenter = is1stplayerstileatthecenter;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public Workshop[] getWorkshops() {
        return workshops;
    }

    public CenterOfWorkshop getCenterOfWorkshop() {
        return centerOfWorkshop;
    }
}

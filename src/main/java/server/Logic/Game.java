package server.Logic;

import client.logic.Player;
import server.ObjectsProcessing.ObjectsSerializer;
import server.Configuration.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Tile[][] board = new Tile[5][5];
    private ArrayList<Player> playersList = new ArrayList<>();
    private Workshop[] workshops;
    private CenterOfWorkshop centerOfWorkshop = new CenterOfWorkshop();
    private int[] tilesAmounts = {20, 20, 20, 20, 20}; //yellow blue green pink purple
    private int round=1;
    private boolean is1stplayerstileatthecenter=false;
    private boolean wasGameStatesReadFromaFile=false;
    private GameStatus gameStatus;
    public Game(int players) {
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
    }

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

    public void letsplay() throws IOException {
        if(!this.wasGameStatesReadFromaFile) {
            this.generateWorkshops();
        }
        boolean hasSomeBodyFinished = false;
        while (!hasSomeBodyFinished) {
                for (Player p : this.playersList) {
                    boolean areWorkshopsEmpty=false;
                    Scanner sc = new Scanner(System.in);
                    Scanner sc2 = new Scanner(System.in);
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
                    int ws = sc.nextInt();
                    System.out.println("kolor: ");
                    String c = sc2.nextLine();
                    if (ws == workshops.length+1) {
                        ArrayList<Integer> indexesToRemove=new ArrayList<>();
                        if(is1stplayerstileatthecenter){
                            for(int i=0;i< centerOfWorkshop.getCenterOfWorkshop().size();i++){
                                if(centerOfWorkshop.getCenterOfWorkshop().get(i).getColor().equals("1st player tile")){
                                    p.getRoundsTiles().add(centerOfWorkshop.getCenterOfWorkshop().get(i));
                                    centerOfWorkshop.getCenterOfWorkshop().remove(i);
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
                        for(int i=0;i<indexesToRemove.size();i++){
                            centerOfWorkshop.getCenterOfWorkshop().remove(indexesToRemove.get(i)-i);
                        }
                    } else {
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
                            }
                            workshops[ws - 1].getTiles()[i] = null;
                        }

                    }
                    System.out.println(p.getRoundsTiles());
                    p.putTilesToPatternLine(p.chooseAction());
                    p.moveTiles();
                    p.calculateRoundPoints();
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
                    if(areWorkshopsEmpty) {
                        this.generateWorkshops();
                    }
                    if(round==1){
                        centerOfWorkshop.getCenterOfWorkshop().add(new Tile(5));
                        this.is1stplayerstileatthecenter=true;
                    }
                    round++;
                }
                System.out.println("czy chcesz zapisac stan gry oraz zakonczyc rozgrywke? yes/no");
                Scanner sccc=new Scanner(System.in);
                String ans= sccc.nextLine();
                if(ans.equals("yes")){
                    System.out.println("Podaj nazwe pliku: ");
                    Scanner sccc2=new Scanner(System.in);
                    String ans2= sccc2.nextLine();
                    this.saveGameStatusToFile(ans2);
                    return;
                }
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
    public void saveGameStatusToFile(String fileName) throws IOException {
        ObjectsSerializer.serializeGameStatus(new GameStatus(this.playersList,this.workshops,this.centerOfWorkshop,this.tilesAmounts,this.round,this.is1stplayerstileatthecenter),fileName);
    }
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
}

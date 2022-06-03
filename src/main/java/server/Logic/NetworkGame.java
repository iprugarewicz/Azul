package server.Logic;

import client.logic.NetworkPlayer;
import client.logic.Player;
import server.Network.Move;
import server.Network.Serwer;
import server.Configuration.Config;
import server.ObjectsProcessing.ObjectsSerializer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class NetworkGame {

    private static final Tile[][] board = new Tile[5][5];
    private ArrayList<NetworkPlayer> playersList = new ArrayList<NetworkPlayer>();
    private Workshop[] workshops;
    private CenterOfWorkshop centerOfWorkshop = new CenterOfWorkshop();
    private int[] tilesAmounts = {20, 20, 20, 20, 20}; //yellow blue green pink purple
    private int round=1;
    private boolean is1stplayerstileatthecenter=false;
    private boolean wasGameStatesReadFromaFile=false;
    private final Serwer serwer;
    private ArrayList<ObjectOutputStream> objectOutputStreams;
    private ArrayList<ObjectInputStream> objectInputStreams;

    public NetworkGame(int players,Serwer s) {
        this.serwer = s;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Tile((j + 5 - i) % 5);
            }
        }

        if(players > 4 || players < 2){
            throw new IllegalArgumentException("Not allowed number of players, try 2-4 instead.");
        }

        for (NetworkPlayer p : s.getPlayersDatabase()) {
            playersList.add(p);
        }

        this.workshops = new Workshop[Config.getWorkshopsAmount(playersList.size())];
        for(int i=0;i<Config.getWorkshopsAmount(playersList.size());i++){
            this.workshops[i]=new Workshop();
        }

        objectOutputStreams = new ArrayList<ObjectOutputStream>();
        objectInputStreams = new ArrayList<ObjectInputStream>();
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

    public NetworkGameStatus generateGameStatus(int id,boolean isGameFinished){
        return new NetworkGameStatus(playersList,workshops,centerOfWorkshop,tilesAmounts,round,is1stplayerstileatthecenter,id,isGameFinished,getBoard());
    }

    public void letsplay() throws IOException, ClassNotFoundException {
        if(!this.wasGameStatesReadFromaFile) {
            this.generateWorkshops();
        }
        for (NetworkPlayer p:
             this.playersList) {
            ObjectInputStream ois = new ObjectInputStream(serwer.getInputStreams().get(p.getId()-1));
            objectInputStreams.add(ois);
        }
        boolean hasSomeBodyFinished = false;
        while (!hasSomeBodyFinished) {
            for (NetworkPlayer p : this.playersList) {
                boolean areWorkshopsEmpty=false;
                serwer.sendToAll(generateGameStatus(p.getId(),hasSomeBodyFinished));
                //Move mv = (Move) serwer.getObjectInputStreams().get(p.getId()).readObject();
                Move mv = (Move) objectInputStreams.get(p.getId()-1).readObject();
                System.out.println(mv);
                System.out.println(mv);
                while(!isMoveCorrect(mv,p)){mv = (Move) serwer.getObjectInputStreams().get(p.getId()-1).readObject();}
                int ws = mv.getWorkshop();
                String c = mv.getColor();
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
                p.putTilesToPatternLine(mv.getMove());
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
                //this.saveNetworkGameStatusToFile("autosave", p.getId());
            }
            //koniec rundy?

            for(NetworkPlayer p : playersList){
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
  // public void saveNetworkGameStatusToFile(String fileName, int id) throws IOException {
  //     ObjectsSerializer.serializeNetworkGameStatus(new NetworkGameStatus(this.playersList,this.workshops,this.centerOfWorkshop,this.tilesAmounts,this.round,this.is1stplayerstileatthecenter,id,false),fileName);
  // }
    public void endGame(){
        for(int i=0;i<playersList.size();i++){
            int biggest=playersList.get(i).getProgress();
            for(int j=i+1;j<playersList.size();j++){
                if(playersList.get(j).getProgress()>biggest){
                    NetworkPlayer temp=playersList.get(j);
                    playersList.set(j,playersList.get(i));
                    playersList.set(i,temp);
                    biggest=playersList.get(i).getProgress();
                }
            }
        }
        this.serwer.sendToAll(playersList);
        //printing results
        for(int i=0;i<playersList.size();i++){
            System.out.println(i+1+". place: player "+playersList.get(i).getId()+" result "+playersList.get(i).getProgress()+" points");
        }
    }

    private static boolean isMoveCorrect(Move m,NetworkPlayer p){
        if(m==null) return false;
        for (int i: p.possibleActions()){
            if(i==m.getMove()){return true;}
        }
        return false;
    }




    public ArrayList<NetworkPlayer> getPlayersList() {
        return playersList;
    }

    public static Tile[][] getBoard() {
        return board;
    }

    public void setWasGameStatesReadFromaFile(boolean wasGameStatesReadFromaFile) {
        this.wasGameStatesReadFromaFile = wasGameStatesReadFromaFile;
    }

    public void setPlayersList(ArrayList<NetworkPlayer> playersList) {
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


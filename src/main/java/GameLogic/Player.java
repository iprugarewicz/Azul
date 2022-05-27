package GameLogic;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player implements Serializable {
    private int progress=0;
    private final int id;
    private ArrayList<Tile> roundsTiles=new ArrayList<>();
    private PlayersBoard matchedTiles=new PlayersBoard();
    private PatternLine patternLine=new PatternLine();
    private Floor floor=new Floor();
    private String chosenColor;
    public Player(int id){


        this.id=id;

    }

    public ArrayList<Tile> getRoundsTiles() {
        return roundsTiles;
    }

    public boolean[][] getMatchedTiles() {
        return matchedTiles.getMatchedTiles();
    }

    public int getId() {
        return id;
    }

    public void calculateRoundPoints(){
        //horizontal check
        for(boolean[] tab : this.matchedTiles.getMatchedTiles()){
            int tempPoints=0;
            for(boolean tile : tab){
                if(tile){
                    tempPoints++;
                }
                else{
                    this.progress+=tempPoints;
                    tempPoints=0;
                }
                if(tempPoints==5){
                    this.progress+=2;
                }
            }
        }
        //vertical check
        for(int i=0;i<5;i++){
            int tempPoints=0;
            for(int j=0;j<5;j++){
                if(this.matchedTiles.getMatchedTiles()[j][i]){
                    tempPoints++;
                }
                else{
                    if(tempPoints>1) {
                        this.progress += tempPoints;
                    }
                    tempPoints=0;
                }
                if(tempPoints==5){
                    this.progress+=7;
                }
            }
        }

        //colors check
        for(int i=0;i<5;i++){
            if(this.matchedTiles.getMatchedTiles()[0][i] && this.matchedTiles.getMatchedTiles()[1][(i+1)%5] && this.matchedTiles.getMatchedTiles()[2][(i+2)%5] && this.matchedTiles.getMatchedTiles()[3][(i+3)%5] && this.matchedTiles.getMatchedTiles()[4][(i+4)%5]){
                this.progress+=10;
            }
        }

        //floor check
        int x=this.floor.getFloor().size();
        if(x==1){this.progress--;}
        else if(x==2){this.progress-=2;}
        else if(x==3){this.progress-=4;}
        else if(x==4){this.progress-=6;}
        else if(x==5){this.progress-=8;}
        else if(x==6){this.progress-=11;}
        else if(x==7){this.progress-=14;}
        this.floor=new Floor();
        if(progress<0){
            this.progress=0;
        }


    }

    private boolean isAlreadyPut(int row){
            for(int i=0;i<5;i++) {
                if (Game.getBoard()[row][i].getColor().equals(this.roundsTiles.get(0).getColor()) && this.matchedTiles.getMatchedTiles()[row][i]) {
                    return true;
                }
            }
            return false;
        }

    private boolean isThatPatternLineFilled(int row){
        if(this.patternLine.getPatternLine().get(row)[row]!=null){
            if(this.patternLine.getPatternLine().get(row)[row].getColor().equals(this.roundsTiles.get(0).getColor()) && this.patternLine.getPatternLine().get(row)[0]==null){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public ArrayList<Integer> possibleActions(){
        ArrayList<Integer> possibleActions=new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(this.isAlreadyPut(i) || this.isThatPatternLineFilled(i)){
                    break;
                }
                if(j==4){
                    possibleActions.add(i);
                }
            }
        }
        return possibleActions;
    }
    public int chooseAction(){
        boolean doIHave1stplayertile=false;
        for (Tile roundsTile : this.roundsTiles) {
            if (roundsTile.getColor().equals("1st player tile")) {
                doIHave1stplayertile = true;
                break;
            }
        }
        if(!doIHave1stplayertile) {
            System.out.println("Masz " + this.roundsTiles.size() + " kafelki koloru " + this.roundsTiles.get(0));
        }else{
            System.out.println("Masz " + (this.roundsTiles.size()-1) + " kafelki koloru " + this.roundsTiles.get(1)+" oraz kafelek: "+this.roundsTiles.get(0));
        }
        System.out.println("twoja tablica zapelnienia");
        for(boolean[] tab : this.matchedTiles.getMatchedTiles()){
            for(boolean b : tab){
                System.out.print(b+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("pelna tablica");
        for(Tile[] tab : Game.getBoard()){
            for (Tile tile : tab){
                System.out.print(tile+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("twoje linie wzorow: ");
        for(Tile[] tiles : this.patternLine.getPatternLine()){
            for(Tile t : tiles){
                System.out.print(t+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Masz: "+progress+" punktow");
        System.out.println();
        System.out.println("Mozesz wybrac akcje sposrod: ");
        ArrayList<Integer> actions=this.possibleActions();
        if(actions.size()!=0) {
            for (int i : actions) {
                System.out.println(i + 1);
            }
            System.out.println("akcja: ");
            Scanner sc = new Scanner(System.in);
            return sc.nextInt() - 1;
        }
        else{
            System.out.println("niestety nic nie mozesz zrobic, wszystkie twoje kafelki z tej rundy laduja na podlodze");
            return 5;
        }
    }
    public void moveTiles(){
        for(int i=0;i<5;i++){
            int temp=0;
            for(Tile t : this.patternLine.getPatternLine().get(i)){
                if(t!=null){
                    temp++;
                }
            }
            if(temp==i+1){
                for(int j=0;j<5;j++){
                    if(Game.getBoard()[i][j].getColor().equals(this.chosenColor)){
                        this.matchedTiles.getMatchedTiles()[i][j]=true;
                        break;
                    }
                }
                Arrays.fill(this.patternLine.getPatternLine().get(i), null);
            }
        }
    }
    public void putTilesToPatternLine(int action){
        if(action<5) {
            for (int i = 0; i < this.roundsTiles.size(); i++) {
                if (this.roundsTiles.get(i).getColor().equals("1st player tile")) {
                    this.floor.getFloor().add(this.roundsTiles.get(i));
                    this.roundsTiles.remove(i);
                    break;
                }
            }
            this.chosenColor = this.roundsTiles.get(0).getColor();
            for (int i = action; i >= 0; i--) {
                if (this.patternLine.getPatternLine().get(action)[i] == null) {
                    if (this.roundsTiles.size() != 0) {
                        this.patternLine.getPatternLine().get(action)[i] = this.roundsTiles.get(0);
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
        return patternLine;
    }
}

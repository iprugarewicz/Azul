package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Tile[][] board = new Tile[5][5];
    private ArrayList<Player> playersList = new ArrayList<>();
    private Tile[][] workshops;
    private ArrayList<Tile> centerOfWorkshop = new ArrayList<>();
    private int[] tilesAmounts = {20, 20, 20, 20, 20}; //yellow blue green pink purple
    private int round = 1;
    private boolean is1stplayerstileatthecenter = false;

    public Game(int players) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Tile((j + 5 - i) % 5, i, j);
            }
        }
        for (int i = 0; i < players; i++) {
            playersList.add(new Player(i + 1));
        }
        if (playersList.size() == 2) {
            this.workshops = new Tile[5][4];
        } else if (playersList.size() == 3) {
            this.workshops = new Tile[7][4];
        } else if (playersList.size() == 4) {
            this.workshops = new Tile[9][4];
        }
    }

    private void generateWorkshops() {
        Random r = new Random();
        for (int i = 0; i < workshops.length; i++) {
            for (int j = 0; j < 4; j++) {
                int ran = Math.abs(r.nextInt()) % 5;
                if (this.tilesAmounts[ran] != 0) {
                    workshops[i][j] = new Tile(ran, 0, 0);
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

    public void letsplay() {
        this.generateWorkshops();
        boolean hasSomeBodyFinished = false;
        while (!hasSomeBodyFinished) {
            for (Player p : this.playersList) {
                boolean areWorkshopsEmpty = false;
                Scanner sc = new Scanner(System.in);
                Scanner sc2 = new Scanner(System.in);
                for (int i = 0; i < workshops.length; i++) {
                    System.out.println(i + 1 + ". workshop: ");
                    for (int j = 0; j < 4; j++) {
                        if (workshops[i][j] != null) {
                            System.out.println("   " + workshops[i][j]);
                        }
                    }
                }
                if (this.centerOfWorkshop.size() != 0) {
                    System.out.print(workshops.length + 1 + ". centrum planszy: ");
                    System.out.println();
                    for (Tile t : this.centerOfWorkshop) {
                        System.out.println("   " + t);
                    }
                }
                System.out.println();
                System.out.println("teraz jest  tura gracza: " + p.getId());
                System.out.println("Wybierz warsztat oraz podaj, ktore kolory chcesz zabrac:");
                System.out.println("warsztat: ");
                int ws = sc.nextInt();
                System.out.println("kolor: ");
                String c = sc2.nextLine();
                if (ws == workshops.length + 1) {
                    ArrayList<Integer> indexesToRemove = new ArrayList<>();
                    if (is1stplayerstileatthecenter) {
                        for (int i = 0; i < centerOfWorkshop.size(); i++) {
                            if (centerOfWorkshop.get(i).getColor().equals("1st player tile")) {
                                p.getRoundsTiles().add(centerOfWorkshop.get(i));
                                centerOfWorkshop.remove(i);
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < centerOfWorkshop.size(); i++) {
                        if (this.centerOfWorkshop.get(i).getColor().equals(c)) {
                            switch (c) {
                                case "yellow" -> p.getRoundsTiles().add(new Tile(0, 0, 0));
                                case "blue" -> p.getRoundsTiles().add(new Tile(1, 0, 0));
                                case "green" -> p.getRoundsTiles().add(new Tile(2, 0, 0));
                                case "pink" -> p.getRoundsTiles().add(new Tile(3, 0, 0));
                                case "purple" -> p.getRoundsTiles().add(new Tile(4, 0, 0));
                            }
                            indexesToRemove.add(i);
                        }
                    }
                    for (int i = 0; i < indexesToRemove.size(); i++) {
                        centerOfWorkshop.remove(indexesToRemove.get(i) - i);
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        if (workshops[ws - 1][i].getColor().equals(c)) {
                            switch (c) {
                                case "yellow" -> p.getRoundsTiles().add(new Tile(0, 0, 0));
                                case "blue" -> p.getRoundsTiles().add(new Tile(1, 0, 0));
                                case "green" -> p.getRoundsTiles().add(new Tile(2, 0, 0));
                                case "pink" -> p.getRoundsTiles().add(new Tile(3, 0, 0));
                                case "purple" -> p.getRoundsTiles().add(new Tile(4, 0, 0));
                            }

                        } else {
                            this.centerOfWorkshop.add(new Tile(workshops[ws - 1][i].getColorNumber(), 0, 0));
                        }
                        workshops[ws - 1][i] = null;
                    }

                }
                System.out.println(p.getRoundsTiles());
                p.putTilesToPatternLine(p.chooseAction());
                p.moveTiles();
                p.calculateRoundPoints();
                for (int i = 0; i < workshops.length; i++) {
                    if (centerOfWorkshop.size() != 0) {
                        break;
                    }
                    boolean haveWefoundNonEmptyIndex = false;
                    for (Tile t : workshops[i]) {
                        if (t != null) {
                            haveWefoundNonEmptyIndex = true;
                            break;
                        }
                    }
                    if (haveWefoundNonEmptyIndex) {
                        break;
                    }
                    if (i + 1 == workshops.length) {
                        areWorkshopsEmpty = true;
                        break;
                    }


                }
                if (areWorkshopsEmpty) {
                    this.generateWorkshops();
                }
                if (round == 1) {
                    centerOfWorkshop.add(new Tile(5, 0, 0));
                    this.is1stplayerstileatthecenter = true;
                }
                round++;
            }

            for (Player p : playersList) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
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

    public void endGame() {
        for (int i = 0; i < playersList.size(); i++) {
            int biggest = playersList.get(i).getProgress();
            for (int j = i + 1; j < playersList.size(); j++) {
                if (playersList.get(j).getProgress() > biggest) {
                    Player temp = playersList.get(j);
                    playersList.set(j, playersList.get(i));
                    playersList.set(i, temp);
                    biggest = playersList.get(i).getProgress();
                }
            }
        }

        //printing results
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println(i + 1 + ". place: player " + playersList.get(i).getId() + " result " + playersList.get(i).getProgress() + " points");
        }
    }

}

package server.Configuration;

import server.Logic.Tile;

public class Config {

    private static int[] workshopsAmount = {0, 0, 5, 7, 9};
    private static int[] floorPoints = {0,1,2,4,6,8,11,14};

    public static Tile[][] getBoard(){
        Tile[][] board = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Tile((j + 5 - i) % 5);
            }
        }
        return board;
    }

    public static int getWorkshopsAmount(int x) {
        return workshopsAmount[x];
    }

    public static int getFloorPoints(int x) {
        return floorPoints[x];
    }
}

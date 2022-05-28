package server.Configuration;

public class Config {

    private static int[] workshopsAmount = {0, 0, 5, 7, 9};
    private static int[] floorPoints = {0,1,2,4,6,8,11,14};

    public static int getWorkshopsAmount(int x) {
        return workshopsAmount[x];
    }

    public static int getFloorPoints(int x) {
        return floorPoints[x];
    }
}

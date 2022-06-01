package server.ObjectsProcessing;

import server.Logic.Game;
import server.Logic.GameStatus;

public class ReadGameStatusFromFile {
    public static Game readGameStatus(GameStatus gs){
        Game game=new Game(gs.getPlayersList().size());
        game.setCenterOfWorkshop(gs.getCenterOfWorkshop());
        //game.setPlayersList(gs.getPlayersList());
        game.setRound(gs.getRound());
        game.setIs1stplayerstileatthecenter(gs.is1stplayerstileatthecenter());
        game.setWorkshops(gs.getWorkshops());
        game.setTilesAmounts(gs.getTilesAmounts());
        game.setWasGameStatesReadFromaFile(true);
        return game;
    }


}

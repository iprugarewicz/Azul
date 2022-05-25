package ObjectsProcessing;

import GameLogic.Game;
import GameLogic.GameStatus;

public class ReadGameStatusFromFile {
    public static Game readGameStatus(GameStatus gs){
        Game game=new Game(gs.getPlayersList().size());
        game.setCenterOfWorkshop(gs.getCenterOfWorkshop());
        game.setPlayersList(gs.getPlayersList());
        game.setRound(gs.getRound());
        game.setIs1stplayerstileatthecenter(gs.isIs1stplayerstileatthecenter());
        game.setWorkshops(gs.getWorkshops());
        game.setTilesAmounts(gs.getTilesAmounts());
        game.setWasGameStatesReadFromaFile(true);
        return game;
    }
}

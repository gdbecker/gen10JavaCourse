package gdb.guessthenumber.service;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import java.util.List;

/**
 * M2 Summative
 * @author garrettbecker
 */

public interface GameService {
    //Corresponding with "GameDao" methods
    //Pass through methods
    List<Game> getAllGames();
    Game getGameByID(int id, boolean forDisplay);
    Game addGame(Game game);
    boolean updateGame(Game game);
    boolean deleteGameByID(int id);
    boolean deleteAllGames();
    
    //Corresponding with "RoundDao" methods
    //Pass through methods
    List<Round> getAllRounds();
    List<Round> getRoundsByGameID(int id);
    Round getRoundByRoundID(int id);
    Round addRound(Round round);
    boolean updateRound(Round round);
    boolean deleteRoundByID(int id);
    boolean deleteAllRounds();
    
    //Unique to "GameService"
    Game generateNewGame();
    Round generateNewRound(int GameID, String guessValue);
}
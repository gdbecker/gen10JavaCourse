package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Game;
import java.util.List;

/**
 * M2 Summative
 * @date Monday January 20, 2020
 * @author garrettbecker
 */
public interface GameDao {
    List<Game> getAllGames();
    Game getGameByID(int id, boolean forDisplay);
    Game addGame(Game game);
    boolean updateGame(Game game);
    boolean deleteGameByID(int id);
    boolean deleteAllGames();
}

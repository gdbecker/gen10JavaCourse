package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Round;
import java.util.List;

/**
 * M2 Summative
 * @author garrettbecker
 */

public interface RoundDao {
    List<Round> getAllRounds();
    List<Round> getRoundsByGameID(int id);
    Round getRoundByRoundID(int id);
    Round addRound(Round round);
    boolean updateRound(Round round);
    boolean deleteRoundByID(int id);
}

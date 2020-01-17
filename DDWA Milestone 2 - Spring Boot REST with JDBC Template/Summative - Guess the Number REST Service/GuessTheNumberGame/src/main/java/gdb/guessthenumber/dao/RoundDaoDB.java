package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * M2 Summative
 * @author garrettbecker
 */

@Repository
public class RoundDaoDB implements RoundDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT GameID, Answer, `Status` FROM Game;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    @Override
    public List<Round> getRoundsByGameID(int id) {
        final String sql = "SELECT Round.RoundID, Round.GameID, Round.GuessValue, Round.GuessTime, Round.GuessResult "
                + "FROM Round "
                + "LEFT OUTER JOIN Game ON Round.GameID = Game.GameID "
                + "WHERE Game.GameID = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), id);
    }

    @Override
    public Round getRoundByRoundID(int id) {
        final String sql = "SELECT RoundID, GameID, GuessValue, GuessTime, GuessResult FROM Round WHERE RoundID = ?;";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), id);
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO Round(GameID, GuessValue, GuessTime, GuessResult) VALUES(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, Integer.toString(round.getGameID()));
            statement.setString(2, round.getGuessValue());
            statement.setString(3, round.getGuessTime());
            statement.setString(4, round.getGuessResult());
            return statement;
        }, keyHolder);
        
        round.setRoundID(keyHolder.getKey().intValue());
        
        return round;
    }

    @Override
    public boolean updateRound(Round round) {
        final String sql = "UPDATE Round SET RoundID = ?, GameID = ?, GuessValue = ?, GuessTime = ?, GuessResult = ? WHERE RoundID = ?;";
        
        return jdbcTemplate.update(sql,
                round.getRoundID(),
                round.getGameID(),
                round.getGuessValue(),
                round.getGuessTime(),
                round.getGuessResult()) > 0;
    }

    @Override
    public boolean deleteRoundByID(int id) {
        final String sql = "DELETE FROM Round WHERE RoundID = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    private static final class RoundMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round r = new Round();
            r.setRoundID(rs.getInt("RoundID"));
            r.setGameID(rs.getInt("GameID"));
            r.setGuessValue(rs.getString("GuessValue"));
            r.setGuessTime(rs.getString("GuessTime"));
            r.setGuessResult(rs.getString("GuessResult"));
            return r;
        }
    }
    
}

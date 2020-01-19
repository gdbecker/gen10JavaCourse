package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * M2 Summative
 * @author garrettbecker
 */

@Repository
public class GameDaoDB implements GameDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    @Autowired
    public GameDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    */
    
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT GameID, Answer, `Status` FROM Game;";
        List<Game> gameList = jdbcTemplate.query(sql, new GameMapper());
        
        //Hide the Answer value of the game if "In Progress"
        //Won't actually change the data in DB
        for (Game g : gameList) {
            if (g.getStatus().equals("In Progress")) {
                g.setAnswer("Hidden until guessed!");
            }
        }
        
        return gameList;
    }

    @Override
    public Game getGameByID(int id, boolean forDisplay) {
        Game g = new Game();
        
        try {
            final String sql = "SELECT GameID, Answer, `Status` FROM Game WHERE GameID = ?;";
            g = jdbcTemplate.queryForObject(sql, new GameMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
        
        //Hide the Answer value of the game if "In Progress"
        //Won't actually change the data in DB
        //ONLY if displaying this information as opposed to using it
        if (forDisplay) {
            if (g.getStatus().equals("In Progress")) {
                g.setAnswer("Hidden until guessed!");
            }
        } 
        
        return g;
    }

    @Override
    public Game addGame(Game game) {
        final String sql = "INSERT INTO Game(Answer, `Status`) VALUES(?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;
        }, keyHolder);
        
        game.setGameID(keyHolder.getKey().intValue());
        
        return game;
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE Game SET GameID = ?, Answer = ?, `Status` = ? WHERE GameID = ?;";
        
        return jdbcTemplate.update(sql,
                game.getGameID(), game.getAnswer(), game.getStatus(), game.getGameID()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteGameByID(int id) {
        final String sqlDelete1 = "DELETE FROM Round WHERE GameID = ?;";
        jdbcTemplate.update(sqlDelete1, id);
        
        final String sqlDelete2 = "DELETE FROM Game WHERE GameID = ?;";
        return jdbcTemplate.update(sqlDelete2, id) > 0;
    }
    
    @Override
    public boolean deleteAllGames() {
        final String sqlDelete = "DELETE FROM Game;";
        return jdbcTemplate.update(sqlDelete) > 0;
    }
    
    private static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game g = new Game();
            g.setGameID(rs.getInt("GameID"));
            g.setAnswer(rs.getString("Answer"));
            g.setStatus(rs.getString("Status"));
            return g;
        }
    }
}

package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * M2 Summative
 * @date Monday January 20, 2020
 * @author garrettbecker
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundDaoDBTest {
    
    @Autowired
    RoundDao rDao;
    
    @Autowired
    GameDao gDao;
    
    @BeforeEach
    public void setUp() {
        rDao.deleteAllRounds();
        gDao.deleteAllGames();
    }

    /**
     * Test of getAllRounds method, of class RoundDaoDB.
     */
    @Test
    public void testGetAllRounds() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        Round r2 = new Round();
        r2.setGameID(g1.getGameID());
        r2.setGuessValue("2345");
        r2.setGuessTime("Time");
        r2.setGuessResult("Result");
        r2 = rDao.addRound(r2);
        
        List<Round> rounds = rDao.getAllRounds();
        assertEquals(2, rounds.size());
    }

    /**
     * Test of getRoundsByGameID method, of class RoundDaoDB.
     */
    @Test
    public void testGetRoundsByGameID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Game g2 = new Game();
        g2.setAnswer("0987");
        g2.setStatus("Finished");
        g2 = gDao.addGame(g2);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        Round r2 = new Round();
        r2.setGameID(g2.getGameID());
        r2.setGuessValue("2345");
        r2.setGuessTime("Time");
        r2.setGuessResult("Result");
        r2 = rDao.addRound(r2);
        
        Round r3 = new Round();
        r3.setGameID(g2.getGameID());
        r3.setGuessValue("4567");
        r3.setGuessTime("Time");
        r3.setGuessResult("Result");
        r3 = rDao.addRound(r3);
        
        List<Round> game1 = rDao.getRoundsByGameID(g1.getGameID());
        List<Round> game2 = rDao.getRoundsByGameID(g2.getGameID());
        
        assertEquals(1, game1.size());
        assertEquals(2, game2.size());
    }

    /**
     * Test of getRoundByRoundID method, of class RoundDaoDB.
     */
    @Test
    public void testGetRoundByRoundID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        Round fromDao = rDao.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
    }

    /**
     * Test of addRound method, of class RoundDaoDB.
     */
    @Test
    public void testAddRound() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        Round fromDao = rDao.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
    }

    /**
     * Test of updateRound method, of class RoundDaoDB.
     */
    @Test
    public void testUpdateRound() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        Round fromDao = rDao.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
        
        r1.setGuessResult("New Result");
        rDao.updateRound(r1);
        
        assertNotEquals(r1, fromDao);
        
        fromDao = rDao.getRoundByRoundID(r1.getRoundID());
        
        assertEquals(r1, fromDao);
    }

    /**
     * Test of deleteRoundByID method, of class RoundDaoDB.
     */
    @Test
    public void testDeleteRoundByID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = rDao.addRound(r1);
        
        rDao.deleteRoundByID(r1.getRoundID());
        
        Round fromDao = rDao.getRoundByRoundID(r1.getRoundID());
        assertNull(fromDao);
    }
}

package gdb.guessthenumber.dao;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * M2 Summative
 * @author garrettbecker
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDaoDBTest {
    
    @Autowired
    GameDao gDao;
    
    @Autowired
    RoundDao rDao;
    
    @Before
    public void setUp() {
        List<Round> rounds = rDao.getAllRounds();
        for (Round r : rounds) {
            rDao.deleteRoundByID(r.getRoundID());
        }
        
        gDao.deleteAllGames();
    }

    /**
     * Test of getAllGames method, of class GameDaoDB.
     */
    @Test
    public void testGetAllGames() {
        //Add two Games to testing DB
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Game g2 = new Game();
        g2.setAnswer("4567");
        g2.setStatus("In Progress");
        g2 = gDao.addGame(g2);
        
        //Make sure it got both
        List<Game> games = gDao.getAllGames();
        
        assertEquals(2, games.size());
        
        gDao.deleteAllGames();
    }

    /**
     * Test of getGameByID method, of class GameDaoDB.
     */
    @Test
    public void testGetGameByID() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Game fromDao = gDao.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
        
        gDao.deleteAllGames();
    }

    /**
     * Test of addGame method, of class GameDaoDB.
     */
    @Test
    public void testAddGame() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Game fromDao = gDao.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
        
        gDao.deleteAllGames();
    }

    /**
     * Test of updateGame method, of class GameDaoDB.
     */
    @Test
    public void testUpdateGame() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        Game fromDao = gDao.getGameByID(g1.getGameID(), true);
        assertEquals(g1, fromDao);
        
        g1.setAnswer("5243");
        gDao.updateGame(g1);
        
        assertNotEquals(g1, fromDao);
        
        fromDao = gDao.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
        
        gDao.deleteAllGames();
    }

    /**
     * Test of deleteGameByID method, of class GameDaoDB.
     */
    @Test
    public void testDeleteGameByID() {
        Game g1 = new Game();
        g1.setAnswer("9076");
        g1.setStatus("Finished");
        g1 = gDao.addGame(g1);
        
        gDao.deleteGameByID(g1.getGameID());
        Game fromDao = gDao.getGameByID(g1.getGameID(), true);

        assertNull(fromDao);
        
        gDao.deleteAllGames();
    }
    
}

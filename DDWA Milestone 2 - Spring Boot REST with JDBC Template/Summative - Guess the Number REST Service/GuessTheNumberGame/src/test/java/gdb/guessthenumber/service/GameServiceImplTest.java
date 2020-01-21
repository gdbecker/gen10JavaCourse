package gdb.guessthenumber.service;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
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
public class GameServiceImplTest {
    
    @Autowired
    GameService service;
    
    @BeforeEach
    public void setUp() {
        service.deleteAllRounds();
        service.deleteAllGames();
    }

    /**
     * Test of getAllGames method, of class GameServiceImpl.
     */
    @Test
    public void testGetAllGames() {
        //Add two Games to testing DB
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Game g2 = new Game();
        g2.setAnswer("4567");
        g2.setStatus("In Progress");
        g2 = service.addGame(g2);
        
        //Make sure it got both
        List<Game> games = service.getAllGames();
        
        assertEquals(2, games.size());
    }

    /**
     * Test of getGameByID method, of class GameServiceImpl.
     */
    @Test
    public void testGetGameByID() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Game fromDao = service.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
    }

    /**
     * Test of addGame method, of class GameServiceImpl.
     */
    @Test
    public void testAddGame() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Game fromDao = service.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
    }

    /**
     * Test of updateGame method, of class GameServiceImpl.
     */
    @Test
    public void testUpdateGame() {
        Game g1 = new Game();
        g1.setAnswer("1234");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Game fromDao = service.getGameByID(g1.getGameID(), true);
        assertEquals(g1, fromDao);
        
        g1.setAnswer("5243");
        service.updateGame(g1);
        
        assertNotEquals(g1, fromDao);
        
        fromDao = service.getGameByID(g1.getGameID(), true);
        
        assertEquals(g1, fromDao);
    }

    /**
     * Test of deleteGameByID method, of class GameServiceImpl.
     */
    @Test
    public void testDeleteGameByID() {
        Game g1 = new Game();
        g1.setAnswer("9076");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        service.deleteGameByID(g1.getGameID());
        Game fromDao = service.getGameByID(g1.getGameID(), true);

        assertNull(fromDao);
    }

    /**
     * Test of getAllRounds method, of class GameServiceImpl.
     */
    @Test
    public void testGetAllRounds() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        Round r2 = new Round();
        r2.setGameID(g1.getGameID());
        r2.setGuessValue("2345");
        r2.setGuessTime("Time");
        r2.setGuessResult("Result");
        r2 = service.addRound(r2);
        
        List<Round> rounds = service.getAllRounds();
        assertEquals(2, rounds.size());
    }

    /**
     * Test of getRoundsByGameID method, of class GameServiceImpl.
     */
    @Test
    public void testGetRoundsByGameID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Game g2 = new Game();
        g2.setAnswer("0987");
        g2.setStatus("Finished");
        g2 = service.addGame(g2);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        Round r2 = new Round();
        r2.setGameID(g2.getGameID());
        r2.setGuessValue("2345");
        r2.setGuessTime("Time");
        r2.setGuessResult("Result");
        r2 = service.addRound(r2);
        
        Round r3 = new Round();
        r3.setGameID(g2.getGameID());
        r3.setGuessValue("4567");
        r3.setGuessTime("Time");
        r3.setGuessResult("Result");
        r3 = service.addRound(r3);
        
        List<Round> game1 = service.getRoundsByGameID(g1.getGameID());
        List<Round> game2 = service.getRoundsByGameID(g2.getGameID());
        
        assertEquals(1, game1.size());
        assertEquals(2, game2.size());
    }

    /**
     * Test of getRoundByRoundID method, of class GameServiceImpl.
     */
    @Test
    public void testGetRoundByRoundID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        Round fromDao = service.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
    }

    /**
     * Test of addRound method, of class GameServiceImpl.
     */
    @Test
    public void testAddRound() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        Round fromDao = service.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
    }

    /**
     * Test of updateRound method, of class GameServiceImpl.
     */
    @Test
    public void testUpdateRound() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        Round fromDao = service.getRoundByRoundID(r1.getRoundID());
        assertEquals(r1, fromDao);
        
        r1.setGuessResult("New Result");
        service.updateRound(r1);
        
        assertNotEquals(r1, fromDao);
        
        fromDao = service.getRoundByRoundID(r1.getRoundID());
        
        assertEquals(r1, fromDao);
    }

    /**
     * Test of deleteRoundByID method, of class GameServiceImpl.
     */
    @Test
    public void testDeleteRoundByID() {
        Game g1 = new Game();
        g1.setAnswer("6789");
        g1.setStatus("Finished");
        g1 = service.addGame(g1);
        
        Round r1 = new Round();
        r1.setGameID(g1.getGameID());
        r1.setGuessValue("1234");
        r1.setGuessTime("Time");
        r1.setGuessResult("Result");
        r1 = service.addRound(r1);
        
        service.deleteRoundByID(r1.getRoundID());
        
        Round fromDao = service.getRoundByRoundID(r1.getRoundID());
        assertNull(fromDao);
    }

    /**
     * Test of generateNewGame method, of class GameServiceImpl.
     */
    @Test
    public void testGenerateNewGame() {   
        Game newGame = service.generateNewGame();
        
        boolean validAnswer = false;
        String answer = newGame.getAnswer();
        char[] answerArray = answer.toCharArray();
        
        if (answerArray[0] != answerArray[1]
                && answerArray[0] != answerArray[2]
                && answerArray[0] != answerArray[3]
                && answerArray[1] != answerArray[2]
                && answerArray[1] != answerArray[3]
                && answerArray[2] != answerArray[3]) {
            validAnswer = true;
        }
        
        boolean validStatus = false;
        String status = newGame.getStatus();
        
        if (status.equals("In Progress")) {
            validStatus = true;
        }
        
        //Check that game answer and status are valid
        //That answer has 4 different digits and
        //that status is "In Progress" like it should be
        assertTrue(validAnswer);
        assertTrue(validStatus);
    }

    /**
     * Test of generateNewRound method, of class GameServiceImpl.
     */
    @Test
    public void testGenerateNewRound() {
        Game g = new Game();
        g.setAnswer("1234");
        g.setStatus("In Progress");
        g = service.addGame(g);
        
        Round r = service.generateNewRound(g.getGameID(), "1243");
        
        //Check that the GameID is the same for both g and newRound
        assertEquals(g.getGameID(), r.getGameID());
        
        //Check that the guessValue is what was inputted above
        assertEquals("1243", r.getGuessValue());
        
        //Check that the guessResult is what it should be "e:2:p:2"
        assertEquals("e:2:p:2", r.getGuessResult());
    }
    
}

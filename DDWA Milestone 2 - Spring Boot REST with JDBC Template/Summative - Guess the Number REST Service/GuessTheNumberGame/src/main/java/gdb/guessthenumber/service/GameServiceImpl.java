package gdb.guessthenumber.service;

import gdb.guessthenumber.dao.GameDaoDB;
import gdb.guessthenumber.dao.RoundDaoDB;
import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * M2 Summative
 * @author garrettbecker
 */

@Repository
public class GameServiceImpl implements GameService {

    @Autowired
    GameDaoDB gameDao;
    
    @Autowired
    RoundDaoDB roundDao;

    /*
    public GameServiceImpl(GameDaoDB gameDao, RoundDaoDB roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    */
   
    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGameByID(int id, boolean forDisplay) {
        return gameDao.getGameByID(id, forDisplay);
    }

    @Override
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    @Override
    public boolean updateGame(Game game) {
        return gameDao.updateGame(game);
    }

    @Override
    public boolean deleteGameByID(int id) {
        return gameDao.deleteGameByID(id);
    }
    
    @Override
    public boolean deleteAllGames() {
        return gameDao.deleteAllGames();
    }

    @Override
    public List<Round> getAllRounds() {
        return roundDao.getAllRounds();
    }

    @Override
    public List<Round> getRoundsByGameID(int id) {
        return roundDao.getRoundsByGameID(id);
    }

    @Override
    public Round getRoundByRoundID(int id) {
        return roundDao.getRoundByRoundID(id);
    }

    @Override
    public Round addRound(Round round) {
        return roundDao.addRound(round);
    }

    @Override
    public boolean updateRound(Round round) {
        return roundDao.updateRound(round);
    }

    @Override
    public boolean deleteRoundByID(int id) {
        return roundDao.deleteRoundByID(id);
    }
    
    @Override
    public boolean deleteAllRounds() {
        return roundDao.deleteAllRounds();
    }

    @Override
    public Game generateNewGame() {
        //Initialize the start of a new Game
        Game g = new Game();
        
        //Randomly generate a four-digit Answer to the Game
        Random r = new Random();
        int a = r.nextInt(10);
        int b = 0;
        int c = 0;
        int d = 0;
        
        do {
            b = r.nextInt(10);
        } while (b == a); //Ensure that b is not equal to a
        
        do {
            c = r.nextInt(10);
        } while (c == a || c == b); //Ensure that c is not equal to a or b
        
        do {
            d = r.nextInt(10);
        } while (d == a || d == b || d == c); //Ensure that d is not equal to a, b, or c
        
        String aString = Integer.toString(a);
        String bString = Integer.toString(b);
        String cString = Integer.toString(c);
        String dString = Integer.toString(d);
        String answer = aString + bString + cString + dString;
        
        //Assign initial values to the new Game
        g.setAnswer(answer);
        g.setStatus("In Progress");
        
        //Return the new Game (no GameID yet, the addGame method will do that)
        return g;
        
    }

    @Override
    public Round generateNewRound(int GameID, String guessValue) {
        //Find current Game from DB
        Game currentGame = gameDao.getGameByID(GameID, false);
        
        //Get Answer from currentGame
        String gameAnswer = currentGame.getAnswer();
        
        //Check for exact matches 
        char gameAnswerArray[] = gameAnswer.toCharArray();
        char guessValueArray[] = guessValue.toCharArray();
        int exactMatch = 0;
        int index = 0;
        
        for (char c : gameAnswerArray) {
            if (c == guessValueArray[index]) {
                exactMatch++;
            }
            index++;
        }
        
        //Check for partial matches
        int partialMatch = 0;
        int answerIndex = 0;
        int guessIndex = 0;
        for (char c : gameAnswerArray) {
            for (char x : guessValueArray) {
                if (c == x && guessIndex != answerIndex) {
                    partialMatch++;
                }
                guessIndex++;
            }
            answerIndex++;
            guessIndex = 0;
        }
        
        //Create GuessResult
        String guessResult = "e:" + exactMatch + ":p:" + partialMatch;
        
        //Create GuessTime
        LocalTime localTime = LocalTime.now();
        String guessTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        //Create new Round to contain the details 
        //Won't include RoundID, the addRound method will do that
        Round r = new Round();
        
        r.setGameID(GameID);
        r.setGuessValue(guessValue);
        r.setGuessTime(guessTime);
        r.setGuessResult(guessResult);
        return r;
    }
}
package gdb.guessthenumber.controller;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import gdb.guessthenumber.service.GameService;
import gdb.guessthenumber.service.GameServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * M2 Summative
 * @author garrettbecker
 */

@RestController
@RequestMapping()
public class GameController {
    
    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int startNewGame() {
        Game newGame = gameService.generateNewGame();
        newGame = gameService.addGame(newGame);
        return newGame.getGameID();
    }
    
    /*
    @PostMapping("/guess")
    public Round makeGuess(int GameID, String guessValue) {
        //Create new Round and add it to DB
        Round newRound = gameService.generateNewRound(GameID, guessValue);
        newRound = gameService.addRound(newRound);
        
        //Get Answer from the current Game being played
        Game currentGame = new Game();
        currentGame = gameService.getGameByID(GameID, false);
        String gameAnswer = currentGame.getAnswer();
        
        //Check and see if the guess is same as the Answer
        //If so, update Game status
        if (gameAnswer.equals(guessValue)) {
            currentGame.setStatus("Finished");
            gameService.updateGame(currentGame);
        }
        
        return newRound;
    }
    */
    
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round r) {
        //Create new Round and add it to DB
        Round newRound = gameService.generateNewRound(r.getGameID(), r.getGuessValue());
        newRound = gameService.addRound(newRound);
        
        //Get Answer from the current Game being played
        Game currentGame = new Game();
        currentGame = gameService.getGameByID(r.getGameID(), false);
        String gameAnswer = currentGame.getAnswer();
        
        //Check and see if the guess is same as the Answer
        //If so, update Game status
        if (gameAnswer.equals(r.getGuessValue())) {
            currentGame.setStatus("Finished");
            gameService.updateGame(currentGame);
        }
        
        return newRound;
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
    
    @GetMapping("game/{gameId}")
    public Game getGameByID(@PathVariable int gameId) {
        Game result = gameService.getGameByID(gameId, true);
        return result;
    }
    
    @GetMapping("rounds/{gameId}")
    public List<Round> getRoundsByGameID(@PathVariable int gameId) {
        return gameService.getRoundsByGameID(gameId);
    }
}

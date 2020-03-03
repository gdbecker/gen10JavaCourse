package gdb.guessthenumber.controller;

import gdb.guessthenumber.entity.Game;
import gdb.guessthenumber.entity.Round;
import gdb.guessthenumber.service.GameService;
import gdb.guessthenumber.service.GameServiceImpl;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * M2 Summative
 * @date Monday January 20, 2020
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
    
    @PostMapping("/guess")
    public ResponseEntity<Round> makeGuess(@RequestBody Round r) {
        int id = r.getGameID();
        String idString = Integer.toString(id);
        char[] idStringArray = idString.toCharArray();
        for (char c : idStringArray) {
            if (c == '.') {
                Error err = new Error();
                err.setMessage("Invalid GameID input: GameID cannot be decimal value.");
                return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
            }
        }
        
        //Create new Round and add it to DB
        Round newRound = new Round();
        try {
            Game exists = gameService.getGameByID(id, false);
            //Send error if user tries to create new guess with game that is Finished
                if (exists.getStatus().equals("Finished")) {
                    Error err = new Error();
                    err.setMessage("Invalid GameID input: Game is already finished! No new guesses allowed.");
                    return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
                }
            newRound = gameService.generateNewRound(r.getGameID(), r.getGuessValue());
            newRound = gameService.addRound(newRound);
        } catch (NullPointerException e) {
            Error err = new Error();
            err.setMessage("Invalid GameID input: GameID does not exist.");
            return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
        } catch (ArrayIndexOutOfBoundsException e) {
            Error err = new Error();
            err.setMessage("Invalid GuessValue input: Input is required to be "
                    + "four unique numerical values to be valid.");
            return new ResponseEntity(err, HttpStatus.BAD_REQUEST);   
        } catch (DataIntegrityViolationException e) {
            Error err = new Error();
            err.setMessage("Invalid GuessValue input: Input length too long. "
                    + "Limit is four numerical values");
            return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
        }
        
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
        
        return ResponseEntity.ok(newRound);
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
    
    @GetMapping("game/{gameId}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameId) {
        Game result = gameService.getGameByID(gameId, true);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsByGameID(@PathVariable int gameId) {
        List<Round> rounds = gameService.getRoundsByGameID(gameId);
        if (rounds.size() == 0) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rounds);
    }
}

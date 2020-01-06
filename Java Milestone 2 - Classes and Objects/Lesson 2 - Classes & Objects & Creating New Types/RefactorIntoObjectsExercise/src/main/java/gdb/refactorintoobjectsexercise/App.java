package gdb.refactorintoobjectsexercise;

/**
 * @date Monday December 9, 2019
 * @author Garrett Becker
 */

//Main file that creates new objects from refactored java files and runs them
public class App {
    public static void main(String[] args) {
        //Factorizer
        Factorizor myFactorizer = new Factorizor();
        myFactorizer.factorize();
        
        //Interest Calculator
        InterestCalculator myCalculator = new InterestCalculator();
        myCalculator.calculate();
        
        //Lucky Sevens
        LuckySevens myGame = new LuckySevens();
        myGame.playGame();
        
        //Rock, Paper, Scissors
        RockPaperScissors rpsGame = new RockPaperScissors();
        rpsGame.playGame();
    }
}
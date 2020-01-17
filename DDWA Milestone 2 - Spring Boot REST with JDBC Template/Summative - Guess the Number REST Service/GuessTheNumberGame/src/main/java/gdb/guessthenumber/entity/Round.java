package gdb.guessthenumber.entity;

import java.time.LocalTime;
import java.util.Objects;

/**
 * M2 Summative
 * @author garrettbecker
 */

public class Round {
    int RoundID;
    int GameID;
    String GuessValue;
    String GuessTime;
    String GuessResult;

    public int getRoundID() {
        return RoundID;
    }

    public void setRoundID(int RoundID) {
        this.RoundID = RoundID;
    }

    public int getGameID() {
        return GameID;
    }

    public void setGameID(int GameID) {
        this.GameID = GameID;
    }

    public String getGuessValue() {
        return GuessValue;
    }

    public void setGuessValue(String GuessValue) {
        this.GuessValue = GuessValue;
    }

    public String getGuessTime() {
        return GuessTime;
    }

    public void setGuessTime(String GuessTime) {
        this.GuessTime = GuessTime;
    }

    public String getGuessResult() {
        return GuessResult;
    }

    public void setGuessResult(String GuessResult) {
        this.GuessResult = GuessResult;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.RoundID;
        hash = 23 * hash + this.GameID;
        hash = 23 * hash + Objects.hashCode(this.GuessValue);
        hash = 23 * hash + Objects.hashCode(this.GuessTime);
        hash = 23 * hash + Objects.hashCode(this.GuessResult);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.RoundID != other.RoundID) {
            return false;
        }
        if (this.GameID != other.GameID) {
            return false;
        }
        if (!Objects.equals(this.GuessValue, other.GuessValue)) {
            return false;
        }
        if (!Objects.equals(this.GuessResult, other.GuessResult)) {
            return false;
        }
        if (!Objects.equals(this.GuessTime, other.GuessTime)) {
            return false;
        }
        return true;
    }
}

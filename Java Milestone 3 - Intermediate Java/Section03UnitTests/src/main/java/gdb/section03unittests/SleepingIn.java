package gdb.section03unittests;

/**
 * @date Wednesday December 18, 2019
 * @author garrettbecker
 */

public class SleepingIn {
    // The parameter weekday is true if it is a weekday, and the 
    // parameter vacation is true if we are on vacation. We sleep 
    // in if it is not a weekday or we're on vacation. Return true 
    // if we sleep in. 
    //
    // canSleepIn(false, false) -> true
    // canSleepIn(true, false) -> false
    // canSleepIn(false, true) -> true
    public boolean canSleepIn(boolean isWeekday, boolean isVacation) {
        if (!isWeekday || isVacation) {
            return true;
        } else {
            return false;
        }
    }
}

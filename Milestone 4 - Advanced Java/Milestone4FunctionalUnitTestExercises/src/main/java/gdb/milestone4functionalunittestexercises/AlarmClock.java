package gdb.milestone4functionalunittestexercises;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class AlarmClock {
    // Given a day of the week encoded as 
    // 0=Sun, 1=Mon, 2=Tue, ...6=Sat, and a boolean indicating 
    // if we are on vacation, return a String of the form "7:00" 
    // indicating when the alarm clock should ring. Weekdays, the 
    // alarm should be "7:00" and on the weekend it should be "10:00". 
    // Unless we are on vacation -- then on weekdays it should be 
    // “10:00" and weekends it should be "off". 
    //
    // alarmClock(1, false) → "7:00"
    // alarmClock(5, false) → "7:00"
    // alarmClock(0, false) → "10:00"
    public String alarmClock(int day, boolean vacation) {
        String result = "";
        if (!vacation) {
            if (day == 0 || day == 6) {
                result = "10:00";
            } else {
                result = "7:00";
            }
        } else {
            if (day == 0 || day == 6) {
                result = "off";
            } else {
                result = "10:00";
            }
        }
        return result;
    }
}

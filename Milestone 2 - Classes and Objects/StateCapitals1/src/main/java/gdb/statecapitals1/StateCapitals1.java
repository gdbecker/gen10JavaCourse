package gdb.statecapitals1;

/**
 * @date Tuesday December 10, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class StateCapitals1 {
    public static void main(String[] args) {
        //Create HashMap of <states, capitals>
        HashMap<String, String> statesCapitals = new HashMap<>();
        
        //Load stateCapitals with data
        statesCapitals.put("Alabama","Montgomery");
        statesCapitals.put("Alaska","Juneau");
        statesCapitals.put("Arizona","Phoenix");
        statesCapitals.put("Arkansas","Little Rock");
        statesCapitals.put("California","Sacramento");
        statesCapitals.put("Colorado","Denver");
        statesCapitals.put("Connecticut","Hartford");
        statesCapitals.put("Delaware","Dover");
        statesCapitals.put("Florida","Tallahassee");
        statesCapitals.put("Georgia","Atlanta");
        statesCapitals.put("Hawaii","Honolulu");
        statesCapitals.put("Idaho","Boise");
        statesCapitals.put("Illinois","Springfield");
        statesCapitals.put("Indiana","Indianapolis");
        statesCapitals.put("Iowa","Des Moines");
        statesCapitals.put("Kansas","Topeka");
        statesCapitals.put("Kentucky","Frankfort");
        statesCapitals.put("Louisiana","Baton Rouge");
        statesCapitals.put("Maine","Augusta");
        statesCapitals.put("Maryland","Annapolis");
        statesCapitals.put("Massachusetts","Boston");
        statesCapitals.put("Michigan","Lansing");
        statesCapitals.put("Minnesota","St Paul");
        statesCapitals.put("Mississippi","Jackson");
        statesCapitals.put("Missouri","Jefferson City");
        statesCapitals.put("Montana","Helena");
        statesCapitals.put("Nebraska","Lincoln");
        statesCapitals.put("Nevada","Carson City");
        statesCapitals.put("New Hampshire","Concord");
        statesCapitals.put("New Jersey","Trenton");
        statesCapitals.put("New Mexico","Santa Fe");
        statesCapitals.put("New York","Albany");
        statesCapitals.put("North Carolina","Raleigh");
        statesCapitals.put("North Dakota","Bismarck");
        statesCapitals.put("Ohio","Columbus");
        statesCapitals.put("Oklahoma","Oklahoma City");
        statesCapitals.put("Oregon","Salem");
        statesCapitals.put("Pennsylvania","Harrisburg");
        statesCapitals.put("Rhode Island","Providence");
        statesCapitals.put("South Carolina","Columbia");
        statesCapitals.put("South Dakota","Pierre");
        statesCapitals.put("Tennessee","Nashville");
        statesCapitals.put("Texas","Austin");
        statesCapitals.put("Utah","Salt Lake City");
        statesCapitals.put("Vermont","Montpelier");
        statesCapitals.put("Virginia","Richmond");
        statesCapitals.put("Washington","Olympia");
        statesCapitals.put("West Virginia","Charleston");
        statesCapitals.put("Wisconsin","Madison");
        statesCapitals.put("Wyoming","Cheyenne");
        
        System.out.println(statesCapitals.size());
        
        //Print out all state names to console
        Set<String> keys = statesCapitals.keySet();
        
        System.out.println("STATES");
        System.out.println("======");
        
        for (String k : keys) {
            System.out.println(k);
        }
        
        System.out.println("");
        
        //Print out all capital names to console
        Collection<String> capitals = statesCapitals.values();
        
        System.out.println("CAPITALS");
        System.out.println("======");
        
        for (String p : capitals) {
            System.out.println(p);
        }
        
        System.out.println("");
        
        //Print out all state - capital pair to console
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("====================");
        
        for (String k : keys) {
            System.out.println(k + " - " + statesCapitals.get(k));
        }
        
    }
}

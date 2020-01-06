package gdb.statecapitals2;

/**
 * @date Wednesday December 11, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class StateCapitals2 {
    public static void main(String[] args) {
        //Declare and initialize key variables
        Map<String, Capital> statesCapitals = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        
        //Load stateCapitals with data
        Capital montgomery = new Capital("Montgomery", 205000, 156);
        Capital juneau = new Capital("Juneau", 214000, 827);
        Capital phoenix = new Capital("Phoenix", 195000, 123);
        Capital littleRock = new Capital("Little Rock", 95000, 765);
        Capital sacramento = new Capital("Sacramento", 728271, 243);
        Capital denver = new Capital("Denver", 827364, 928);
        Capital hartford = new Capital("Hartford", 817293, 432);
        Capital dover = new Capital("Dover", 728361, 476);
        Capital tallahassee = new Capital("Tallahassee", 728372, 132);
        Capital atlanta = new Capital("Atlanta", 928374, 873);
        Capital honolulu = new Capital("Honolulu", 82934, 321);
        Capital boise = new Capital("Boise", 8234, 625);
        Capital springfield = new Capital("Springfield", 928374, 982);
        Capital indianapolis = new Capital("Indianapolis", 728374, 726);
        Capital desMoines = new Capital("Des Moines", 72837, 625);
        Capital topeka = new Capital("Topeka", 52436, 243);
        Capital frankfort = new Capital("Frankfort", 728374, 376);
        Capital batonRouge = new Capital("Baton Rouge", 7263, 492);
        Capital augusta = new Capital("Augusta", 17263, 309);
        Capital annapolis = new Capital("Annapolis", 8982, 107);
        Capital boston = new Capital("Boston", 98374, 625);
        Capital lansing = new Capital("Lansing", 827363, 921);
        Capital stPaul = new Capital("St Paul", 27643, 763);
        Capital jackson = new Capital("Jackson", 472837, 289);
        
        statesCapitals.put("Alabama",montgomery);
        statesCapitals.put("Alaska",juneau);
        statesCapitals.put("Arizona",phoenix);
        statesCapitals.put("Arkansas",littleRock);
        statesCapitals.put("California",sacramento);
        statesCapitals.put("Colorado",denver);
        statesCapitals.put("Connecticut",hartford);
        statesCapitals.put("Delaware",dover);
        statesCapitals.put("Florida",tallahassee);
        statesCapitals.put("Georgia",atlanta);
        statesCapitals.put("Hawaii",honolulu);
        statesCapitals.put("Idaho",boise);
        statesCapitals.put("Illinois",springfield);
        statesCapitals.put("Indiana",indianapolis);
        statesCapitals.put("Iowa",desMoines);
        statesCapitals.put("Kansas",topeka);
        statesCapitals.put("Kentucky",frankfort);
        statesCapitals.put("Louisiana",batonRouge);
        statesCapitals.put("Maine",augusta);
        statesCapitals.put("Maryland",annapolis);
        statesCapitals.put("Massachusetts",boston);
        statesCapitals.put("Michigan",lansing);
        statesCapitals.put("Minnesota",stPaul);
        statesCapitals.put("Mississippi",jackson);
        
        //Print out all state - capital pair to console
        Set<String> keys = statesCapitals.keySet();
        
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("====================");
        
        for (String k : keys) {
            System.out.println(k + " - " + statesCapitals.get(k).toString());
        }
        
        //Get user input for population size & print out all pairs with 
        //population greater than the input
        System.out.println("\nPlease enter the lower limit for capital city population:");
        int input = sc.nextInt();
        System.out.println("\nLISTING CAPITALS WITH POPULATIONS GREATER THAN " + input + ":");
        
        for (String k : keys) {
            if (statesCapitals.get(k).getPopulation() > input) {
                System.out.println(k + " - " + statesCapitals.get(k).toString());
            }
        }
    }
    
}

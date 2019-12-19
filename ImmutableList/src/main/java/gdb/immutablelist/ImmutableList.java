package gdb.immutablelist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author garrettbecker
 */
final public class ImmutableList {
    private List<String> myList = new ArrayList<>();
    private List<String> myImmutableList = new Collections.unmodifiableList(myList);
    
    //Constructor
    public ImmutableList(String[] stringArray) {
        for (String s : stringArray) {
            myList.add(s);
        }
    }

    public List<String> getMyList() {
        return myList;
    }
    
}

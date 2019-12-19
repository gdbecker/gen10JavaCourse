package gdb.immutablelist;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author garrettbecker
 */

public class App {
    public static void main(String[] args) {
        String[] stringArray = {"Hello", "Hi", "Hola"};
        
        ImmutableList newList = new ImmutableList(stringArray);
        List<String> duplicateList = newList.getMyList();
        
        
        
    }
}

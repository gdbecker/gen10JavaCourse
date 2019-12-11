package gdb.studentquizscores;

/**
 * @date Wednesday December 11, 2019
 * @author Garrett Becker
 */

import java.util.*;
public class StudentQuizGrades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Create main map to house all student and quiz information
        //<"Students' Names", [array of quiz scores]> 
        Map<String, ArrayList<Integer>> students = new HashMap<>();
        
        //Create UserIO object to handle inputs and outputs 
        UserIO userio = new UserIO();
        
        //Add some initial data
        ArrayList<Integer> gregScores = new ArrayList<>();
        students.put("Greg", gregScores);
        gregScores.add(87);
        gregScores.add(34);
        gregScores.add(90);
        
        ArrayList<Integer> aliceScores = new ArrayList<>();
        students.put("Alice", aliceScores);
        aliceScores.add(100);
        aliceScores.add(79);
        aliceScores.add(96);
        
        ArrayList<Integer> frankScores = new ArrayList<>();
        students.put("Frank", frankScores);
        frankScores.add(46);
        frankScores.add(93);
        frankScores.add(76);
        
        ArrayList<Integer> adamScores = new ArrayList<>();
        students.put("Adam", adamScores);
        adamScores.add(98);
        adamScores.add(100);
        adamScores.add(90);
        
        ArrayList<Integer> amyScores = new ArrayList<>();
        students.put("Amy", amyScores);
        amyScores.add(68);
        amyScores.add(84);
        amyScores.add(92);
        
        //Create overall loop to keep user within application until decision to exit
        userio.print("-- Welcome to the Student Quiz Score Database --");
        String userInput = "";
        while (!userInput.equals("e")) {
            userio.print("Please input what you would like to do: ");
            userio.print("(1) View list of students within the system");
            userio.print("(2) Add a student to the system");
            userio.print("(3) Remove a student from the system");
            userio.print("(4) View quiz scores for a student");
            userio.print("(5) View average quiz score for a student");
            userio.print("(e) Exit the Database");
            
            userInput = userio.readString("Input option: ");
            
            //Error handling to get valid input
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("e")) {
                userio.print("Error: invalid input. Please try again.");
                userInput = userio.readString("Input valid option from the list above: ");
            } 
            
            //After getting valid input, do action requested by user
            //Option (1): View list of students within the system
            if (userInput.equals("1")) {
                
                //Create Set of student names, print to screen
                Set<String> keys = students.keySet();
                userio.print("\nList of students in database: ");
                
                for (String k : keys) {
                    System.out.println(k);
                }
                
                System.out.println("\n");
            }
            
            //Option (2): Add a student to the system
            else if (userInput.equals("2")) {
                String name = userio.readString("\nWhich student would you like to add?");
                ArrayList<Integer> newStudentScores = new ArrayList<>();
                students.put(name, newStudentScores);
                
                int num = userio.readInt("\nHow many quiz scores would you like to enter?");
                
                for (int i = 1; i <= num; i++) {
                    newStudentScores.add(userio.readInt("Input quiz score: "));
                }
                
                sc.nextLine();
         
            }
            
            //Option (3): Remove a student from the system
            else if (userInput.equals("3")) {
                String name = userio.readString("\nWhich student would you like to remove?");
                Set<String> keys = students.keySet();
                List<String> studentList = new ArrayList<>(keys);
                int counter = 0;
                boolean hasRemoved = false;
                
                Iterator itr = keys.iterator();
                while (itr.hasNext()) {
                    String currentName = (String)itr.next(); 
                    if (currentName.equalsIgnoreCase(name)) {
                        itr.remove();
                        hasRemoved = true;
                    }
                }
                
                if (!hasRemoved) {
                    userio.print("Student not found!");
                }
                
                userio.print("\n");
            }
            
            //Option (4): View quiz scores for a student
            else if (userInput.equals("4")) {
                String name = userio.readString("\nWhich student would you like to see quiz scores for?");
                Set<String> keys = students.keySet();
                ArrayList<Integer> toViewScores = new ArrayList<>();
                String printName = "";
                boolean studentExists = false;
                
                for (String k : keys) {
                    if (k.equalsIgnoreCase(name)) {
                        toViewScores = students.get(k);
                        printName = k;
                        studentExists = true;
                    }
                }
                
                //Print out quiz scores (only if student was found)
                if (studentExists) {
                    userio.print("\nQuiz scores for " + printName);
                
                    for (Integer i : toViewScores) {
                        userio.print(i + "");
                    }
                } else {
                    userio.print("\nStudent not found!");
                }
                    
                userio.print("");
            }
            
            //Option (5): View average quiz score for a student
            else if (userInput.equals("5")) {
                String name = userio.readString("\nWhich student would you like to see the average quiz score for?");
                Set<String> keys = students.keySet();
                ArrayList<Integer> toViewScores = new ArrayList<>();
                String printName = "";
                int counter = 0;
                int sum = 0;
                boolean studentExists = false;
                
                for (String k : keys) {
                    if (k.equalsIgnoreCase(name)) {
                        toViewScores = students.get(k);
                        printName = k;
                        studentExists = true;
                    }
                }
                
                //Find average of quiz scores and print (only if student was found
                if (studentExists) {
                    userio.print("Quiz scores for " + printName);
                
                    for (Integer i : toViewScores) {
                        sum += i;
                        counter++;
                    }
                
                    int average = sum / counter;
                
                    userio.print("Quiz average for " + printName + ": ");
                    userio.print(average + "");
                    userio.print("\n");
                } else {
                    userio.print("\nStudent not found!");
                    userio.print("\n");
                }
            }
            
        }
        
        System.out.println("-- You exited the Database --");
        
    }
}

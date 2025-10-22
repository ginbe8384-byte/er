import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TheatreManager {
    // 4.2
    private Theatre[] tArr = new Theatre[50];
    private int tSize = 0;
    private Play[] pArr = new Play[50];
    private int pSize = 0;

    // 4.3
    public TheatreManager(){
        try{
            Scanner file = new Scanner("Production.txt");
            String line = file.nextLine();
            String tokens[] = line.split(";");
            if (tokens.length == 5) {
                String play = tokens[0];
                boolean trap = Boolean.parseBoolean(tokens[1]);
                int license = Integer.parseInt(tokens[2]);
                String first = tokens[3];
                String last = tokens[4];
                pArr[pSize] = new Play(play, trap, license, first, last);
                pSize++;
            } else if (tokens.length == 3){
                String play = tokens[0];
                boolean trap = Boolean.parseBoolean(tokens[1]);
                int seats = Integer.parseInt(tokens[2]);
                tArr[tSize] = new Theatre(play, trap, seats);
                tSize++;
            }
            file.close();

        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    // 4.4
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < tSize; i++) {
            temp = temp + tArr[i].toString() + "\n";
        }
        return temp;
    }

    // 4.5
    public void sortTheatre(){
        for (int i = 0; i < tSize - 1; i++) {
            for (int j = i + 1; j < tSize; j++) {
                if (tArr[i].getSeats() < tArr[j].getSeats()) {
                    Theatre temp = tArr[i];
                    tArr[i] = tArr[j];
                    tArr[j] = temp;
                }
            }
        }
    }

    public static int search(int[] numbers, int searchVal) {
        boolean found = false;
        int i = 0;
        int index = -1;
        while (!found && i < numbers.length) {
            if (numbers[i] == searchVal) {
                found = true;
                index = i;
            }
            i++;
        }
        return index;
    }

    public static String search(String search) {
        String result = "No matching task found.";

        // Loop through each Task in the array
        for (int i = 0; i < tArr.length; i++) {

            // Check if the search string matches any of the string fields in the Task
            if (tArr[i].getPlay().equalsIgnoreCase(search)) {
                // If a match is found, return the task name
                result = tArr[i].getTaskName();
                break;  // Stop the loop after finding the first match
            }
        }

        // Return the result (either task name or the default message)
        return result;
    }

    /**
    public Theatre searchTheatre(String keyword) {
        int pos = 0;
        while (pos < tSize) {
            if (tArr[pos].getName().equals(keyword)) {
                return tArr[pos];  // Return the theatre at the correct position
            }
            pos++;
        }
        return null;  // Return null if no matching theatre is found
    }
     */

    // 6.2
    public String allocatePlays(){
        String unassigned = "Unassigned Plays\n";
        for (int i = 0; i < pSize; i++) {
            int j = 0;
            boolean assigned = false;
            while (!assigned&& j < tSize) {
                if (pArr[i].getLicense().equals("Local") && tArr[j].getSeats() >= 200){
                    assigned = tArr[j].addPlay(pArr[i]);
                }
                else if (pArr[i].getLicense().equals("International") && tArr[j].getSeats() < 200){
                    assigned= tArr[j].addPlay(pArr[i]);
                    j++;
                }
                if (!assigned) {
                    unassigned = unassigned + pArr[i].getName() + "\n";
                }
            }

        }
        return unassigned;
    }
}
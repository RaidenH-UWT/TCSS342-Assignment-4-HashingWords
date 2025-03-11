import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

// 25,143 lines in words.txt

/**
 * This is the driver class for the Anagram application working with a Hash Table
 * @author raidenh
 */
public class Driver {
    private static final boolean DEBUG = false;
    private static final String EXITMSG = "I <3 O(1)";
    private static int CAPACITY = 63421;
    private static final int DEBUGCAPACITY = 51;

    private static void print(Object object) {
        System.out.println(object);
    }

    // Load all words from words.txt into a HashTable
    // Search for all anagrams of a given word. do not count a word as an anagram of itself
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        if (DEBUG) CAPACITY = DEBUGCAPACITY;
        HashTable table = new HashTable(CAPACITY);

        // Building the HashTable from file
        try {
            File file = new File("words.txt");
            Scanner reader = new Scanner(file);
            // For limiting the count for testing
            int counter = 0;
            while(reader.hasNextLine()) {
                counter++;
                if (DEBUG && counter > 50) break;
                table.add(reader.nextLine());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Counting collisions
        int collisions = 0;
        for (Anagram a : table.getArray()) {
            if (a != null && a.getCollision()) collisions++;
        }

        // Default output
        if (DEBUG) print(table);
        print("\n" + table.length() + " words added to the HashTable with " + collisions 
            + " collisions. Load factor is " + ((float) table.length() / CAPACITY) + ".\n");


        // User input for searching
        String input;
        Scanner reader;
        do {
            reader = new Scanner(System.in);
            print("Enter search term, or '" + EXITMSG + "' to exit:");

            input = reader.nextLine();
            print("\nYou entered: " + input);
            try {
                if (input.toLowerCase().contains("dragon")) {
                    print("WOOOOOO DRAGONS!!!!!");
                }
                
                Anagram anagram = table.get(table.search(input));
                int count = anagram.getValues().size();
                if (anagram.getValues().contains(input)) count--;

                // Found match
                String result = "Found '" + input + "', " + count + " anagrams\n";
                
                // Assembling anagrams
                LinkedList<String> anagrams = new LinkedList<String>(anagram.getValues());
                anagrams.remove(input);
                
                if (anagrams.size() > 0) result += Arrays.toString(anagrams.toArray()) + "\n";

                print(result);
            } catch (NullPointerException e) {
                print("Search found no results\n");
            }
        }
        while (!EXITMSG.equals(input));
        print("Exiting...");
        reader.close();
    }
}
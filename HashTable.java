import java.util.Arrays;

/**
 * HashTable class for O(1) operations.
 * @author raidenh
 */
public class HashTable {
    private Anagram[] array;
    private int size = 0;

    public HashTable(int capacity) {
        super();

        array = new Anagram[capacity];
    }

    /**
     * takes a key as a parameter and computes the correct int index position and returns it
     * this method guarantees that all values are stored in the same index of the hash table
     */
    public int hashCode(String theKey) {
        return Math.abs(theKey.hashCode()) % array.length; // using the String.hashCode() method as per spec
    }

    /**
     * adds new (if key not found) Anagram object to to the hash table at the correct index
     * or updates the existing Anagram object if the key already exists
     */
    public void add(String theValue) {
        int index = selectIndex(theValue);
        if (array[index] == null) {
            array[index] = new Anagram(theValue);
        } else {
            array[index].addValue(theValue);
        }
        size++;
    }

    /**
     * takes a String parameter and using the hashCode() method returns the index where it can be found
     * Returns -1 if theValue is not found in the HashTable
     */
    public int search(String theValue) {
        int out = -1;
        int index = selectIndex(theValue);
        if (array[index].isAnagram(theValue)) out = index;
        return out;
    }

    private int selectIndex(String theValue) {
        theValue = theValue.toLowerCase();

        char[] arr = theValue.toCharArray();
        Arrays.sort(arr);
        String sorted = new String(arr);

        int out = -1;
        int index = hashCode(sorted);
        int square = 0;

        while (true) {
            if (array[index] == null) {
                out = index;
                break;
            } else if (array[index].isAnagram(theValue)) {
                out = index;
                break;
            } else {
                array[index].setCollision(true);
                index = (index + (square * square)) % array.length;
                square++;
            }
        }

        return out;
    }

    /**
     * Getter method for the Anagram objects stored in the HashTable
     * @param theIndex index of the Anagram to retrieve
     * @return an Anagram object
     */
    public Anagram get(int theIndex) {
        if (theIndex < 0) {
            return null;
        } else if (theIndex > array.length) {
            throw new IndexOutOfBoundsException();
        }
        return array[theIndex];
    }

    /**
     * returns the underlying array of the HashTable
     */
    public Anagram[] getArray() {
        return array;
    }

    /**
     * deletes a value from the HashTable if it exists
     */
    public void delete(String theValue) {
        theValue = theValue.toLowerCase();
        int index = search(theValue);
        if (index >= 0) { 
            array[search(theValue)].removeValue(theValue);
        }
    }

    /**
     * gets the amount of items in this HashTable
     * @return int number of objects in this HashTable
     */
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
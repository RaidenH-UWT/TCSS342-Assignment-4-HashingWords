import java.util.Arrays;
import java.util.LinkedList;

/**
 * Anagram class, stores a key (a sorted String) and a List<String> of words which are an anagram of that key.
 * @author raidenh
 */
public class Anagram {
    private final String key;
    private LinkedList<String> values;
    private boolean flag = false;

    /**
     * Initializes a new Anagram object with the key being a sorted version of theKey and storing theKey as its first value
     * @param theKey
     */
    public Anagram(String theKey) {
        super();

        values = new LinkedList<String>();

        char[] sorted = theKey.toCharArray();
        Arrays.sort(sorted);
        key = new String(sorted);

        addValue(theKey);
    }

    /**
     * returns the Anagram key
     */
    public String getKey() {
        return key;
    }

    /**
     * returns the collection of values
     */
    public LinkedList<String> getValues() {
        return values;
    }

    /**
     * takes a value as a parameter and adds it to the existing collection of values
     */
    public void addValue(String theValue) {
        values.add(theValue);
    }

    /**
     * Removes a value from this Anagram object
     * @param theValue the value to be removed
     * @return true if the value was found in this Anagram, false otherwise
     */
    public boolean removeValue(String theValue) {
        return values.remove(theValue);
    }

    /**
     * Setter method to set the collision flag
     * @param theBool new value for the flag
     */
    public void setCollision(boolean theBool) {
        flag = theBool;
    }

    /**
     * Check whether there's been a collision at this index
     * @return true if there has been a collision at this index, false otherwise
     */
    public boolean getCollision() {
        return flag;
    }

    /**
     * Method returning whether the input string is an anagram of this Anagram object or not
     * @param theString String to check
     * @return true if theString is an anagram of this Anagram object, false otherwise.
     */
    public boolean isAnagram(String theString) {
        theString = theString.toLowerCase();
        char[] arr = theString.toCharArray();
        Arrays.sort(arr);
        String sorted = new String(arr);
        return sorted.equals(getKey());
    }

    @Override
    public String toString() {
        return getKey() + "; " + Arrays.toString(getValues().toArray());
    }
}
import java.util.Set;
import java.util.TreeSet;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 6.
 * andrewID: zhixuanj
 * @author Zhixuan Jiang
 */

public class Word implements Comparable<Word> {
    /**
     * The word itself.
     */
    private String word;

    /**
     * The index of the word in the file.
     * It is a set of integers, each representing a line number.
     */
    private Set<Integer> index;

    /**
     * The frequency of the word in the file.
     * It is an integer representing how many times the word appears.
     */
    private int frequency;

    /**
     * Default constructor.
     * Initializes the frequency to 0 and the index to an empty TreeSet.
     */
    public Word() {
        this.frequency = 0;
        this.index = new TreeSet<>();
    }

    /**
     * Constructor with a word.
     * Initializes the word, sets the frequency to 0, and the index to an empty TreeSet.
     *
     * @param w The word to initialize.
     */
    public Word(String w) {
        this.word = w;
        this.index = new TreeSet<>();
        this.frequency = 0;
    }

    /**
     * Gets the word.
     *
     * @return The word as a String.
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the word.
     *
     * @param s The word to set.
     */
    public void setWord(String s) {
        this.word = s;
    }

    /**
     * Gets the frequency of the word.
     *
     * @return The frequency as an integer.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency of the word.
     *
     * @param freq The frequency to set.
     */
    public void setFrequency(int freq) {
        this.frequency = freq;
    }

    /**
     * Adds a line number to the index.
     *
     * @param line The line number to add.
     */
    public void addToIndex(Integer line) {
        if (line != null) {
            index.add(line);
        }
    }

    /**
     * Gets the index of line numbers where the word appears.
     *
     * @return A set of integers representing the line numbers.
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * Compares this Word object with another Word object for order.
     * The comparison is based on the word's lexicographical order.
     *
     * @param o The Word object to compare to.
     * @return A negative integer, zero, or a positive integer as this word
     *         is less than, equal to, or greater than the specified word.
     * @throws NullPointerException if the specified object is null.
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Word o) {
        if (o == null) {
            throw new NullPointerException("object is null");
        }
        if (!(o instanceof Word)) {
            throw new ClassCastException("object's type is not Word");
        }
        return this.word.compareTo(o.word);
    }

    /**
     * Returns a string representation of the Word object.
     * The string includes the word, its frequency, and its index.
     *
     * @return A string representation of the Word object.
     */
    @Override
    public String toString() {
        return word + " " + frequency + " " + index.toString();
    }
}

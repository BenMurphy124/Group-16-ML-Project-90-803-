import java.util.Comparator;
/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 6.
 *
 * andrewID: zhixuanj
 * @author Zhixuan Jiang
 */
public class IgnoreCase implements Comparator<Word> {
    /**
     * Compares two Word objects ignoring case.
     * @param word1 the first Word object
     * @param word2 the second Word object
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Word word1, Word word2) {
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null) {
            return -1;
        }
        if (word2 == null) {
            return 1;
        }
        return word1.getWord().toLowerCase().compareTo(word2.getWord().toLowerCase());
    }
}

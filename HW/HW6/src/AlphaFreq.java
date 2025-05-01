import java.util.Comparator;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 6.
 *
 * andrewID: zhixuanj
 * This class implements a comparator for Word objects.
 * It compares two Word objects first by their word in alphabetical order,
 * and if the words are the same, by their frequency in descending order.
 * This comparator is used to sort Word objects based on these criteria.
 *
 * @author Zhixuan Jiang
 */

public class AlphaFreq implements Comparator<Word> {

    /**
     * Compares two Word objects first by their word (in alphabetical order),
     * and then by their frequency (in descending order).
     *
     * @param word1 the first Word object to compare
     * @param word2 the second Word object to compare
     * @return a negative integer if word1 is less than word2, zero if they are equal,
     *         or a positive integer if word1 is greater than word2
     */
    @Override
    public int compare(Word word1, Word word2) {
        if (word1 == null && word2 == null) {
            return 0; // Both words are null, considered equal
        }
        if (word1 == null) {
            return -1; // Null word1 is considered less than word2
        }
        if (word2 == null) {
            return 1;  // Null word2 is considered greater than word1
        }

        // Compare words alphabetically
        int alphaCompare = word1.getWord().compareTo(word2.getWord());

        if (alphaCompare != 0) {
            return alphaCompare; // Return result if words are not equal
        }

        // Compare frequencies in descending order
        return Integer.compare(word1.getFrequency(), word2.getFrequency());
    }
}

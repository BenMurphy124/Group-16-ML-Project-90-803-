import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 6.
 *
 * andrewID: zhixuanj
 * @author: Zhixuan Jiang
 */
public class Index {

    /**
     * Checks if a word is valid.
     * A valid word is non-null, non-empty, and contains only alphabetic characters.
     *
     * @param word The word to check.
     * @return true if the word is valid, false otherwise.
     */
    private boolean isValidWord(String word) {
        return word != null && !word.isEmpty() && word.matches("[a-zA-Z]+");
    }

    /**
     * Builds an index from a file.
     * The words are stored in a BST with their frequencies and line numbers.
     *
     * @param fileName The name of the file to read.
     * @return A BST containing the words and their frequencies.
     */
    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null);
    }

    /**
     * Builds an index from a file with a specified comparator.
     * The comparator determines the ordering of the words in the BST.
     *
     * @param fileName   The name of the file to read.
     * @param comparator The comparator to use for sorting the words.
     * @return A BST containing the words and their frequencies.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> bst = new BST<>(comparator);

        if (fileName == null || fileName.trim().isEmpty()) {
            return bst;
        }

        File file = new File(fileName);
        boolean ignoreCase = (comparator instanceof IgnoreCase);

        int lineCount = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lineCount++;
                String line = scanner.nextLine();
                String[] words = line.split("\\W");
                for (String word : words) {
                    if (word != null && !word.isEmpty() && word.matches("[a-zA-Z]+")) {
                        if (ignoreCase) {
                            word = word.toLowerCase();
                        }
                        Word searchWord = new Word(word);
                        Word found = bst.search(searchWord);
                        if (found != null) {
                            found.setFrequency(found.getFrequency() + 1);
                            found.addToIndex(lineCount);
                        } else {
                            Word newWord = new Word(word);
                            newWord.setFrequency(1);
                            newWord.addToIndex(lineCount);
                            bst.insert(newWord);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }

        return bst;
    }

    /**
     * Builds a BST index from a given list of Word objects.
     * The comparator determines the ordering of the words in the BST.
     *
     * @param list       The list of Word objects to be indexed.
     * @param comparator The comparator used to order the words in the BST.
     * @return A BST containing all unique Word objects from the list.
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> bst = new BST<>(comparator);

        if (list == null) {
            return bst;
        }

        for (Word word : list) {
            if (word == null) {
                continue;
            }

            if (bst.search(word) == null) {
                bst.insert(word);
            }
        }

        return bst;
    }

    /**
     * Sorts the words in the BST by alphabetical order.
     *
     * @param tree The BST to sort.
     * @return An ArrayList of Word objects sorted by alphabetical order.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<>();

        if (tree == null) {
            return result;
        }

        for (Word word : tree) {
            result.add(word);
        }

        result.sort(new AlphaFreq());

        return result;
    }

    /**
     * Sorts the words in the BST by frequency.
     *
     * @param tree The BST to sort.
     * @return An ArrayList of Word objects sorted by frequency.
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<>();

        if (tree == null) {
            return result;
        }

        for (Word word : tree) {
            result.add(word);
        }

        result.sort(new Frequency());

        return result;
    }

    /**
     * Retrieves the words with the highest frequency from the BST.
     *
     * @param tree The BST to search.
     * @return An ArrayList of Word objects with the highest frequency.
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<>();
        int maxFreq = 0;

        if (tree == null) {
            return result;
        }

        for (Word word : tree) {
            if (word.getFrequency() > maxFreq) {
                maxFreq = word.getFrequency();
            }
        }

        for (Word word : tree) {
            if (word.getFrequency() == maxFreq) {
                result.add(word);
            }
        }

        return result;
    }
}

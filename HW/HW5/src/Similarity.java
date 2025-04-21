import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 5.
 *
 * andrewID: zhixuanj
 * @author Zhixuan Jiang
 */
public class Similarity {
    /**
     * number of lines in the text.
     */
    private int numOfLines;
    /**
     * number of words in the text.
     */
    private BigInteger numOfWords;
    /**
     * number of distinct words in the text.
     */
    private int numOfWordsNoDups;
    /**
     * map of words and their frequencies.
     * HashMap is chosen for O(1) lookup and insertion operations,
     * which is optimal for word frequency counting and comparison.
     */
    private Map<String, BigInteger> wordFreq;
    /**
     * Constructor that takes a string and initializes the object.
     * @param string the string to be processed
     */
    public Similarity(final String string) {
        wordFreq = new HashMap<>();
        numOfLines = 0;
        numOfWords = BigInteger.ZERO;
        numOfWordsNoDups = 0;

        if (string == null || string.trim().isEmpty()) {
            return;
        }

        Scanner scanner = new Scanner(string);
        while (scanner.hasNextLine()) {
            numOfLines++;
            String line = scanner.nextLine();
            String[] words = line.split("\\W");
            for (String word : words) {
                    if (word != null && !word.isEmpty() && word.matches("[a-zA-Z]+")) {
                        word = word.toLowerCase();
                        BigInteger currentFreq = wordFreq.getOrDefault(word, BigInteger.ZERO);
                        wordFreq.put(word, currentFreq.add(BigInteger.ONE));
                        numOfWords = numOfWords.add(BigInteger.ONE);
                    }
            }
        }
        numOfWordsNoDups = wordFreq.size();
        scanner.close();
    }
    /**
     * Constructor that takes a file and initializes the object.
     * @param file the file to be processed
     */
    public Similarity(final File file) {
        wordFreq = new HashMap<>();
        numOfLines = 0;
        numOfWords = BigInteger.ZERO;
        numOfWordsNoDups = 0;

        if (file == null) {
            return;
        }

        try {
            Scanner scanner = new Scanner(file, "latin1");
            while (scanner.hasNextLine()) {
                numOfLines++;
                String line = scanner.nextLine();
                String[] words = line.split("\\W");
                for (String word : words) {
                    if (word != null && !word.isEmpty() && word.matches("[a-zA-Z]+")) {
                        word = word.toLowerCase();
                        BigInteger currentFreq = wordFreq.getOrDefault(word, BigInteger.ZERO);
                        wordFreq.put(word, currentFreq.add(BigInteger.ONE));
                        numOfWords = numOfWords.add(BigInteger.ONE);
                    }
                }
            }
            numOfWordsNoDups = wordFreq.size();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file: " + file.getName());
            numOfLines = 0;
            numOfWords = BigInteger.ZERO;
            numOfWordsNoDups = 0;
            wordFreq = new HashMap<>();
        }
    }

    /**
     * returns the number of lines in the text.
     * @return the number of lines
     */
    public int numOfLines() {
        return numOfLines;
    }
    /**
     * returns the number of words in the text.
     * @return the number of words
     */
    public BigInteger numOfWords() {
        return numOfWords;
    }
    /**
     * returns the number of distinct words in the text.
     * @return the number of distinct words
     */
    public int numOfWordsNoDups() {
        return numOfWordsNoDups;
    }

    /**
     * returns the Euclidean norm of the word frequencies.
     * @return the Euclidean norm
     */
    public double euclideanNorm() {
        BigInteger eucValSq = BigInteger.ZERO;
        for (BigInteger freq : wordFreq.values()) {
            eucValSq = eucValSq.add(freq.pow(2));
        }
        return Math.sqrt(eucValSq.doubleValue());
    }

    /**
     * returns the dot product of two word frequency maps.
     * This implementation avoids O(n ^ 2) complexity by always iterating through
     * the smaller map and looking up values in the larger map, resulting in
     * O(min(n,m)) complexity where n and m are the sizes of the two maps.
     * HashMap's get() operation is O(1) on average, making the overall
     * time complexity O(min(n,m)) rather than O(n*m).
     * @param map the map to be compared
     * @return the dot product
     */
    public double dotProduct(final Map<String, BigInteger> map) {

        if (map == null || wordFreq == null || wordFreq.isEmpty() || map.isEmpty()) {
            return 0.0;
        }

        Map<String, BigInteger> smaller = (map.size() < wordFreq.size()) ? map : wordFreq;
        Map<String, BigInteger> greater = (map.size() > wordFreq.size()) ? map : wordFreq;
        BigInteger dotProd = BigInteger.ZERO;

        for (String word : smaller.keySet()) {
            BigInteger freq1 = smaller.get(word);
            BigInteger freq2 = greater.get(word);
            if (freq2 != null) {
                dotProd = dotProd.add(freq1.multiply(freq2));
            }
        }
        return dotProd.doubleValue();
    }

    /**
     * returns the distance between two word frequency maps.
     * @param map the map to be compared
     * @return the distance
     */
    public double distance(final Map<String, BigInteger> map) {

        if (Objects.equals(map, wordFreq) && (!map.isEmpty()) && !wordFreq.isEmpty()) {
            return 0.0;
        }

        if (map == null || wordFreq.isEmpty() || map.isEmpty()) {
            return Math.PI / 2;
        }

        double dotProd = dotProduct(map);
        double lengthProd = euclideanNorm(map) * euclideanNorm();

        if (lengthProd == 0 || dotProd == 0) {
            return Math.PI / 2;
        }
        return Math.acos(dotProd / lengthProd);
    }

    /**
     * returns the Euclidean norm of a given map.
     * @param map the map to be compared
     * @return the Euclidean norm
     */
    private double euclideanNorm(final Map<String, BigInteger> map) {

        if (map == null || map.isEmpty()) {
            return 0.0;
        }
        BigInteger eucValSq = BigInteger.ZERO;
        for (BigInteger freq : map.values()) {
            eucValSq = eucValSq.add(freq.pow(2));
        }
        return Math.sqrt(eucValSq.doubleValue());
    }

    /**
     * returns the map of word frequencies.
     * @return the map of word frequencies
     */
    public Map<String, BigInteger> getMap() {
        return new HashMap<>(wordFreq);
    }
}


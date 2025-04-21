import java.util.HashSet;
import java.util.Set;

/**
 * 17683 Data Structures for Application Programmers.
 * Module 13 Hashing in Java, HashMap and HashSet
 *
 * A Simple HashSet demo
 *
 * @author Terry Lee
 */
public class HashSetDemo {

    /**
     * Simple program to demo HashSet.
     * @param args arguments
     */
    public static void main(String[] args) {
        Set<String> distinctWords = new HashSet<String>();
        String[] words = "coming together is a beginning keeping together is progress working together is success"
                .split(" ");

        for (String word : words) {
            // no duplicates allowed
            distinctWords.add(word);
        }

        System.out.println("There are " + distinctWords.size() + " distinct words.");
        System.out.println("They are " + distinctWords);

        // using enhanced for-loop
        for (String word : distinctWords) {
            System.out.println(word);
        }
    }

}

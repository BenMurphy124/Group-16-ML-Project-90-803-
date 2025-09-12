import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapDemo {

    public TreeMapDemo() {
        Map<String, Integer> freqOfWords = new HashMap<String, Integer>();
        String[] words = "coming together is a beginning keeping together is progress working together is success".split(" ");

        for (String word : words) {
            Integer frequency = freqOfWords.get(word);
            if(frequency == null) {
                frequency = 1;
            } else {
                frequency++;
            }
            freqOfWords.put(word, frequency);
        }

        TreeMap<String, Integer> sortedWords = new TreeMap<>(freqOfWords);
        System.out.println(sortedWords);
        System.out.println(sortedWords.descendingMap());
    }

    public static void main(String[] args) {
        new TreeMapDemo();
    }
}

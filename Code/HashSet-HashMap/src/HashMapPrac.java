import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapPrac {

    public static void main(String[] args) {
        // in this case, String is the key,
        // interger is the value, which is the frequency
        Map<String, Integer> freqOfWords = new HashMap<>(10, 0.65f);

        String[] words = "coming together is a beginning keeping together is progress working together is success"
                .split(" ");

        for (String word : words) {
            Integer frequency = freqOfWords.get(word);
            if (frequency == null) {
                frequency = 1;
            }else {
                frequency++;
            }
            freqOfWords.put(word, frequency);
        }

        System.out.println(freqOfWords);
        System.out.println(" ");

        Iterator<String> itr = freqOfWords.keySet().iterator();
        if (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println();

        for (Integer freq : freqOfWords.values()) {
            System.out.println(freq);
        }

        for (String key : freqOfWords.keySet()) {
            System.out.println(key);
        }

    }
}

import java.util.HashSet;
import java.util.Set;

public class HashSetPrac {

    public static void main(String[] args) {
        Set<String> distinctWords = new HashSet<>();
        String[] words = "coming together is a beginning".split(" ");

        for (String word : words) {
            distinctWords.add(word);
        }

        System.out.println(distinctWords.size());

        System.out.println(distinctWords);


    }
}

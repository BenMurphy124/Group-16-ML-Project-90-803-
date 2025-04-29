import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    public TreeSetDemo() {
        Set<String> distinctWords = new HashSet<>();

        String[] words = "coming together is a beginning keeping together is progress working together is success".split(" ");

        for (String word : words) {
            distinctWords.add(word);
        }

        System.out.println(distinctWords.size());
        System.out.println(distinctWords);

        TreeSet<String> sortedWords = new TreeSet<>(distinctWords);

        System.out.println(sortedWords);

    }


}

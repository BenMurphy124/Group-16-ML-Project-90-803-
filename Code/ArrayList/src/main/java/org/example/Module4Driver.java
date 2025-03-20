import java.util.ArrayList;
import java.util.List;

public class Module4Driver {

    public static void main(String[] args) {
        // Initialize an arraylist with initial length of 0
        List<Integer> numbers = new ArrayList<Integer>(0);
        // add numbers
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);

        // delete some numbers
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
        
        List<Integer> numbers2 = new ArrayList<Integer>();
        int size = 5000000;
        // addition as many as size
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < size; i++) { numbers2.add(i); }
        System.out.println("addition: " + timer1.elapsedTime() + " millisec");
        // removal as many as size
        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < size; i++) { numbers2.remove(numbers2.size()-1); }
        System.out.println("remove: " + timer2.elapsedTime() + " millisec");
    }

}

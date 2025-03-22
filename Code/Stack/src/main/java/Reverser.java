/**
 * 17683 Data Structures for Application Programmers.
 * Module 6 Stack
 *
 * Simple Application to reverse a String using Stack
 * @author Terry Lee
 */
public class Reverser {
    /**
     * Input string.
     */
    private String input;

    /**
     * Constructor with a specific input string.
     * @param str string to reverse
     */
    public Reverser(String str) {
        input = str;
    }

    /**
     * Performs reversal operation and returns a new string.
     * @return reversed string
     */
    public String doReverse() {
        ArrayStack<Character> theStack = new ArrayStack<>(input.length());
        for (int i = 0; i < input.length(); i++) {
            theStack.push(input.charAt(i));
        }

        // remember the lesson from lab 1
        StringBuilder output = new StringBuilder();
        while (!theStack.isEmpty()) {
            output.append(theStack.pop());
        }
        return output.toString();
    }
}

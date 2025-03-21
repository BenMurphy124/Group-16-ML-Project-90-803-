public class ReverserTest {
    private String input;

    public ReverserTest(String str) {
        input = str;
    }

    public String doReverse() {
        ArrayStackTest<Character> theStack= new ArrayStackTest<>(input.length());

        for (int i = 0; i < input.length(); i++) {
            theStack.push(input.charAt(i));
        }
        StringBuilder output = new StringBuilder();
        while (!theStack.isEmpty()) {
            output.append(theStack.pop());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        ReverserTest str1 = new ReverserTest("abcdefg");
        System.out.println("----------------");
        System.out.println(str1.doReverse());
}
}



/**
 * Andrew ID: zhixuanj.
 * @author Zhixuan Jiang
 */

public class MyArray {

    /** Default capacity constant to replace magic number. */
    private static final int DEFAULT_CAPACITY = 10;
    /** array of strings. */
    private String[] array;
    /** current size of array. */
    private int size;

    /**
     * constructs a new array without specifying parameter.
     * time complexity: O(1)
     */
    public MyArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * constructs a new array with specified initialCapacity parameter.
     * time complexity: O(1)
     * @param initialCapacity initial capacity of the array
     */
    public MyArray(final int initialCapacity) {
        array = new String[initialCapacity];
        size = 0;
    }

    /**
     * add a new word to the array.
     * time complexity: O(n) if the size reaches array.length
     * @param text new word
     */
    public void add(final String text) {
        if (text == null || !isValidText(text)) {
            return;
        }
        if (size == array.length) {
            doubleSize();
        }
        array[size] = text;
        size++;
    }

    /**
     * search a word in the array.
     * time complexity: O(n)
     * @param key the word needs to be searched
     * @return true when key was found, false otherwise
     */
    public boolean search(final String key) {
        if (key == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (key.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * return array's size.
     * time complexity: O(1)
     * @return array's size
     */
    public int size() {
        return size;
    }

    /**
     * return array's length.
     * time complexity: O(1)
     * @return array's length
     */
    public int getCapacity() {
        return array.length;
    }

    /**
     * print the words in the array.
     * time complexity: O(n)
     */
    public void display() {
        String results = "";
        for (int i = 0; i < size; i++) {
            results += array[i];
            if (i < size - 1) {
                results += " ";
            }
        }
        System.out.println(results);
    }

    /**
     * remove duplicate words in the array.
     * time complexity: O(n^2)
     */
    public void removeDups() {
        if (size <= 1) {
            return;
        }
        int uniqueText = 0;

        for (int i = 0; i < size; i++) {
            boolean isDuplicate = false;

            for (int j = 0; j < uniqueText; j++) {
                if (array[i].equals(array[j])) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                array[uniqueText] = array[i];
                uniqueText++;
            }
        }
        for (int i = uniqueText; i < size; i++) {
            array[i] = null;
        }

        size = uniqueText;
    }


    /**
     * check word if it is valid?
     * time complexity: O(n)
     * @param text the word needs to check
     * @return true if it's valid, otherwise false
     */
    public boolean isValidText(final String text) {
        if (text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return false;
            }
        }
        return true;
    }

    /**
     * double the size of array.
     * when the current length of array is 0, set new array's length to 1
     * time complexity: O(n)
     */
    public void doubleSize() {
        int newCapacity = array.length * 2;
        if (newCapacity == 0) {
            newCapacity = 1;
        }

        String[] tmp = new String[newCapacity];

        for (int i = 0; i < size; i++) {
            tmp[i] = array[i];
        }
        array = tmp;
    }
}

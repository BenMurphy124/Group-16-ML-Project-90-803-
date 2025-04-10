/**
 * @author Zhixuan Jiang
 * Andrew ID: zhixuanj
 */
public class SortedLinkedList implements MyListInterface {

    /**
     * reference to the head node.
     */
    private Node<String> head;
    /**
     * initial SortedLinkedList size.
     */
    private int size = 0;

    /**
     *  construct an empty list.
     */
    public SortedLinkedList() {
        head = null;
    }

    /**
     * construct a list with elements in array.
     * @param array the string array
     */
    public SortedLinkedList(final String[] array) {
        this();
        if (array != null) {
            addElementsRecur(array, 0);
        }
    }

    private void addElementsRecur(final String[] array, final int index) {
        if (index >= array.length) {
            return;
        }
        add(array[index]);

        addElementsRecur(array, index + 1);
    }


    @Override
    public void add(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        head = addRec(head, value);
    }

    private Node<String> addRec(Node<String> curr, String value) {
        if (curr == null || curr.word.compareToIgnoreCase(value) > 0) {
            size++;
            return new Node<>(value, curr);
        }
        if (curr.word.compareToIgnoreCase(value) == 0) {
            return curr;
        }
        curr.next = addRec(curr.next, value);
        return curr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        StringBuilder result = new StringBuilder("[");
        displayRec(head, result);
        result.append("]");
        System.out.println(result);
    }

    private void displayRec(Node<String> curr, StringBuilder result) {

        if (curr == null) {
            return;
        }
        result.append(curr.word);
        if (curr.next != null) {
            result.append(", ");
            displayRec(curr.next, result);
        }
    }

    @Override
    public boolean contains(String key) {
        return containRecursion(head, key);
    }

    private boolean containRecursion(Node<String> cur, String key) {
        if (cur == null) {
            return false;
        }
        if (cur.word.equalsIgnoreCase(key)) {
            return true;
        }
        return containRecursion(cur.next, key);
    }


    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public String removeFirst() {
        if (isEmpty()) {
            return null;
        }
        String temp = head.word;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public String removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Invalid index value");
        }
        if (index == 0) {
            return removeFirst();
        }

        Node<String> prev = findPrevRecur(head, index - 1);
        if (prev == null || prev.next == null) {
            throw new RuntimeException("Invalid index value");
        }

        Node<String> toRemove = prev.next;
        prev.next = toRemove.next;
        size--;
        return toRemove.word;
    }

    private Node<String> findPrevRecur(Node<String> node, int index) {
        if (index == 0) {
            return node;
        }
        if (node == null || node.next == null) {
            return null;
        }
        return findPrevRecur(node.next, index - 1);
    }

    /**
     * Static nested class for Node.
     * @param <String> the data type is String
     */
    private static class Node<String> {
        /**
         * word in the node.
         */
        private String word;
        /**
         * reference to the next node.
         */
        private Node<String> next;

        Node(String newWord, Node<String> newNext) {
            word = newWord;
            next = newNext;
        }

    }

}

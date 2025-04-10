public class SortedLinkedList1 implements MyListInterface {

    private Node<String> head;

    private int size = 0;

    @Override
    public void add(String value) {
        if (value == null || isEmpty()) {
            return;
        }
        head = addRec(head, value);
    }

    private Node<String> addRec(Node<String> curr, String value) {
        if (curr == null || value.compareToIgnoreCase(curr.word) < 0) {
            size++;
            return new Node<>(value, curr);
        }
        if (value.compareToIgnoreCase(curr.word) == 0) {
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
        StringBuilder sb = new StringBuilder("[");
        displayRec(sb, head);
        sb.append("]");
        System.out.println(sb);
    }

    private void displayRec(StringBuilder sb, Node<String> curr) {
        if (curr == null) {
            return;
        }
        sb.append(curr.word);
        if (curr.next != null) {
            sb.append(", ");
        }
        displayRec(sb, curr.next);
    }

    @Override
    public boolean contains(String key) {
        return containsRec(head, key);
    }

    private boolean containsRec(Node<String> curr, String key) {
        if (curr == null) {
            return false;
        }
        if (curr.word.equalsIgnoreCase(key)) {
            return true;
        }
        return containsRec(curr.next, key);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String removeFirst() {
        String temp = head.word;
        head = head.next;
        return temp;
    }

    @Override
    public String removeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<String> prev = findPrevRec(head, index, 0);
        String temp = prev.word;
        prev.next = prev.next.next;
        size--;
        return temp;
    }

    private Node<String> findPrevRec(Node<String> curr, int index, int curPos) {
        if (curPos == index - 1) {
            return curr;
        }
        return findPrevRec(curr.next, index, curPos + 1);
    }

    private static class Node<String> {
        private String word;
        private Node<String> next;

        Node(String newWord, Node<String> newNext) {
            word = newWord;
            next = newNext;
        }
    }

}

public class SortedLinkedList implements MyListInterface {

    private Node<String> head;

    public int size = 0;


    public SortedLinkedList() {
        head = null;
    }

    public SortedLinkedList(String[] array) {
        this(); // 调用默认构造器，初始化head为null
        if (array != null) {
            for (String s : array) {
                add(s); // 利用已有的add方法，保证升序+去重
            }
        }
    }


    @Override
    public void add(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        Node<String> newNode = new Node<>(value, null);

        if (head == null || head.word.compareToIgnoreCase(value) > 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        Node<String> temp = head;
        while (temp.next != null && temp.next.word.compareToIgnoreCase(value) < 0) {
            temp = temp.next;
        }

        // avoid duplicate
        if (temp.word.equalsIgnoreCase(value) || (temp.next != null && temp.next.word.equalsIgnoreCase(value))) {
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        StringBuilder result = new StringBuilder("[");

        Node<String> temp = head;
        while (temp != null) {
            result.append(temp.word);
            if (temp.next != null) {
                result.append(",");
            }
            temp = temp.next;
        }
        result.append("]");
        System.out.println(result);
    }

    @Override
    public boolean contains(String key) {
        Node<String> temp = head;
        for (int i = 0; i < size; i++) {
            if(temp.word.equalsIgnoreCase(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
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
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }

        Node<String> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node<String> toRemove = prev.next;
        prev.next = toRemove.next;
        size--;
        return toRemove.word;
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
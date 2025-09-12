package org.example;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLL<AT> implements Iterable<AT> {
    private Node<AT> head;

    public SLL() {
        head = null;
    }

    public void addFirst(AT item) {
        head = new Node<>(item, head);
    }

    public void addLast(AT item) {
        if (head == null) {
            addFirst(item);
        }
        Node<AT> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node<>(item, null);
    }

    public void insertAfter(AT item, AT key) {
        if (head == null) {
            return;
        }

        Node<AT> tmp = head;
        while (tmp != null && !tmp.data.equals(key)) {
            tmp = tmp.next;
        }
        if (tmp != null) {
            tmp.next = new Node<>(item, tmp.next);
        }
    }

    public void insertBefore(AT key, AT item) {
        if (head == null) {
            return;
        }
        if (head.data.equals(key)) {
            addFirst(item);
            return;
        }
        Node<AT> prev = null;
        Node<AT> curr = head;

        while (curr != null && !curr.data.equals(key)) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            prev.next = new Node<>(item, curr);
        }
    }

    public void remove(AT key) {
        if (head == null) {
            return;
        }
        if (head.data.equals(key)) {
            head = head.next;
            return;
        }
        Node<AT> prev = null;
        Node<AT> curr = head;

        while (curr != null && !curr.data.equals(key)) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            prev.next = curr.next;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<AT> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AT> {

        private Node<AT> nextNode;

        LinkedListIterator() {
            this.nextNode = head;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public AT next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AT result = nextNode.data;
            nextNode = nextNode.next;
            return result;
        }
    }

    private class Node<AT> {
        private AT data;
        private Node next;

        Node(AT newData, Node newNext) {
            data = newData;
            next = newNext;
        }
    }
}

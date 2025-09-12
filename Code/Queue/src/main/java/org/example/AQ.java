package org.example;

import java.util.NoSuchElementException;

public class AQ<AT> implements QueueInterface<AT> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int front;

    private int back;

    private int nItems;

    public AQ() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1;
        nItems = 0;
    }

    /**
     * Enqueues a new item to the back of the queue in O(1).
     *
     * @param item a new item to add
     * @throws RuntimeException indicates that queue is full
     */
    @Override
    public void enqueue(AT item) {

        if (nItems == elements.length) {
            throw new RuntimeException("queen is full");
        }
        back++;
        elements[back % elements.length] = item;
        nItems++;
    }

    /**
     * Gets and deletes an item from the front of the queue in O(1).
     *
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    @Override
    public AT dequeue() {
        if (isEmpty()) {
            throw new  NoSuchElementException("queue is empty");
        }
        int index = front % elements.length;
        @SuppressWarnings("unchecked")
        AT result = (AT) elements[index];
        elements[front] = null;
        front++;
        nItems--;
        return result;
    }

    /**
     * Gets an item from the front of the queue in O(1) but does not delete it.
     *
     * @return the first item in the queue
     * @throws NoSuchElementException indicates that queue is empty
     */
    @Override
    public AT peekFront() {
        if (isEmpty()) {
            throw new  NoSuchElementException("queue is empty");
        }
        int index = front % elements.length;
        @SuppressWarnings("unchecked")
        AT result = (AT) elements[index];
        return result;
    }

    /**
     * Checks if the queue is empty or not in O(1).
     *
     * @return true if the queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return nItems == 0;
    }
}

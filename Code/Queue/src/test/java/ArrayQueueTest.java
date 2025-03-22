import java.util.NoSuchElementException;

import org.example.ArrayQueue;

public class ArrayQueueTest<T> implements QueueInterfaceTest<T>  {
    private final static int DEFAULT_CAPACITY = 10;

    public int front;

    public int back;

    public Object[] elements;

    public int nItems;

    /**
     * Constructor non-arg.
     */
    public ArrayQueueTest() {
        elements = new Object[DEFAULT_CAPACITY];
        front = 0;
        back = -1;
        nItems = 0;
    }


    /**
     * Implement abstract method.
     */

    @Override
    public void enqueue(T item) {
        if(isFull()) {
            throw new RuntimeException("Queue is full");
        }
        back++;
        elements[back % elements.length] = item;
        nItems++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }

        T item = (T) elements[front % elements.length];
        elements[front % elements.length] = null;
        front++;
        nItems--;
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        return (T) elements[front % elements.length];
    }

    @Override
    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == elements.length;
    }

}

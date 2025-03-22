public interface  QueueInterfaceTest<T> {
    void enqueue(T item);

    T dequeue();

    boolean isEmpty();

    T peekFront();
}

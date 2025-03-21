public class AS1<AT> implements StackInterfaceTest<AT>{
    public static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int top;

    public AS1() {
        this(DEFAULT_CAPACITY);
    }

    public AS1(int initialCapacity) {
        if (initialCapacity <= 0) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            elements = new Object[initialCapacity];
        }
        top = -1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(AT item) {
        if (isEmpty()) {
            throw new StackException("empty");
        }
        ++top;
        elements[top] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AT peek() {
        if (isEmpty()) {
            throw new StackException("empty");
        }
        return (AT) elements[top];
    }

    @SuppressWarnings("unchecked")
    @Override
    public AT pop() {
        if (isEmpty()) {
            throw new StackException("empty");
        }
        AT item = (AT) elements[top];
        elements[top] = null;
        --top;
        return item;
    }
}



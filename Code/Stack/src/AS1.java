public class AS1<AT> implements StackInterface{

    public static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int top;

    public AS1(int initialCapacity) {
        if (initialCapacity <= DEFAULT_CAPACITY) {
            elements = new Object[DEFAULT_CAPACITY];
        }
        if (initialCapacity > DEFAULT_CAPACITY) {
            elements = new Object[initialCapacity];
        }
        top = -1;
    }

    public AS1() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Pushes a new item onto the top of the stack in O(1).
     *
     * @param item a new item to add
     * @throws StackException if stack is full
     */
    @Override
    public void push(Object item) {
        if (top == elements.length - 1) {
            throw new StackException("stack is full");
        }
        top++;
        elements[top] = item;
    }

    /**
     * Gets and removes the item on the top in O(1).
     *
     * @return Top item
     * @throws StackException indicates that stack is empty
     */
    @Override
    public AT pop() {
        if (isEmpty()) {
            throw new StackException("stack is empty");
        }
        @SuppressWarnings("unchecked")
        AT tmp = (AT) elements[top];
        elements[top] = null;
        top--;
        return tmp;
    }

    /**
     * Gets the item on the top but does NOT remove it in O(1).
     *
     * @return Top item
     * @throws StackException indicates that stack is empty
     */
    @SuppressWarnings("unchecked")
    @Override
    public AT peek() {
        if (isEmpty()) {
            throw new StackException("stack is empty");
        }
        return (AT) elements[top];
    }

    /**
     * Checks if the stack is empty or not in O(1).
     *
     * @return true if it is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}

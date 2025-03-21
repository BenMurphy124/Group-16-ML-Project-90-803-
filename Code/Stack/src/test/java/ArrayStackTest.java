public class ArrayStackTest<AT> implements StackInterfaceTest<AT> {

    private Object[] elements;
    private int top;
    public static final int DEFAULT_CAPACITY = 10;

    public ArrayStackTest(int initialCapacity) {
        if (initialCapacity <= 0) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            elements = new Object[initialCapacity];
        }
        top = -1;
    }

    public ArrayStackTest() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(AT item) {
        if (top == elements.length - 1) {
            throw new StackException("the stack is full");
        }
        top++;
        elements[top] = item;
    }

    public AT pop() {
        if (isEmpty()) {
            throw new StackException("stack is empty");
        }
        @SuppressWarnings("unchecked")
        AT item = (AT) elements[top];
        elements[top] = null;
        top--;
        return item;
    }

    @Override
    public AT peek() {
        if (isEmpty()) {
            throw new StackException("the stack is empty");
        }
        @SuppressWarnings("unchecked")
        AT item = (AT) elements[top];
        return item;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(6);
        stack.push(7);
        stack.push(9);
        stack.push(11);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(15);
        stack.push(27);
        stack.push(78);
        stack.push(9);
        System.out.println(stack.peek());
    }
}

class StackException extends RuntimeException {
    StackException() {
        super();
    }

    StackException(String message) {
        super(message);
    }
}


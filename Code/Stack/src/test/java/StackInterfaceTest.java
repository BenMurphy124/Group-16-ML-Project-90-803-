/**
 * @param <AT> type of element objects
 */
public interface StackInterfaceTest<AT> {
    
    /**
     * @param item an item to add
     */
    void push(AT item);

    AT pop();

    AT peek();

    boolean isEmpty();
}

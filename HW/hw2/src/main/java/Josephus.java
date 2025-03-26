import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework Assignment 2 Solve Josephus problem using different data structures
 * and different algorithms and compare running times.
 *
 * Andrew ID: zhixuanj
 * @author Zhixuan Jiang
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(final int size, final int rotation) {
    // TODO your implementation here

        if (size <= 0) {
            throw new IllegalArgumentException("Size of array needs to be greater than 0");
        }
        if (rotation <= 0) {
            throw new IllegalArgumentException("Rotation needs to be greater than 0");
        }

        ArrayDeque<Integer> aD = new ArrayDeque<>(size);

        for (int i = 1; i <= size; i++) {
            aD.add(i);
        }

        if (aD.size() == 1) {
            return aD.peek();
        }

        while (aD.size() != 1) {
            for (int j = 0; j < rotation - 1; j++) {
                aD.addLast(aD.removeFirst());
            }
            aD.removeFirst();
        }
        return aD.peek();

    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(final int size, final int rotation) {
        // TODO your implementation here

        if (size <= 0) {
            throw new IllegalArgumentException("Size of array needs to be greater than 0");
        }
        if (rotation <= 0) {
            throw new IllegalArgumentException("Rotation needs to be greater than 0");
        }

        LinkedList<Integer> lL = new LinkedList<>();

        for (int i = 1; i <= size; i++) {
            lL.add(i);
        }
        if (lL.size() == 1) {
            return lL.peek();
        }

        while (lL.size() != 1) {
            for (int j = 0; j < rotation - 1; j++) {
                lL.addLast(lL.removeFirst());
            }
            lL.removeFirst();
        }
        return lL.peek();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     *
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That is, use the index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours or ask questions on Piazza.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */


    public int playWithLLAt(final int size, final int rotation) {

    if (size <= 0) {
        throw new IllegalArgumentException("Size of array needs to be greater than 0");
    }
    if (rotation <= 0) {
        throw new IllegalArgumentException("Rotation needs to be greater than 0");
    }

    LinkedList<Integer> llat = new LinkedList<>();

    for (int i = 1; i <= size; i++) {
        llat.add(i);
    }

    if (llat.size() == 1) {
        return llat.get(0);
    }

    int curIndex = 0;

    while (llat.size() != 1) {
        curIndex = (curIndex + rotation - 1) % llat.size();
        llat.remove(curIndex);
    }

    return llat.get(0);
}


}

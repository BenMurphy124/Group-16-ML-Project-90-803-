import java.util.Arrays;

/**
 * 17683 Data Structures for Application Programmers.
 * Module 8 Simple Sorting Algorithms
 *
 * Bubble Sort, Selection Sort, and Insertion Sort
 *
 * Understanding simple sorting algorithms is worthwhile:
 * 1. help us work out the ground rules.
 * 2. provide performance benchmarks.
 * 3. often are the method of choice in some specialized situations.
 * 4. can serve as the basis for developing better algorithms.
 *
 * @author Terry Lee
 */
public class SimpleSorting {

    /**
     * Bubble sort runs in O(n^2) in the worst-case
     * Simple but slow.
     * Focus on the largest value!
     * @param data an array of int to sort
     */
    public static void bubbleSort(int[] data) {
        // move backward from the last index
        for (int out = data.length - 1; out >= 1; out--) {
            // move forward from the 0th index
            // bubble up the largest value to the right
            for (int in = 0; in < out; in++) {
                if (data[in] > data[in + 1]) {
                    swap(data, in, in + 1);
                }
            }
        }
    }

    public static void bSort(int[] data) {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    public static void swap1(int[] data, int index1, int index2) {
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    /**
     * Selection sort runs in O(n^2) too.
     * Focus on the smallest value!
     * Faster than bubble sort mainly due to less number of swaps.
     * @param data an array of int to sort
     */
    public static void selectionSort(int[] data) {
        int min;
        for (int out = 0; out < data.length - 1; out++) {
            // find the smallest value among the remaining values
            // set the min index initially as the out index
            min = out;
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;
                }
            }
            // swap the min value with out index's value
            // notice this happens outside the inner loop
            if (out != min) {
                swap(data, out, min);
            }
        }
    }

    public static void selSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] > data[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap1(data, i, min);
            }
        }
    }


    /**
     * Insertion sort runs in O(n^2) in the worst case.
     * But its best case running time complexity is O(n).
     * It is the fastest among the three.
     * Sensitive to the input values.
     *
     * Less number of comparisons on average.
     * Uses shifting (copying) instead of swapping (one swap equals to three copies).
     * @param data an array of int to sort
     */
    public static void insertionSort(int[] data) {
        // start from the 1st index till the last index
        for (int out = 1; out < data.length; out++) {
            int tmp = data[out]; // store the value temporarily
            int in = out; // initially set the in variable to be the same as out

            /* loop backward to check the sorted section
             * but not necessarily all the way to the 0th
             * On average, go halfway through the sorted section
             */
            while (in > 0 && data[in - 1] >= tmp) {
                data[in] = data[in - 1]; // shift to right
                in--;
            }
            // finally, INSERT the tmp value into the right position of the sorted section
            if (out != in) {
                data[in] = tmp;
            }
        }
    }

    public static void insertionSort1(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int tmp = data[i];
            int j = i;
            while (j > 0 && data[j - 1] >= tmp) {
                data[j] = data[j - 1];
                j--;
            }
            if (j != i) {
                data[j] = tmp;
            }
        }
    }

    /**
     * helper method to swap two values in an array.
     * one swap requires three copies.
     * @param data an array to update
     * @param one index of one value
     * @param two index of the other value
     */
    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
    }

    /**
     * Simple test program with int arrays.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] a = { 4, 7, 2, 5, 3 };
        int[] b = a.clone();
        int[] c = a.clone();

        System.out.println("Before sort (bubble sort): " + Arrays.toString(a));
        bubbleSort(a);
        System.out.println("After sort (bubble sort): " + Arrays.toString(a));

        System.out.println("Before sort (selection sort): " + Arrays.toString(b));
        selectionSort(b);
        System.out.println("After sort (selection sort): " + Arrays.toString(b));

        System.out.println("Before sort (insertion sort): " + Arrays.toString(c));
        insertionSort(c);
        System.out.println("After sort (insertion sort): " + Arrays.toString(c));
    }

}

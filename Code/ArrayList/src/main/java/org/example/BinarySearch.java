package org.example;
/**
 * 17683 Data Structures for Application Programmers.
 * A Simple Binary Search Implementation.
 * @author Terry Lee
 */
public class BinarySearch {

    /**
     * A simple binary search in an array that is not null.
     * @param data int array to search
     * @param key int key value to search for
     * @return index of the key value if found, -1 if not found
     */
    public static int binarySearch(int[] data, int key) {
        int lowerBound = 0;
        int upperBound = data.length - 1;
        int mid;

        while (true) {
            // not found
            if (lowerBound > upperBound) {
                return -1;
            }

            mid = lowerBound + (upperBound - lowerBound) / 2;
            // found
            if (data[mid] == key) {
                return mid;
            }

            if (data[mid] < key) {
                // go to right
                lowerBound = mid + 1;
            } else {
                // go to left
                upperBound = mid - 1;
            }
        }
    }

    public static int BS(int[] data, int key) {
        int lb = 0;
        int ub = data.length - 1;
        int mid = lb + (ub - lb) / 2;

        while (true) {
            if (key == data[mid]) {
                return mid;
            }

            if (data[mid] < key) {
                lb = mid + 1;
            }else {
                ub = mid - 1;
            }
        }

    }

    //recursion method
    public static int BSRec(int[] data, int key) {
        return BSRecHelper(data, key, 0, data.length - 1);
    }

    private static int BSRecHelper(int[] data, int key, int lb, int ub) {
        if (lb > ub) {
            return -1;
        }

        int mid = lb + (ub - lb) / 2;

        if (key == data[mid]) {
            return mid;
        }

        if (key < data[mid]) {
            return BSRecHelper(data, key, lb, mid - 1);
        }else {
            return BSRecHelper(data, key, mid + 1, ub);
        }
    }



    /**
     * Simple test program to run binary search algorithm.
     * @param args arguments
     */
    public static void main(String[] args) {
        int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(binarySearch(data, 6));
    }

}

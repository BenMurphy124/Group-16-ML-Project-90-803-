public class QSPrac {

    private static final int SIZE = 10000;

    public static void quickSort(int[] unsorted) {
        quickSort(unsorted, 0, unsorted.length - 1);
    }

    private static void quickSort(int[] unsorted, int left, int right) {
        if (left >= right) return;

        int pivot = unsorted[right];

        int partition = partition(unsorted, 0, right, pivot);

        quickSort(unsorted, left, partition - 1);
        quickSort(unsorted, partition + 1, right);



    }

    private static int partition(int[] arr, int left, int right, int pivot) {

        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {
            while (arr[++leftPointer] < pivot);
            while (rightPointer> left && arr[--rightPointer] > pivot);

            if (leftPointer >= rightPointer) {
                break;
            }

            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, pivot);
        return leftPointer;
    }


        /**
     * Helper method to swap two elements in the input array.
     * @param data input array to update
     * @param one one index
     * @param two the other index
     */
    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
    }

    /**
     * A simple debugging program to check if array is sorted.
     * @param array array to check
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array) {
        return isSorted(array, 0, array.length - 1);
    }

    /**
     * Helper method to check if array is sorted.
     * @param array array to check
     * @param lo low boundary
     * @param hi high boundary
     * @return true if sorted and false if not
     */
    private static boolean isSorted(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

}

public class MSPrac {
    private static final int SIZE = 10000;

    public static int[] mergeSort(int[] unsorted) {
        if (unsorted.length <= 1) {
            return unsorted;
        }

        int mid = unsorted.length / 2;
        int[] left = new int[mid];
        int[] right = new int[unsorted.length - mid];

        System.arraycopy(unsorted, 0, left, 0, mid);
        System.arraycopy(unsorted, mid, right, 0, right.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int leftPointer = 0;
        int rightPointer = 0;
        int indexMerge = 0;

        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] < right[rightPointer]) {
                merged[indexMerge] = left[leftPointer];
                leftPointer++;
            } else {
                merged[indexMerge] = right[rightPointer];
                rightPointer++;
            }
            indexMerge++;
        }
        if (leftPointer < left.length) {
            for (int i = leftPointer; i < left.length; i++) {
                merged[indexMerge] = left[i];
                indexMerge++;
            }
        } else if (rightPointer < right.length) {
            for (int i = rightPointer; i < right.length; i++) {
                merged[indexMerge] = right[i];
                indexMerge++;
            }
        }
        return merged;
    }




}

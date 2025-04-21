public class MSPrac {

    private static final int SIZE = 10000;

    public static int[] mergeSort(int[] unsorted) {

        if (unsorted.length <= 1) {
            return unsorted;
        }

        int mid = unsorted.length / 2;

        int[] left = new int[mid];
        System.arraycopy(unsorted, 0, left, 0, mid);

        int[] right = new int[unsorted.length - mid];
        System.arraycopy(unsorted, mid, right, 0, right.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }


    private static int[] merge(int[] left, int[] right) {

        int[] merged = new int[left.length + right.length];

        int indexRight = 0;
        int indexLeft = 0;
        int indexMerged = 0;

        while (indexRight < right.length && indexLeft < left.length) {

            if (left[indexLeft] < right[indexRight]) {
                merged[indexMerged] = left[indexLeft];
                indexLeft++;
            } else {
                merged[indexMerged] = right[indexRight];
                indexRight++;
            }
            indexMerged++;
        }

        if (indexLeft < left.length) {
            for (int i = indexLeft; i < left.length; i++) {
                merged[indexMerged] = left[i];
                indexLeft++;
                indexMerged++;
            }
        } else {
            for (int i = indexRight; i < right.length; i++) {
                merged[indexMerged] = right[i];
                indexRight++;
                indexMerged++;
            }
        }
        return merged;
    }




}

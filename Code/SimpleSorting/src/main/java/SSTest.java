public class SSTest{

    public static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void bubbleSort(int[] data) {
        for (int out = data.length - 1; out > 0 ; out--) {
            for (int in = 0; in < out; in++) {
                if (data[in] > data[in + 1]) {
                    swap(data, in, in + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] data) {
        for (int out = 0; out < data.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;
                }
            }
            swap(data, min, out);
        }
    }

    public static void insertionSort(int[] data) {
        for (int out = 1; out < data.length; out++) {
            int temp = data[out];
            int in = out;
            while (in >= 0 && data[in - 1] > temp) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    /**
     * insertionSort.
     * */

    public static void insertionSort(int[] data) {
        for (int out = ; out < ; out++) {

        }
    }


    /*
    public static void swap(int[] data, int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }

    public static void bubbleSort(int[] data) {
        for (int out = data.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (data[in] > data[in + 1]) {
                    swap(data, in, in + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] data) {
        for (int out = 0; out < data.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;
                }
            }
            swap(data, min, out);
        }
    }

    public static void insertionSort(int[] data) {
        for (int out = 1; out < data.length; out++) {
            int tmp = data[out];
            int in = out;
            while (in > 0 && data[in - 1] > tmp) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = tmp;
        }


     */
}
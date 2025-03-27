
public class SSTest {


    /**
     * BubbleSort
     * */
    public static void bubbleSort(int[] data) {
        for (int out = data.length - 1; out >= 1; out--) {
//            当out = 1时，内层循环为0和1的比较，这也是最后一次比较，这次以后，0变成最小值
//            当out = 0时， 内层循环只有0和0， 无需比较
            for (int in = 0; in < out; in++) {
                if (data[in+1] <data[in]) {
                    swap(data, in+1, in);
                }
            }
        }
    }

    /**
     * SlectionSort
     * */
    public static void selectionSort(int[] data) {
        for (int out = 0; out < data.length - 1; out++) {
            int min = out;
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;
                }
            }
            if (out != min) {
                swap(data, out, min);
            }
        }
    }

    /**
     * insertionSort.
     * */

    public static void insertionSort(int[] data) {
        for (int out = ; out < ; out++) {

        }
    }




    /**
     * swap method swap the element in index a with element in index b.
     * */
    public static void swap(int[] data, int a, int b){
            int temp = data[a];
            data[a] = data[b];
            data[b] = temp;
        }
}

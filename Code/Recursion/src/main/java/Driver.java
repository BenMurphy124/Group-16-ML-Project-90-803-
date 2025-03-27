public class Driver {

    public static int sum(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static int recSum(int n) {
        int result = 0;
        if (n == 1) {
            return n;
        }
        return n + recSum(n - 1);
    }

     public static int binarySearch(int[] data, int key) {
        int lb = 0;
        int ub = data.length - 1;
        int mid = (lb + ub) / 2;
        while (true) {

            if (lb > ub) {
                return -1;
            }

            if (data[mid] == key) {
                return mid;
            }

            if (data[mid] < key) {
                lb = mid + 1;
            }else {
                ub = mid - 1;
            }
        }
     }

     public static int find(int[] data, int key) {
        return find(data, key, 0, data.length - 1);
     }


     private static int find(int[] data, int key, int lb, int ub) {
        if (lb > ub) {
            return -1;
        }

        int mid = (ub + lb) / 2;

        if (data[mid] == key) {
            return mid;
        }

        if (data[mid] < key) {
            return find(data, key, mid + 1, ub);
        }else {
            return find(data, key, lb, mid - 1);
        }

     }



    public static void main(String[] args) {
        System.out.println(recSum(5));
        System.out.println(sum(5));
    }




}

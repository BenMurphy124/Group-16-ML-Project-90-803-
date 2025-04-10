public class HT implements HashTableInterface{

    private DataItem[] hashArray;
    private static final DataItem DELETED = new DataItem(-1);

    public HT(int INITIAL_CAPACITY) {
        hashArray = new DataItem[INITIAL_CAPACITY];
    }

    private int hashFunc(int key) {
        return key % hashArray.length;
    }

    @Override
    public boolean search(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key == key) {
                return true;
            }
            hashVal++;
            hashVal = hashVal % hashArray.length;
        }
        return false;
    }

    @Override
    public int delete(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key == key) {
                int temp = hashArray[hashVal].key;
                hashArray[hashVal] = DELETED;
                return temp;
            }
            hashVal++;
            hashVal = hashVal % hashArray.length;
        }
        return -1;
    }

    @Override
    public void insert(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
            hashVal++;
            hashVal = hashVal % hashArray.length;
        }
        DataItem tmp = new DataItem(key);
        hashArray[hashVal] = tmp;
    }

    private static class DataItem {
        private int key;
        DataItem(int k) {
            key = k;
        }
    }


}

public class HT implements HashTableInterface {

    private static final DataItem DELETED = new DataItem(-1);

    private DataItem[] hashArray;

    public HT(int initialCapacity) {
        hashArray = new DataItem[initialCapacity];
    }

    /**
     * Returns true when the key is found.
     *
     * @param key int key to search for
     * @return boolean true if found, false not found
     */
    @Override
    public boolean search(int key) {
        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key == key) {
                return true;
            }
            hashVal++;
            hashVal %= hashArray.length;
        }
        return false;
    }

    /**
     * Deletes and returns the int key found in the table.
     *
     * @param key int key to delete
     * @return deleted int from the table (if not found, -1)
     */
    @Override
    public int delete(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key == key) {
                hashArray[hashVal] = DELETED;
                return hashArray[hashVal].key;
            }
            hashVal++;
            hashVal %= hashArray.length;
        }
        return -1;
    }

    /**
     * Inserts a positive int key to the table.
     *
     * @param key int key to insert
     */
    @Override
    public void insert(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
            if (hashArray[hashVal].key == key) {
                return;
            }
            hashVal++;
            hashVal %= hashArray.length;
        }
        hashArray[hashVal] = new DataItem(key);
    }

    public int hashFunc(int key) {
        return key % hashArray.length;
    }

    private static class DataItem{
        private int key;

        DataItem(int k) {
            key = k;
        }
    }


}

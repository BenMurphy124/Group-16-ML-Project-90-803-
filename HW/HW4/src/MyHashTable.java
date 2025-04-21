/**
 * 17-683 Data Structures for Application Programmers.
 * Homework Assignment 4: HashTable Implementation with linear probing.
 *
 * Andrew ID: zhixuanj
 * @author Zhixuan Jiang
 */
public class MyHashTable implements MyHTInterface {

    /**
     * no-arg constructor initial size.
     */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * load factor is the ratio of the number of data items
     * (not including deleted flags)
     * in a hash table to the length of the array.
     */
    private static final double ALPHA = 0.5;

    /**
     * count the current number of items which are not null or being deleted.
     */
    private int size = 0;

    /**
     * default collision time is 0.
     */
    private int colissionTimes = 0;

    /**
     * A special marker to indicate a deleted item in the hash table.
     * This sentinel value helps distinguish between an empty slot.
     * and a deleted slot during probing.
     */
    private static final DataItem DEL = new DataItem("#DEL#", 0);


    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;

    /**
     * Constructs a hash table with the specified initial size.
     *
     * @param tableSize the initial capacity of the hash table
     * @throws RuntimeException if the specified size is less than or
     * equal to zero
     */
    public MyHashTable(final int tableSize) {
        if (tableSize <= 0) {
            throw new RuntimeException("invalid table size.");
        }
        hashArray = new DataItem[tableSize];
    }

    /**
     * Default constructor.
     */
    public MyHashTable() {
        hashArray = new DataItem[INITIAL_CAPACITY];
    }

    /**
     * Checks if the input string is a valid word.
     * A valid word consists of lowercase English letters only.
     *
     * @param input the string to check
     * @return true if the input is a valid word, false otherwise
     */
    private boolean isWord(final String input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 'a' || input.charAt(i) > 'z') {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of items in the hash table.
     * @return the number of items in the hash table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Displays the contents of the hash table.
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] == null) {
                sb.append("**");
            } else if (hashArray[i] == DEL) {
                sb.append("#DEL#");
            } else {
                sb.append("[").append(hashArray[i].value).append(", ").append(hashArray[i].frequency).append("]");
            }
            if (i < hashArray.length - 1) {
                    sb.append(" ");
                }
        }
        System.out.println(sb);
    }

    /**
     * Checks if the hash table contains the specified key.
     * This method calculates the hash value of the key using the hash function,
     * then uses linear probing to search for the key in the hash table.
     * Returns true if a matching non-deleted element is found,
     * otherwise returns false.
     *
     * @param key the string key to check for
     * @return true if the hash table contains the key, false otherwise
     *         Also returns false if the provided key is null
     * @see #hashFunc(String)
     */
    @Override
    public boolean contains(final String key) {

        int count = 0;
        if (key == null) {
            return false;
        }

        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null && count < hashArray.length) {
            if (hashArray[hashVal] != DEL && hashArray[hashVal].value.equals(key)) {
                return true;
            }
            hashVal++;
            count++;
            hashVal = hashVal % hashArray.length;
        }
        return false;
    }

    /**
     * Returns the number of collisions that occurred during insertion.
     * @return the number of collisions
     */
    @Override
    public int numOfCollisions() {
        return colissionTimes;
    }

    /**
     * Returns the hash value of the specified string.
     * This method uses the hash function to compute the hash value
     * of the input string.
     *
     * @param value the string for which to compute the hash value
     * @return the hash value of the input string
     */
    @Override
    public int hashValue(final String value) {
        return hashFunc(value);
    }

    /**
     * Returns the frequency of the specified key in the hash table.
     * This method uses linear probing to search for the key in the hash table.
     * If a matching non-deleted element is found, its frequency is returned.
     * Otherwise, 0 is returned.
     *
     * @param key the string key whose frequency to retrieve
     * @return the frequency of the key, or 0 if not found
     */
    @Override
    public int showFrequency(final String key) {

        if (!isWord(key)) {
            return 0;
        }

        int hashVal = hashFunc(key);
        int count = 0;

        while (hashArray[hashVal] != null
                && count < hashArray.length) {
            if (hashArray[hashVal] != DEL && hashArray[hashVal].value.equals(key)) {
                return hashArray[hashVal].frequency;
            }
            hashVal++;
            count++;
            hashVal = hashVal % hashArray.length;
        }
        return 0;
    }

    /**
     * Removes the specified key from the hash table.
     * This method uses linear probing to search for the key in the hash table.
     * If a matching non-deleted element is found, it is marked as deleted.
     * The method returns the removed key if successful, or null if not found.
     *
     * @param key the string key to remove
     * @return the removed key, or null if not found
     */
    @Override
    public String remove(final String key) {

        if (!isWord(key)) {
            return null;
        }

        int hashVal = hashFunc(key);
        int orginVal = hashVal;
        int count = 0;

        while (hashArray[hashVal] != null && count < hashArray.length) {
            if (hashArray[hashVal] != DEL && hashArray[hashVal].value.equals(key)) {
                hashArray[hashVal] = DEL;
                size--;
                return key;
            }
            hashVal++;
            count++;
            hashVal = hashVal % hashArray.length;
        }
        return null;
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be
     * applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(final String input) {

        int length = input.length();
        int iniHashVal = 0;
        final int base = 27;

        for (int i = 0; i < length; i++) {
            int charVal = input.charAt(i) - 'a' + 1;
            iniHashVal = ((iniHashVal * base + charVal) % hashArray.length);
        }
        return iniHashVal;
    }

    /**
     * Inserts a new string into the hash table.
     * If the string is already present, its frequency is incremented.
     * If the string is null or not a valid word, it is ignored.
     *
     * @param value the string to insert
     */
    @Override
    public void insert(final String value) {
        if (!isWord(value)) {
            return;
        }

        int hashVal = hashFunc(value);
        int originHash = hashVal;
        int count = 0;
        int firstDelItem = -1;
        boolean isCollide = false;

        // find insertion position
        while (hashArray[hashVal] != null && count != hashArray.length) {

            if (hashArray[hashVal] == DEL) {
                if (firstDelItem == -1) {
                    // tag original index as del item pos.
                    firstDelItem = hashVal;
                }
                // if current item not being deleted, check if it is the same
            } else {
                if (hashArray[hashVal].value.equals(value)) {
                    hashArray[hashVal].frequency++;
                    return;
                } else {
                    // not the same value, but has same hashFunc value, collide
                    if (hashFunc(hashArray[hashVal].value) == originHash) {
                        isCollide = true;
                    }
                }
            }
            // wrap around
            hashVal++;
            count++;
            hashVal = hashVal % hashArray.length;
        }

        if (isCollide) {
            colissionTimes++;
        }

        /*
         * while loop end, found null item.
         * first check if the originHashVal item are DEL?
         * if yes, add new item in the DEL
         * if not, add new item in the null Position(returned hashVal)
         */
        if (firstDelItem != -1) {
            hashArray[firstDelItem] = new DataItem(value, 1);
        } else {
            hashArray[hashVal] = new DataItem(value, 1);
        }
        size++;

        if ((double) (size) / hashArray.length > ALPHA) {
            rehash();
        }
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     * Note: do not include the number of deleted spaces to check the load factor.
     * Remember that deleted spaces are available for insertion.
     */
    private void rehash() {
        size = 0;
        DataItem[] temp = hashArray;
        int rehashTimes = 0;
        int len = hashArray.length;
        int newLen = 2 * len;
        colissionTimes = 0;

        // resize a new array
        hashArray = new DataItem[nextPrime(newLen)];

        for (int i = 0; i < temp.length; i++) {

            if (temp[i] != null && temp[i] != DEL) {
                int newHashval = hashFunc(temp[i].value);
                int originVal = newHashval;
                while (hashArray[newHashval] != null && hashArray[newHashval] != DEL) {
                    newHashval++;
                    newHashval = newHashval % hashArray.length;
                }

                for (int j = 0; j < hashArray.length; j++) {
                    if (hashArray[j] != null && hashFunc(hashArray[j].value) == originVal) {
                        colissionTimes++;
                        break;
                    }
                }

                hashArray[newHashval] = new DataItem(temp[i].value, temp[i].frequency);
                size++;
                rehashTimes++;

            }

        }
        System.out.println("Rehashing " + rehashTimes + " items, new length is " + hashArray.length);
    }


    private int nextPrime(final int current) {
    int number = current + 1;
    while (true) {
        if (isPrime(number)) {
            return number;
        }
        number++;
    }
}


    /**
     * Determines whether the input integer is prime.
     *
     * You may use this implementation of isPrime during resizing, if you'd like.
     * Or, you can ignore this helper method and compute "isPrime" differently.
     * @param number the number to test
     * @return true if the number is prime, false otherwise.
     */
    private boolean isPrime(final int number) {
        // Define constants to avoid magic numbers
        final int evenDivisor = 2;
        final int firstOddDivisor = 3;

        // Edge case: numbers <= 1 are not prime
        if (number <= 1) {
            return false;
        }

        // 2 and 3 are prime numbers
        if (number <= firstOddDivisor) {
            return true;
        }

        // Optimization: any even number greater than 2 is not prime
        if (number % evenDivisor == 0) {
            return false;
        }

        // Only need to check odd divisors up to square root of number
        int sqrt = (int) Math.sqrt(number);
        for (int i = firstOddDivisor; i <= sqrt; i += evenDivisor) {
            // If divisible by any i, then it's not prime
            if (number % i == 0) {
                return false;
            }
        }

        // No divisors found; number is prime
        return true;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        DataItem(final String newVal, final int curFreq) {
            value = newVal;
            frequency = curFreq;
        }
    }

}


import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * 17-683 Data Structures for Application Programmers.
 * Homework 6.
 *This class implements a Binary Search Tree (BST) with support for custom comparators.
 *It provides methods for insertion, searching, and traversal of elements.
 *
 * andrewID: zhixuanj
 * @author Zhixuan Jiang
 *
 * @param <T> the type of elements stored in the BST, which must be comparable
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * The root node of the BST.
     */
    private Node<T> root;

    /**
     * The comparator used for ordering elements in the BST.
     * If null, natural ordering is used.
     */
    private Comparator<T> comparator;

    /**
     * Default constructor for BST.
     * Initializes the BST with natural ordering.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor for BST with a custom comparator.
     *
     * @param comp the comparator to use for ordering elements in the BST
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Returns the comparator used for ordering elements in the BST.
     *
     * @return the comparator, or null if natural ordering is used
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Returns the root element of the BST.
     *
     * @return the root element of the BST, or null if the BST is empty
     */
    public T getRoot() {
        return (root == null) ? null : root.data;
    }

    /**
     * Returns the height of the BST.
     *
     * @return the height of the BST
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Helper method to calculate the height of the BST recursively.
     *
     * @param node the current node
     * @return the height of the subtree rooted at the given node
     */
    private int getHeight(Node<T> node) {
        // Base case: if the node is null, return -1
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 0;
        }
        // Recursive case: calculate the height of left and right subtrees
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Returns the number of nodes in the BST.
     *
     * @return the number of nodes in the BST
     */
    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }

    /**
     * Helper method to calculate the number of nodes in the BST recursively.
     *
     * @param node the current node
     * @return the number of nodes in the subtree rooted at the given node
     */
    private int getNumberOfNodes(Node<T> node) {
        // Base case: if the node is null, return 0
        if (node == null) {
            return 0;
        }
        // Recursive case: count the current node and the nodes in left and right subtrees
        return 1 + getNumberOfNodes(node.left) + getNumberOfNodes(node.right);
    }

    /**
     * Searches for a specific element in the BST.
     *
     * @param toSearch the element to search for
     * @return the element if found, or null if not found
     */
    @Override
    public T search(T toSearch) {
        if (root == null) {
            return null;
        }
        Node<T> curr = root;
        if (curr.data.equals(toSearch)) {
            return curr.data;
        }
        return searchHelper(toSearch, root);
    }

    /**
     * Helper method to search for a specific element in the BST recursively.
     *
     * @param toSearch the element to search for
     * @param curr the current node
     * @return the element if found, or null if not found
     */
    private T searchHelper(T toSearch, Node<T> curr) {
        // Base case: if the current node is null, return null
        if (curr == null) {
            return null;
        }
        int cmp = compare(toSearch, curr.data);
        // Compare the element to search with the current node's data
        if (cmp < 0) {
            // If the element is less, search in the left subtree
            return searchHelper(toSearch, curr.left);
        } else if (cmp > 0) {
            // If the element is greater, search in the right subtree
            return searchHelper(toSearch, curr.right);
        } else {
            // If the element is equal, return the current node's data
            return curr.data;
        }
    }

    /**
     * Inserts a new element into the BST.
     *
     * @param toInsert the element to insert
     */
    @Override
    public void insert(T toInsert) {
        if (toInsert == null) {
            return;
        }
        root = insertHelper(toInsert, root);
    }

    /**
     * Helper method to insert a new element into the BST recursively.
     *
     * @param toInsert the element to insert
     * @param curr the current node
     * @return the updated node
     */
    private Node<T> insertHelper(T toInsert, Node<T> curr) {
        // Base case: if the current node is null, create a new node with the element
        if (curr == null) {
            return new Node<>(toInsert);
        }
        int cmp = compare(toInsert, curr.data);
        // Compare the element to insert with the current node's data
        if (cmp > 0) {
            // If the element is greater, insert into the right subtree
            curr.right = insertHelper(toInsert, curr.right);
        } else if (cmp < 0) {
            // If the element is less, insert into the left subtree
            curr.left = insertHelper(toInsert, curr.left);
        }
        return curr;
    }

    /**
     * Compares two elements using the comparator or the natural ordering.
     *
     * @param a the first element to compare
     * @param b the second element to compare
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second
     */
    private int compare(T a, T b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return a.compareTo(b);
        }
    }

    /**
     * Returns an iterator for the BST.
     *
     * @return an iterator for the BST
     */
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator<>(root);
    }

    /**
     * Iterator implementation for the BST.
     *
     * @param <T> the type of elements stored in the BST
     */
    private static class BSTIterator<T extends Comparable<T>> implements Iterator<T> {
        /**
         * Stack to store nodes for in-order traversal.
         */
        private Stack<Node<T>> stack = new Stack<>();

        /**
         * Constructor for the BSTIterator.
         *
         * @param root the root node of the BST
         */
        BSTIterator(Node<T> root) {
            pushLeft(root);
        }

        /**
         * Pushes all left children of a node onto the stack.
         *
         * @param node the starting node
         */
        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element
         */
        @Override
        public T next() {
            Node<T> node = stack.pop();
            T result = node.data;
            if (node.right != null) {
                pushLeft(node.right);
            }
            return result;
        }
    }

    /**
     * Node class representing a single node in the BST.
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {
        /**
         * The data stored in the node.
         */
        private T data;

        /**
         * The left child of the node.
         */
        private Node<T> left;

        /**
         * The right child of the node.
         */
        private Node<T> right;

        /**
         * Constructor for a node with data only.
         *
         * @param d the data to store in the node
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructor for a node with data, left child, and right child.
         *
         * @param d the data to store in the node
         * @param l the left child of the node
         * @param r the right child of the node
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }
}

/**
 * 17683 Data Structures for Application Programmers.
 * Module 15 Binary Trees, mainly Binary Search Trees.
 *
 * A very simple Binary Search Tree implementation.
 * key and value mapping but no duplicate keys allowed.
 *
 * Note: This is only to help your understanding of the concepts
 *
 * @author Terry Lee
 */
public class BSTtest implements BSTInterface {
    /**
     * reference to root node.
     */
    private Node root;

    /**
     * Constructs an empty BST.
     */
    public BSTtest() {
        root = null;
    }

    /**
     * Searches for the specified key in the tree.
     * @param key key of the element to search
     * @return boolean value indication of success or failure
     */
    @Override
    public boolean find(int key) {
        if (root == null) {
            return false;
        }

        Node curr = root;
        while (curr.key != key) {
            if (curr.key < key) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        if (curr == null) {
            return false;
        }
        return true;
    }

    /**
     * Inserts a new element into the tree.
     * @param key key of the element
     * @param value value of the element
     */
    @Override
    public void insert(int key, double value) {

        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = root;
        Node curr = root;


        while (true) {

            if (curr.key == key) {
                return;
            }
            parent = curr;
            if (curr.key < key) {
                curr = curr.right;
                if (curr == null) {
                    parent.right = newNode;
                    return;
                }
            }else {
                curr = curr.left;
                if (curr == null) {
                    parent.left = newNode;
                    return;
                }
            }
        }
    } // end of insert method

    /**
     * Deletes an element from the tree using the specified key.
     * @param key key of the element to delete
     */
    @Override
    public void delete(int key) {

        boolean isLeftChild = false;
        Node curr = root;
        Node parent = root;

        while (curr.key != key) {

            parent = curr;
            if (curr.key < key) {
                curr = curr.right;
                isLeftChild = false;
            }else {
                curr = curr.left;
                isLeftChild = true;
            }
            //case 1: not found
            if (curr == null) {
                return;
            }
        }
        //case 2: just leaf(no child)
        if (curr.left == null && curr.right == null) {
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            }else {
                parent.right = null;
            }
        } else if (curr.left == null) {
            // case 3a: curr has right child
            if (curr == root) {
                root = curr.right;
            }else if (isLeftChild) {
                parent.left = curr.right;
            }else {
                parent.right = curr.right;
            }
        }else if (curr.right == null) {
            //case 3b: curr has left child
            if (curr == root) {
                root = curr.left;
            } else if (isLeftChild) {
                parent.left = curr.left;
            }else {
                parent.right = curr.left;
            }
        }else {
            // case 4: has both children
            Node predecessor = getPredecessor(curr);
            if (curr == root) {
                root = predecessor;
            } else if (isLeftChild) {
                parent.left = predecessor;
            }else {
                parent.right = predecessor;
            }
            predecessor.right = curr.right;
        }

    }

    private Node getPredecessor(Node toDelete) {

        Node predecessorPre = toDelete;
        Node predecessor = toDelete;
        Node curr = toDelete.left;

        while (curr != null) {
            predecessorPre = predecessor;
            predecessor = curr;
            curr = curr.right;
        }

        if (predecessor != toDelete.left) {
            predecessorPre.right = predecessor.left;
            predecessor.left = toDelete.left;
        }
        return predecessor;
    }



    /**
     * Helper method to find the successor of the toDelete node.
     * This tries to find the smallest value of the right subtree
     * of the toDelete node by going down to the left most node in the subtree
     * @param toDelete node to delete
     * @return the successor of the toDelete node
     */
    private Node getSuccessor(Node toDelete) {
        Node successorParent = toDelete;
        Node successor = toDelete;
        // start the search from the root of the right subtree
        Node curr = toDelete.right;

        // move down to left as far as possible in the right subtree
        // successor's left child must be null
        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.left;
        }

        /*
         * If successor is NOT the right child of the node to delete,
         * then necessary to take care of two connections in the right subtree
         */
        if (successor != toDelete.right) {
            successorParent.left = successor.right;
            successor.right = toDelete.right;
        }

        return successor;
    }

    @Override
    public void traverse() {
        StringBuilder sb = new StringBuilder();
        inOrderHelper(root, sb);
        System.out.println(sb);
    }

    private void inOrderHelper(Node toVisit, StringBuilder sb) {
        inOrderHelper(toVisit.left, sb);
        sb.append("[").append(toVisit.key).append(",").append(toVisit.value).append("]");
        inOrderHelper(toVisit.right, sb);
    }


    /**
     * Traverses and prints values of the tree in ascending order based on key.
     */
    //@Override
    public void traverse1() {
        // Here is a feedback about how to use StringBuilder properly in recursive calls
        // use a StringBuilder instance here instead of string concatenations
        // also create the instance once and pass the reference in recursive calls
        // instead of creating the instance over and over again in the recursive method
        StringBuilder sb = new StringBuilder();
        inOrderHelper(root, sb);
        System.out.println(sb);
    }

    /**
     * Recursive helper method to traverse the tree.
     * @param toVisit node to visit
     * @param sb stringbuilder instance to append values of the node
     */
    private void inOrderHelper1(Node toVisit, StringBuilder sb) {
        if (toVisit != null) {
            inOrderHelper(toVisit.left, sb);
            sb.append("[").append(toVisit.key).append(",").append(toVisit.value).append("]");
            inOrderHelper(toVisit.right, sb);
        }
    }

    private void preOrderHelper(Node toVisit, StringBuilder sb) {
        if (toVisit != null) {
            sb.append("[").append(toVisit.key).append(",").append(toVisit.value).append("]");
            preOrderHelper(toVisit.left, sb);
            preOrderHelper(toVisit.right, sb);
        }
    }

    private void postOrderHelper(Node toVisit, StringBuilder sb) {
        if (toVisit != null) {
            preOrderHelper(toVisit.left, sb);
            preOrderHelper(toVisit.right, sb);
            sb.append("[").append(toVisit.key).append(",").append(toVisit.value).append("]");
        }
    }





    /**
     * static nested Node class for Node.
     */
    private static class Node {
        /**
         * Key integer.
         */
        private int key;
        /**
         * double value mapped to the key.
         */
        private double value;
        /**
         * reference to left child.
         */
        private Node left;
        /**
         * reference to right child.
         */
        private Node right;

        /**
         * Constructs a new node with key and value.
         * @param k integer key
         * @param v double value
         */
        Node(int k, double v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
}

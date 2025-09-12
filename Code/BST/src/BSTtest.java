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

    private Node root;

    public BSTtest() {
        root = null;
    }

    /**
     * Searches for the specified key in the tree.
     *
     * @param key key of the element to search
     * @return boolean value indication of success or failure
     */
    @Override
    public boolean find(int key) {
        if (root == null) {
            return false;
        }

        Node curr = root;

        if (root.key == key) {
            return true;
        }

        while (curr.key != key) {

            if (curr.key < key) {
                curr = curr.right;
            }else {
                curr = curr.left;
            }

            if (curr == null) {
                return false;
            }
        }
        return true;
    }



    /**
     * Inserts a new element into the tree.
     *
     * @param key   key of the element
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
    }

    /**
     * Deletes an element from the tree using the specified key.
     *
     * @param key key of the element to delete
     */
    @Override
    public void delete(int key) {
        if (root == null) {
            return;
        }

        Node parent = root;
        Node curr = root;

        boolean isLeftChild = true;

        while (curr.key != key) {
            parent = root;
            if (curr.key < key) {
                curr = curr.right;
                isLeftChild = false;
            }else {
                isLeftChild = true;
                curr = curr.left;
            }

            if (curr == null) {
                return;
            }

            if (curr.left == null && curr.right == null) {
                if (curr == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                }else {
                    parent.right = null;
                }
            } else if (curr.right == null) {
                if (curr == root) {
                    root = curr.left;
                } else if (isLeftChild) {
                    parent.left = curr.left;
                }else {
                    parent.right = curr.left;
                }
            } else if (curr.left == null) {
                if (curr == root) {
                    root = curr.right;
                } else if (isLeftChild) {
                    parent.left = curr.right;
                }else {
                    parent.right = curr.right;
                }
            }else {
                // 2 children
                Node successor = getSuccessor(curr);
                if (curr == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                }else {
                    parent.right = successor;
                }
                successor.left = curr.left;
            }

        }
    }

    private Node getSuccessor(Node toDelete) {
        Node successorParent = toDelete;
        Node successor = toDelete;
        Node curr = toDelete.right;

        while (curr != null) {
            successorParent = successor;
            successor = curr;
            curr = curr.left;
        }
        if (successor != toDelete.right) {
            successorParent.right = successor.right;
            successor.right = toDelete.right;
        }
        return successor;
    }

    /**
     * Traverses and prints values of the tree in ascending order based on key.
     */
    @Override
    public void traverse() {

    }

    private class Node {

        private Node left;

        private Node right;

        private int key;

        private double value;

        Node(int k, double v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
}

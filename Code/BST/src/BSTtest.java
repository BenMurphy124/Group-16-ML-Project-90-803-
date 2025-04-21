public class BSTtest implements BSTInterface{

    private Node root;

    public BSTtest() {
        root = null;
    }






    @Override
    public boolean find(int key) {
        if (root == null) {
            return false;
        }

        Node curr = root;
        while (curr.value != key) {
            if (curr.value < key) {
                curr = curr.right;
            }
            if (curr.value > key) {
                curr = curr.left;
            }
            if (curr == null) {
                return false;
            }
        }
        return true;
    }

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

    @Override
    public void delete(int key) {

    }

    @Override
    public void traverse() {

    }


    private static class Node {

        private int key;

        private double value;

        private Node left;

        private Node right;

        Node(int k, double v) {

            key = k;

            value = v;

            left = null;

            right = null;
        }


    }




}
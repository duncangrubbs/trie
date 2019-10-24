/**
 * Duncan Grubbs
 * 10/24/19
 */

import java.util.ArrayList;

public class Trie {
    private TrieNode root;
    private int height;
    private int size;

    public Trie(TrieNode root) {
        this.root = root;
        this.height = 1;
        this.size = 1;
    }

    public Trie() {
        this.root = null;
        this.height = 0;
        this.size = 0;
    }

    /**
     * Helper function for insert
     * @param start Tie break start node (leaf)
     * @param data String we are trying to insert
     * @param index Index that they match at (up until)
     */
    public void breakTie(TrieNode start, String data, int index) {
        String matchedString = start.getData();
        start = start.getParent();
        if (start == null) {
            start = new TrieNode("\0");
            this.root = start;
        }
        while (data.charAt(index) == matchedString.charAt(index)) {
            if (data.charAt(index) == '0') {
                TrieNode next = new TrieNode("0", start);
                start.setLeft(next);
                start = next;
            } else {
                TrieNode next = new TrieNode("1", start);
                start.setRight(next);
                start = next;
            }
            index++;
        }
        if (data.charAt(index) == '0') {
            start.setLeft(new TrieNode(data, start));
            start.setRight(new TrieNode(matchedString, start));
        } else {
            start.setRight(new TrieNode(data, start));
            start.setLeft(new TrieNode(matchedString, start));
        }
    }


    /**
     * Inserts a bit string into the Trie.
     * @param data Bit String to insert
     * @return TRUE if successful, FALSE otherwise
     */
    public boolean insert(String data) {
        if (this.root == null) {
            this.root = new TrieNode(data);
            this.height = this.size = 1;
            return true;
        }
        TrieNode current = this.root;
        TrieNode parent = this.root;
        int index = 0;
        while (true) {
            // if we ended at a node with one child who is a leaf
            if (current == null) {
                if (parent.getLeft() == null) {
                    parent.setLeft(new TrieNode(data, parent));
                } else {
                    parent.setRight(new TrieNode(data, parent));
                }
                this.size++;
                return true;
            }

            // if ended on a leaf (i.e. there is a common prefix)
            if (current.isLeaf()) {
                // in this case the string are equal
                if (data.equals(current.getData())) {
                    return false;
                }
                this.breakTie(current, data, Math.max(0, index - 1));
                this.size++;
                return true;
            }

            parent = current;
            // move left
            if (data.charAt(index) == '0') {
                current = current.getLeft();
            } else { // move right
                current = current.getRight();
            }
            index++;
        }
    }

    /**
     * Prints the Trie through recursive DFS
     * @param root Root node of Trie
     */
    public void recursivePrint(TrieNode root) {
        if (root == null) { return; }
        if (root.isLeaf() && this.size != 1) {
            System.out.println(root.getData() + " :self: " + root + " :parent: " + root.getParent());
        } else {
            System.out.println(root.getData() + " :self: " + root);
        }
        recursivePrint(root.getLeft());
        recursivePrint(root.getRight());
    }

    /**
     * Prints out DFS formatted Trie
     */
    public void print() {
        System.out.println("\nTRIE:");
        this.recursivePrint(this.root);
    }

    /**
     * Helper function for toList, recursively runs DFS
     * @param root Root note of trie
     * @param list List to be appended to
     * @return ArrayList of Nodes
     */
    public ArrayList<TrieNode> recursiveList(TrieNode root, ArrayList<TrieNode> list) {
        if (root == null) { return list; }
        list.add(root);
        list = recursiveList(root.getLeft(), list);
        list = recursiveList(root.getRight(), list);
        return list;
    }

    /**
     * Creates a DFS search list of nodes in the Trie
     * @return ArrayList of Nodes
     */
    public ArrayList<TrieNode> toList() {
        return recursiveList(this.root, new ArrayList<>());
    }


    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

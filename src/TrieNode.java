/**
 * Duncan Grubbs
 * 10/24/19
 */

public class TrieNode {
    private TrieNode parent;
    private TrieNode left;
    private TrieNode right;
    private String data;

    public TrieNode(String data) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public TrieNode(String data, TrieNode parent) {
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public TrieNode getLeft() {
        return left;
    }

    public void setLeft(TrieNode left) {
        this.left = left;
    }

    public TrieNode getRight() {
        return right;
    }

    public void setRight(TrieNode right) {
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

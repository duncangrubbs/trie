/**
 * Duncan Grubbs
 * 10/24/19
 */

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("0001");
        t.insert("0010");
        t.insert("0011");
        t.insert("0100");
        t.insert("0101");
        t.insert("0110");
        t.insert("0111");
        t.insert("1000");

        t.print();

        ArrayList<TrieNode> nodes = t.toList();
        for (TrieNode node : nodes) {
            System.out.println(node.getData());
        }
    }

}

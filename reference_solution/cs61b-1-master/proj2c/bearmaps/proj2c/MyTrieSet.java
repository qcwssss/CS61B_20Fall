package bearmaps.proj2c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet {
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;
        Node(boolean b) {
            this.isKey = b;
            map = new HashMap<>();
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node(false);
    }

    /** Clears all items out of Trie */
    public void clear() {
        root = new Node(false);
    }

    private Node find(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node n = find(key);
        return n != null && n.isKey;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    private void collect(String s, List<String> x, Node n) {
        if (n == null) {
            return;
        }
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.map.keySet()) {
            collect(s + c, x, n.map.get(c));
        }
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        Node n = find(prefix);
        collect(prefix, keys, n);
        return keys;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}

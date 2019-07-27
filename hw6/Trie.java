import java.util.LinkedList;
import java.util.*;

public class Trie {
    private static final int RADIX = 256;

    public class Node {
        Node[] next = new Node[RADIX];
        boolean isWord = false;
    }

    private Node root = null;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int cnt = 0;

        if (x.isWord) {
            cnt =1;
        }

        for (int i = 0; i < RADIX; i++) {
            cnt += size(x.next[i]);
        }
        return cnt;
    }

    public void put(String word) {
        root = put(root, word, 0);
    }

    private Node put(Node x, String word, int d) {
        if (x == null) {
            x = new Node();
        }

        if (word.length() == d) {
            x.isWord = true;
            return x;
        }

        char c = word.charAt(d);
        x.next[c] = put(x.next[c], word, d + 1);

        return x;
    }

    public boolean contains(String word) {
        return contains( root, word, 0);
    }

    private boolean contains(Node x, String word, int d) {
        if (x == null) {
            return false;
        }
        if (word.length() < d) {
            return false;
        }
        if (word.length() == d) {
            return x.isWord;
        }
        char c = word.charAt(d);
        return contains(x.next[c], word, d + 1);
    }

    public boolean isPrefix(String prefix) {
        return isPrefix(root, prefix, 0);
    }

    private boolean isPrefix( Node x, String prefix, int d) {
        if (x == null) {
            return false;
        }

        if (prefix.length() <= 0) {
            return true;
        }

        char c = prefix.charAt(d);
        return isPrefix(x.next[c], prefix, d + 1);
    }

    private Node getPrefixNode (Node x, String prefix, int d) {
        if (x == null) {
            return null;
        }

        if (prefix.length() <= d) {
            return x;
        }

        char c = prefix.charAt(d);
        return getPrefixNode(x.next[c], prefix, d + 1);
    }

    private boolean nodeContainKey(Node x) {
        if (x == null) {
            return false;
        }

        if (x.isWord) {
            return true;
        }

        boolean res = false;
        for (int i = 0; i < RADIX; i++) {
            res = (res || nodeContainKey(x.next[i]));
        }
        return res;
    }

    public Iterable<String> getAll() {
        List<String> list = new LinkedList<String>();
        getAll(root, list, "");
        return list;
    }

    private void getAll(Node x, List<String> list, String s) {
        if (x == null) {
            return;
        }

        if (x.isWord == true) {
            list.add(s);
        }

        for (int i = 0; i < RADIX; i++) {
            getAll(x.next[i], list, s + (char)i);
        }
    }
}

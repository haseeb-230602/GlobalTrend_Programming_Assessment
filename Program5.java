class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // for 'a' to 'z'
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    private TrieNode searchNode(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}

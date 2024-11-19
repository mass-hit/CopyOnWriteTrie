import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Trie {
    private TrieNode root;

    public Trie() {
    }

    public Trie(TrieNode root) {
        this.root = root;
    }

    public Integer get(String key) {
        if(root == null) {
            return null;
        }
        TrieNode currentNode = root;
        for (char ch : key.toCharArray()) {
            if (!currentNode.children.containsKey(ch)) {
                return null;
            }
            currentNode = currentNode.children.get(ch);
        }
        if (currentNode != null && currentNode.value != null)
            return currentNode.value;
        return null;
    }

    public Trie put(String key, Integer value) {
        Stack<Map.Entry<Character, TrieNode>> stack = new Stack<>();
        TrieNode currentNode = root;
        for (char ch : key.toCharArray()) {
            if (currentNode != null) {
                stack.push(Map.entry(ch, new TrieNode(currentNode.value, currentNode.children)));
                currentNode = currentNode.children.getOrDefault(ch, null);
            } else {
                stack.push(Map.entry(ch, new TrieNode(null, new HashMap<>())));
            }
        }
        if (currentNode != null) {
            currentNode = new TrieNode(value, currentNode.children);
        } else {
            currentNode = new TrieNode(value, new HashMap<>());
        }
        while (!stack.isEmpty()) {
            Map.Entry<Character, TrieNode> entry = stack.pop();
            char ch = entry.getKey();
            TrieNode parent = entry.getValue();
            parent.children.put(ch, currentNode);
            currentNode = parent;
        }
        return new Trie(currentNode);
    }

    public Trie remove(String key) {
        if (root == null) {
            return new Trie(null);
        }
        Stack<Map.Entry<Character, TrieNode>> stack = new Stack<>();
        TrieNode currentNode = root;
        for (char ch : key.toCharArray()) {
            stack.push(Map.entry(ch, new TrieNode(currentNode.value, currentNode.children)));
            if (!currentNode.children.containsKey(ch)) {
                return new Trie(root);
            }
            currentNode = currentNode.children.get(ch);
        }
        if (currentNode.children.isEmpty()) {
            currentNode = null;
        } else {
            currentNode = new TrieNode(null, currentNode.children);
        }
        while (!stack.isEmpty()) {
            Map.Entry<Character, TrieNode> entry = stack.pop();
            char ch = entry.getKey();
            TrieNode parent = entry.getValue();
            if (currentNode == null) {
                parent.children.remove(ch);
                if (parent.children.isEmpty() && parent.value == null) {
                    parent = null;
                }
            } else {
                parent.children.put(ch, currentNode);
            }
            currentNode = parent;
        }
        return new Trie(currentNode);
    }
}

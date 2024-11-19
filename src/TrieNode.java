import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Integer value;
    Map<Character, TrieNode> children;

    public TrieNode() {
    }

    public TrieNode(Integer value, Map<Character, TrieNode> children) {
        this.value = value;
        this.children = new HashMap<>(children);
    }
}
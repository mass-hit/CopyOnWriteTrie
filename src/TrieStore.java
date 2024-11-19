
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class TrieStore {
    private volatile Trie trie;
    private final ReentrantLock writeLock = new ReentrantLock();

    public TrieStore() {
        trie = new Trie();
    }

    public Integer get(String key) {
        return trie.get(key);
    }

    public void put(String key, Integer value) {
        try {
            writeLock.lock();
            trie = trie.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String key) {
        try {
            writeLock.lock();
            trie = trie.remove(key);
        } finally {
            writeLock.unlock();
        }
    }
}

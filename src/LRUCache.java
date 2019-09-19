import java.util.HashMap;

public class LRUCache {

    HashMap<String, Node> cacheMap;
    int MAX_CACHE_SIZE = 4;
    Node head, tail;

    public LRUCache() {
        this.cacheMap = new HashMap<String, Node>();
    }

    public class Node {
        String key, value;
        Node left, right;
    }

    public void addCache(String key, String value) {
        if (cacheMap.containsKey(key)) {
            Node old = cacheMap.get(key);
            Node node = new Node();
            node.value = value;
            node.key = key;

            removeNode(old);
            addToHead(node);
            cacheMap.put(key, node);
        } else {
            Node node = new Node();
            node.value = value;
            node.key = key;
            addToHead(node);
            cacheMap.put(key, node);
        }
        if (cacheMap.size() > MAX_CACHE_SIZE) {
            Node lru = cacheMap.get(tail.key);
            removeNode(lru);
            cacheMap.remove(lru.key);
        }
    }

    public String getCache(String key) {
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return "-1";
    }

    private void removeNode(Node node) {
        if (node.left != null) {
            node.left.right = node.right;
        }
        if (node.right != null) {
            node.right.left = node.left;
        } else { // else this.tail is being removed and we need to reassign it
            tail = node.left;
        }
    }

    private void addToHead(Node node) {
        node.right = head;
        if (head != null) { // when cache size is 0 and thus this.head is null
            head.left = node;
        } else {
            tail = node;  // if cache size is 0, head is also tail
        }
        head = node;
    }

    public static void main(String[] args) {
        LRUCache lrucache = new LRUCache();
        lrucache.addCache("1", "1");
        lrucache.addCache("10", "15");
        lrucache.addCache("15", "10");
        lrucache.addCache("10", "16");
        lrucache.addCache("12", "15");
        lrucache.addCache("18", "10");
        lrucache.addCache("13", "16");

        lrucache.getCache("1");
        lrucache.getCache("10");
        lrucache.getCache("15");
    }
}

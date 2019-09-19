import java.util.HashMap;

public class LRUCache {

    // TODO Limit Max Length

    HashMap<String, Node> cacheMap;
    int MAX_CACHE_LENGTH = 4;
    Node head, tail;

    public LRUCache() {
        this.cacheMap = new HashMap<String, Node>();
    }

    public class Node {
        String value;
        Node left, right;
    }

    public void addCache(String key, String value) {
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            node.value = value;

            Node old = cacheMap.get(key);
            removeNode(old);
            addToHead(node);
            cacheMap.put(key, node);
        } else {
            Node node = new Node();
            node.value = value;
            addToHead(node);
            cacheMap.put(key, node);
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

    private void addToHead(Node node) {
        node.left = null;
        node.right = head;
        if (head != null) {
            head.left = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    private void removeNode(Node node) {
        if (node.left != null) {
            node.left.right = node.right;
        }
        if (node.right != null) {
            if (node.left != null) {
                node.right.left = node.left;
            } else {
                node.right.left = head;
            }
        } else {
            tail = node.left;
        }
        if (node == head) {
            head = null;
        }
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

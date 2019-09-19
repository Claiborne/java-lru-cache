import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void testAddCache() {
        LRUCache cache = new LRUCache();

        cache.addCache("1", "1");
        assertEquals("1", cache.cacheMap.get("1").value);
        cache.addCache("10", "15");
        assertEquals("15", cache.cacheMap.get("10").value);
        cache.addCache("10", "16");
        assertEquals("16", cache.cacheMap.get("10").value);
        cache.addCache("12", "15");
        assertEquals("15", cache.cacheMap.get("12").value);

        assertEquals("1", cache.cacheMap.get("1").value);
    }

    @Test
    void testGetCache() {
        LRUCache cache = new LRUCache();

        cache.addCache("1", "1");
        cache.addCache("10", "15");
        cache.addCache("10", "16");
        cache.addCache("12", "15");

        assertEquals("1", cache.getCache("1"));
        assertEquals("16", cache.getCache("10"));
        assertEquals("15", cache.getCache("12"));
        assertEquals("-1", cache.getCache("999"));
    }

    @Test
    void testHead() {
        LRUCache cache = new LRUCache();

        cache.addCache("1", "1");
        assertEquals("1", cache.head.value);
        cache.addCache("10", "15");
        assertEquals("15", cache.head.value);
        cache.addCache("10", "16");
        assertEquals("16", cache.head.value);
        cache.addCache("12", "15");
        assertEquals("15", cache.head.value);

        cache.getCache("1");
        assertEquals("1", cache.head.value);
        cache.getCache("10");
        assertEquals("16", cache.head.value);
    }

    @Test
    void testTail() {
        LRUCache lrucache = new LRUCache();
        lrucache.addCache("1", "1");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("2", "2");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("3", "3");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("4", "4");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("5", "5");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("6", "6");
        assertEquals("1", lrucache.tail.value);

        lrucache.getCache("1");
        assertEquals("2", lrucache.tail.value);
        lrucache.getCache("3");
        assertEquals("2", lrucache.tail.value);
        lrucache.getCache("2");
        assertEquals("4", lrucache.tail.value);
    }

    @Test
    void testTailWhenHeadIsSetTwice() {
        LRUCache cache = new LRUCache();

        cache.addCache("1", "1");
        assertEquals("1", cache.tail.value);
        cache.addCache("1", "5");
        assertEquals("5", cache.tail.value);
        cache.addCache("10", "2");
        assertEquals("5", cache.tail.value);
        cache.addCache("10", "3");
        assertEquals("5", cache.tail.value);
        cache.addCache("12", "4");
        assertEquals("5", cache.tail.value);

        cache.getCache("12");
        assertEquals("5", cache.tail.value);
        cache.getCache("1");
        assertEquals("3", cache.tail.value);
        cache.getCache("1");
        assertEquals("3", cache.tail.value);
        cache.getCache("10");
        assertEquals("4", cache.tail.value);
    }

    @Test
    void testHeadWhenHeadIsSetTwice() {
        LRUCache cache = new LRUCache();

        cache.addCache("1", "1");
        assertEquals("1", cache.head.value);
        cache.addCache("1", "5");
        assertEquals("5", cache.head.value);
        cache.addCache("10", "2");
        assertEquals("2", cache.head.value);
        cache.addCache("10", "3");
        assertEquals("3", cache.head.value);
        cache.addCache("12", "4");
        assertEquals("4", cache.head.value);

        cache.getCache("12");
        assertEquals("4", cache.head.value);
        cache.getCache("1");
        assertEquals("5", cache.head.value);
        cache.getCache("1");
        assertEquals("5", cache.head.value);
        cache.getCache("10");
        assertEquals("3", cache.head.value);
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void testAddCache() {
        LRUCache lrucache = new LRUCache();

        lrucache.addCache("1", "1");
        assertEquals("1", lrucache.cacheMap.get("1").value);
        lrucache.addCache("10", "15");
        assertEquals("15", lrucache.cacheMap.get("10").value);
        lrucache.addCache("10", "16");
        assertEquals("16", lrucache.cacheMap.get("10").value);
        lrucache.addCache("12", "15");

        assertEquals("15", lrucache.cacheMap.get("12").value);
        assertEquals("1", lrucache.cacheMap.get("1").value);
    }

    @Test
    void testGetCache() {
        LRUCache lrucache = new LRUCache();

        lrucache.addCache("1", "1");
        lrucache.addCache("10", "15");
        lrucache.addCache("10", "16");
        lrucache.addCache("12", "15");

        assertEquals("1", lrucache.getCache("1"));
        assertEquals("16", lrucache.getCache("10"));
        assertEquals("15", lrucache.getCache("12"));
        assertEquals("-1", lrucache.getCache("999"));
    }

    @Test
    void testHead() {
        LRUCache lrucache = new LRUCache();

        lrucache.addCache("1", "1");
        assertEquals("1", lrucache.head.value);
        lrucache.addCache("10", "15");
        assertEquals("15", lrucache.head.value);
        lrucache.addCache("10", "16");
        assertEquals("16", lrucache.head.value);
        lrucache.addCache("12", "15");
        assertEquals("15", lrucache.head.value);

        lrucache.getCache("1");
        assertEquals("1", lrucache.head.value);
        lrucache.getCache("10");
        assertEquals("16", lrucache.head.value);
    }

    @Test
    void testTail() {
        LRUCache lrucache = new LRUCache();

        // 4 is the max cache size
        lrucache.addCache("1", "1");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("2", "2");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("3", "3");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("4", "4");
        assertEquals("1", lrucache.tail.value);
        lrucache.addCache("5", "5");
        assertEquals("2", lrucache.tail.value);
        lrucache.addCache("6", "6");
        assertEquals("3", lrucache.tail.value);

        lrucache.getCache("3");
        assertEquals("4", lrucache.tail.value);
        lrucache.getCache("4");
        assertEquals("5", lrucache.tail.value);
    }

    @Test
    void testECacheEvict() {
        LRUCache lrucache = new LRUCache();

        // 4 is the max cache size
        lrucache.addCache("1", "1");
        assertEquals(1, lrucache.cacheMap.size());
        lrucache.addCache("2", "2");
        assertEquals(2, lrucache.cacheMap.size());
        lrucache.addCache("3", "3");
        assertEquals(3, lrucache.cacheMap.size());
        lrucache.addCache("4", "4");
        assertEquals(4, lrucache.cacheMap.size());
        lrucache.addCache("5", "5");
        assertEquals(4, lrucache.cacheMap.size());
        lrucache.addCache("6", "6");
        assertEquals(4, lrucache.cacheMap.size());

        assertEquals("-1", lrucache.getCache("1"));
        assertEquals("-1", lrucache.getCache("2"));
        assertEquals(4, lrucache.cacheMap.size());
    }
}

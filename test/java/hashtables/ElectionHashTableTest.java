package hashtables;

import models.Election;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectionHashTableTest {

    private ElectionHashTable table;

    @BeforeEach
    void setUp() {
        table = new ElectionHashTable(10);
    }

    @Test
    void hashFunction() {
        // 15 % 10 = 5
        assertEquals(5, table.hashFunction(15));
        // 101 % 10 = 1
        assertEquals(1, table.hashFunction(101));
    }

    @Test
    void insertAndGet() {
        Election e1 = new Election(1, "General", "Dublin", 2020, 5);
        Election e2 = new Election(11, "Local", "Cork", 2019, 3); // collision with ID 1

        table.insert(e1);
        table.insert(e2);

        assertEquals(e1, table.get(1));
        assertEquals(e2, table.get(11));
    }

    @Test
    void insertHandlesCollisions() {
        Election e1 = new Election(1, "General", "Dublin", 2020, 5);
        Election e2 = new Election(11, "Local", "Cork", 2019, 3); // same hash index as 1

        table.insert(e1);
        table.insert(e2);

        // e1 goes to index 1
        // e2 should probe to index 2
        Election[] arr = table.toArray();

        assertEquals(e1, arr[1]);
        assertEquals(e2, arr[2]);
    }

    @Test
    void getReturnsNullWhenNotFound() {
        table.insert(new Election(1, "General", "Dublin", 2020, 5));
        assertNull(table.get(999)); // nonexistent ID
    }

    @Test
    void rehashCreatesLargerTable() {
        table.insert(new Election(1, "General", "Dublin", 2020, 5));
        table.insert(new Election(2, "Local", "Cork", 2019, 3));

        ElectionHashTable bigger = table.rehash(2.0); // double size

        assertEquals(20, bigger.toArray().length);
        assertNotNull(bigger.get(1));
        assertNotNull(bigger.get(2));
    }

    @Test
    void toArrayReturnsInternalArray() {
        Election e = new Election(1, "General", "Dublin", 2020, 5);
        table.insert(e);

        Election[] arr = table.toArray();
        assertEquals(10, arr.length);
        assertEquals(e, arr[1]); // 1 % 10 = 1
    }

    @Test
    void displayHashTableDoesNotCrash() {
        // This test ensures the method runs without throwing exceptions.
        table.insert(new Election(1, "General", "Dublin", 2020, 5));
        assertDoesNotThrow(() -> table.displayHashTable());
    }
}
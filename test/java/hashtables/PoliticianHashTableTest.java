package hashtables;

import models.Politician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliticianHashTableTest {

    private PoliticianHashTable table;

    @BeforeEach
    void setUp() {
        table = new PoliticianHashTable(10);
    }

    @Test
    void hashFunction() {
        assertEquals(5, table.hashFunction(15));  // 15 % 10 = 5
        assertEquals(1, table.hashFunction(101)); // 101 % 10 = 1
    }

    @Test
    void insertAndGet() {
        Politician p1 = new Politician(1, "John Doe", "Fine Gael", "Dublin");
        Politician p2 = new Politician(11, "Jane Smith", "Sinn Fein", "Cork"); // collision with ID 1

        table.insert(p1);
        table.insert(p2);

        assertEquals(p1, table.get(1));
        assertEquals(p2, table.get(11));
    }

    @Test
    void insertHandlesCollisions() {
        Politician p1 = new Politician(1, "John Doe", "Fine Gael", "Dublin");
        Politician p2 = new Politician(11, "Jane Smith", "Sinn Fein", "Cork"); // same hash index as 1

        table.insert(p1);
        table.insert(p2);

        Politician[] arr = table.toArray();

        assertEquals(p1, arr[1]); // 1 % 10 = 1
        assertEquals(p2, arr[2]); // collision → next free slot
    }

    @Test
    void getReturnsNullWhenNotFound() {
        table.insert(new Politician(1, "John Doe", "Fine Gael", "Dublin"));
        assertNull(table.get(999)); // nonexistent ID
    }

    @Test
    void rehashCreatesLargerTable() {
        table.insert(new Politician(1, "John Doe", "Fine Gael", "Dublin"));
        table.insert(new Politician(2, "Jane Smith", "Sinn Fein", "Cork"));

        PoliticianHashTable bigger = table.rehash(2.0);

        assertEquals(20, bigger.toArray().length);
        assertNotNull(bigger.get(1));
        assertNotNull(bigger.get(2));
    }

    @Test
    void toArrayReturnsInternalArray() {
        Politician p = new Politician(1, "John Doe", "Fine Gael", "Dublin");
        table.insert(p);

        Politician[] arr = table.toArray();
        assertEquals(10, arr.length);
        assertEquals(p, arr[1]); // 1 % 10 = 1
    }

    @Test
    void displayHashTableDoesNotCrash() {
        table.insert(new Politician(1, "John Doe", "Fine Gael", "Dublin"));
        assertDoesNotThrow(() -> table.displayHashTable());
    }
}
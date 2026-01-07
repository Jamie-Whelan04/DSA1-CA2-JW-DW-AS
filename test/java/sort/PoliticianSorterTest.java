package sort;

import models.Politician;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliticianSorterTest {

    @Test
    void sortByName() {
        PoliticianSorter sorter = new PoliticianSorter();

        Politician[] arr = {
                new Politician(3, "Charlie", "Green", "Cork"),
                new Politician(1, "Alice", "Fine Gael", "Dublin"),
                new Politician(2, "Bob", "Labour", "Limerick"),
                null
        };

        sorter.sortByName(arr);

        assertEquals("Alice", arr[0].getName());
        assertEquals("Bob", arr[1].getName());
        assertEquals("Charlie", arr[2].getName());
        assertNull(arr[3]); // null stays at end
    }

    @Test
    void sortByParty() {
        PoliticianSorter sorter = new PoliticianSorter();

        Politician[] arr = {
                new Politician(1, "Alice", "Fine Gael", "Dublin"),
                new Politician(2, "Bob", "Labour", "Limerick"),
                new Politician(3, "Charlie", "Aontú", "Cork"),
                null
        };

        sorter.sortByParty(arr);

        assertEquals("Aontú", arr[0].getParty());
        assertEquals("Fine Gael", arr[1].getParty());
        assertEquals("Labour", arr[2].getParty());
        assertNull(arr[3]);
    }
}
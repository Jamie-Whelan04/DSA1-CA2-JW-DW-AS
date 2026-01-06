package hashtables;

import models.Election;

public class ElectionHashTable {

    private Election[] hashTable; // array of Election objects

    public ElectionHashTable(int size) {
        hashTable = new Election[size]; // fix: correct array initialization
    }

    public void displayHashTable() {
        System.out.println("Election Hash Table (Linear Probing):\n==================");
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                System.out.println(i + ". " + hashTable[i].getElectionType() + " (" + hashTable[i].getId() + ")");
            } else {
                System.out.println(i + ". empty");
            }
        }
    }

    // Hash function: key = election ID
    public int hashFunction(int key) {
        return key % hashTable.length;
    }


    public void insert(Election e) {
        int loc = hashFunction(e.getId());
        System.out.println("Inserting " + e.getElectionType() + " (home location " + loc + ")...");

        while (hashTable[loc] != null) { // while not free
            System.out.println("Location " + loc + " is taken. Probing to " + ((loc + 1) % hashTable.length) + "...");
            loc = (loc + 1) % hashTable.length;
        }

        System.out.println("Storing " + e.getElectionType() + " at location " + loc);
        hashTable[loc] = e;
    }

    public Election get(int id) {
        int loc = hashFunction(id);
        int start = loc;

        while (hashTable[loc] != null) {
            if (hashTable[loc].getId() == id) {
                return hashTable[loc];
            }
            loc = (loc + 1) % hashTable.length;
            if (loc == start) break; // prevent infinite loop
        }
        return null;
    }

    public ElectionHashTable rehash(double sizeFactor) {
        ElectionHashTable newTable = new ElectionHashTable((int) (hashTable.length * sizeFactor));
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) newTable.insert(hashTable[i]);
        }
        return newTable;
    }
    public Election[] toArray() { return hashTable; }

}

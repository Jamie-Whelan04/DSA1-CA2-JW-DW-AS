package hashtables;

import models.Politician;

public class PoliticianHashTable {

    Politician[] hashTable;


    public PoliticianHashTable(int size) {
        hashTable = new Politician[size];
    }


    public void displayHashTable() {
        System.out.println("Politician Hash Table (Linear Probing):\n==================");
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                System.out.println(i + ". " + hashTable[i].getName() + " (" + hashTable[i].getId() + ")");
            } else {
                System.out.println(i + ". empty");
            }
        }
    }

    // Hash function: key = politician ID
    public int hashFunction(int key) {
        return key % hashTable.length;
    }

    // Insert a politician using linear probing
    public void insert(Politician p) {
        int loc = hashFunction(p.getId());
        System.out.println("Inserting " + p.getName() + " (home location " + loc + ")...");

        while (hashTable[loc] != null) { // while not free
            System.out.println("Location " + loc + " is taken. Probing to " + ((loc + 1) % hashTable.length) + "...");
            loc = (loc + 1) % hashTable.length;
        }

        System.out.println("Storing " + p.getName() + " at location " + loc);
        hashTable[loc] = p;
    }


    public Politician get(int id) {
        int loc = hashFunction(id);
        int start = loc;

        while (hashTable[loc] != null) {
            if (hashTable[loc].getId() == id) {
                return hashTable[loc];
            }
            loc = (loc + 1) % hashTable.length;
            if (loc == start) break;
        }
        return null;
    }


    public PoliticianHashTable rehash(double sizeFactor) {
        PoliticianHashTable newTable = new PoliticianHashTable((int) (hashTable.length * sizeFactor));
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) newTable.insert(hashTable[i]);
        }
        return newTable;
    }
    public Politician[] toArray() { return hashTable; }


}


package Hashing;

import java.util.BitSet;
import java.util.Vector;

public class Double_Hashing {

    public void main(String[] args) {
        DoubleHash myHash = new DoubleHash(13); // creates an empty hash table of size 13

        /* Inserts random element in the hash table */

        int[] insertions = { 115, 12, 87, 66, 123 };
        int n1 = insertions.length;

        for (int i = 0; i < n1; i++)
            myHash.insert(insertions[i]);

        System.out.print("Status of hash table after initial insertions : ");
        myHash.print();

        /*
         ** Searches for random element in the hash table,
         ** and prints them if found.
         */

        int[] queries = { 1, 12, 2, 3, 69, 88, 115 };
        int n2 = queries.length;

        System.out.println("\n" + "Search operation after insertion : ");

        for (int i = 0; i < n2; i++)
            if (myHash.search(queries[i]))
                System.out.println(queries[i] + " present");

        /* Deletes random element from the hash table. */

        int[] deletions = { 123, 87, 66 };
        int n3 = deletions.length;

        for (int i = 0; i < n3; i++)
            myHash.erase(deletions[i]);

        System.out.print("Status of hash table after deleting elements : ");
        myHash.print();
    }

    class DoubleHash {
        private int TABLE_SIZE, keysPresent, PRIME;
        private Vector<Integer> hashTable;
        private BitSet isPrime;
        private static final long MAX_SIZE = 10000001L;

        /* Function to set sieve of Eratosthenes. */
        private void setSieve() {
            isPrime.set(0, true);
            isPrime.set(1, true);
            for (long i = 2; i * i <= MAX_SIZE; i++)
                if (!isPrime.get((int) i))
                    for (long j = i * i; j <= MAX_SIZE; j += i)
                        isPrime.set((int) j);
        }

        private int hash1(int value) {
            return value % TABLE_SIZE;
        }

        private int hash2(int value) {
            return PRIME - (value % PRIME);
        }

        private boolean isFull() {
            return (TABLE_SIZE == keysPresent);
        }

        public DoubleHash(int n) {
            isPrime = new BitSet((int) MAX_SIZE);
            setSieve();
            TABLE_SIZE = n;

            /* Find the largest prime number smaller than hash table's size. */
            PRIME = TABLE_SIZE - 1;
            while (isPrime.get(PRIME))
                PRIME--;

            keysPresent = 0;

            /* Fill the hash table with -1 (empty entries). */
            hashTable = new Vector<>();
            for (int i = 0; i < TABLE_SIZE; i++)
                hashTable.add(-1);
        }

        private void printPrime(long n) {
            for (long i = 0; i <= n; i++)
                if (!isPrime.get((int) i))
                    System.out.print(i + ", ");
            System.out.println();
        }

        /* Function to insert value in hash table */
        public void insert(int value) {

            if (value == -1 || value == -2) {
                System.out.println("ERROR : -1 and -2 can't be inserted in the table");
            }

            if (isFull()) {
                System.out.println("ERROR : Hash Table Full");
                return;
            }

            int probe = hash1(value), offset = hash2(value); // in linear probing offset = 1;

            while (hashTable.get(probe) != -1) {
                if (-2 == hashTable.get(probe))
                    break; // insert at deleted element's location
                probe = (probe + offset) % TABLE_SIZE;
            }

            hashTable.set(probe, value);
            keysPresent += 1;
        }

        public void erase(int value) {
            /* Return if element is not present */
            if (!search(value))
                return;

            int probe = hash1(value), offset = hash2(value);

            while (hashTable.get(probe) != -1)
                if (hashTable.get(probe) == value) {
                    hashTable.set(probe, -2); // mark element as deleted (rather than unvisited(-1)).
                    keysPresent--;
                    return;
                } else
                    probe = (probe + offset) % TABLE_SIZE;

        }

        public boolean search(int value) {
            int probe = hash1(value), offset = hash2(value), initialPos = probe;
            boolean firstItr = true;

            while (true) {
                if (hashTable.get(probe) == -1) // Stop search if -1 is encountered.
                    break;
                else if (hashTable.get(probe) == value) // Stop search after finding the element.
                    return true;
                else if (probe == initialPos && !firstItr) // Stop search if one complete traversal of hash table is
                    // completed.
                    return false;
                else
                    probe = ((probe + offset) % TABLE_SIZE); // if none of the above cases occur then update the index and
                // check at it.

                firstItr = false;
            }
            return false;
        }

        /* Function to display the hash table. */
        public void print() {
            for (int i = 0; i < TABLE_SIZE; i++)
                System.out.print(hashTable.get(i) + ", ");
            System.out.println();
        }
    }
}





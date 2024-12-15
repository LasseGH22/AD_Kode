package Hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class Seperate_Chaining {
    public void main(String[] args) {
        // ArrayList that contains keys to be mapped
        ArrayList<Integer> a = new ArrayList<>();
        a.add(15);
        a.add(11);
        a.add(27);
        a.add(8);
        a.add(12);

        // Insert the keys into the hash table
        Hash h = new Hash(7); // 7 is the number of buckets in the hash table
        for (int key : a) {
            h.insertItem(key);
        }

        // Delete 12 from the hash table
        h.deleteItem(12);

        // Display the hash table
        h.displayHash();

        /*
        // Insert more items to trigger rehashing
        h.insertItem(33);
        h.insertItem(45);
        h.insertItem(19);

        // Display the hash table after rehashing
        System.out.println("\nAfter rehashing:");
        h.displayHash();
        */
    }

    class Hash {
        int BUCKET; // Number of buckets
        int numOfElements; // To track the number of elements

        // ArrayList of LinkedLists to store chains
        ArrayList<LinkedList<Integer>> table;

        // Constructor to initialize bucket count and table
        public Hash(int b) {
            this.BUCKET = b;
            this.numOfElements = 0;
            table = new ArrayList<>(BUCKET);

            // Initialize each bucket with an empty LinkedList
            for (int i = 0; i < BUCKET; i++) {
                table.add(new LinkedList<>());
            }
        }

        // Hash function to map values to key
        private int hashFunction(int x) {
            return (x % BUCKET);
        }

        // Function to calculate the current load factor
        private float getLoadFactor() {
            return (float) numOfElements / BUCKET;
        }

        // Rehashing function to double the capacity and re-insert elements
        private void rehashing() {
            int oldBucket = BUCKET;
            BUCKET = 2 * BUCKET; // Double the number of buckets
            ArrayList<LinkedList<Integer>> oldTable = table; // Store current table

            table = new ArrayList<>(BUCKET);
            numOfElements = 0; // Reset the element count

            // Initialize the new bucket with empty LinkedLists
            for (int i = 0; i < BUCKET; i++) {
                table.add(new LinkedList<>());
            }

            // Re-insert old values into the new table
            for (int i = 0; i < oldBucket; i++) {
                for (int key : oldTable.get(i)) {
                    insertItem(key); // Insert keys into the new table
                }
            }
        }

        // Inserts a key into the hash table
        public void insertItem(int key) {
            // If load factor exceeds 0.5, rehash
            while (getLoadFactor() > 0.5) {
                rehashing();
            }

            int index = hashFunction(key);
            table.get(index).add(key); // Insert the key into the bucket
            numOfElements++;
        }

        // Deletes a key from the hash table
        public void deleteItem(int key) {
            int index = hashFunction(key);

            // Find and remove the key from the table[index] LinkedList
            LinkedList<Integer> chain = table.get(index);
            if (chain.contains(key)) {
                chain.remove((Integer) key); // Remove the key
                numOfElements--;
            }
        }

        // Display the hash table
        public void displayHash() {
            for (int i = 0; i < BUCKET; i++) {
                System.out.print(i);
                for (int x : table.get(i)) {
                    System.out.print(" --> " + x);
                }
                System.out.println();
            }
        }
    }
}

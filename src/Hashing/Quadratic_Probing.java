package Hashing;

public class Quadratic_Probing {

    public static void main(String args[])
    {
        int[] arr = { 50, 700, 76, 85, 92, 73, 101 };

        int tsize = 11;
        int[] hash_table = new int[tsize];

        // Initializing the hash table
        for (int i = 0; i < tsize; i++) {
            hash_table[i] = -1;
        }

        // Function call
        hashing(hash_table, arr);
    }

    // Function to print an array
    static void printArray(int arr[])
    {

        // Iterating and printing the array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Function to implement the
    // quadratic probing
    static void hashing(int table[], int arr[])
    {

        int tsize = table.length, n = arr.length;

        // Iterating through the array
        for (int i = 0; i < n; i++) {

            // Computing the hash value
            int hv = arr[i] % tsize;

            // Insert in the table if there
            // is no collision
            if (table[hv] == -1)
                table[hv] = arr[i];
            else {

                // If there is a collision
                // iterating through all
                // possible quadratic values
                for (int j = 1; j <= tsize; j++) {

                    // Computing the new hash value
                    int t = (hv + j * j) % tsize;
                    if (table[t] == -1) {

                        // Break the loop after
                        // inserting the value
                        // in the table
                        table[t] = arr[i];
                        break;
                    }
                }
            }
        }

        printArray(table);
    }
}

package Recursion;

public class SummationFrom1ToN {
    public static int sumSeries(int n) {
        if (n == 0) return 0; // Base case
        return n + sumSeries(n - 1); // Recursive call
    }

    public static void main(String[] args) {
        int n = 10; // Example input
        System.out.println("Sum of series 1 to " + n + ": " + sumSeries(n));
    }
}

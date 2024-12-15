package Recursion;

public class Fibonacci {
    public static int fibonacci(int n) {
        if (n <= 1) return n; // Base cases: F(0) = 0, F(1) = 1
        return fibonacci(n - 1) + fibonacci(n - 2); // Recursive case
    }

    public static void main(String[] args) {
        int count = 10; // Example input, number of Fibonacci numbers to print
        System.out.print("Fibonacci Series: ");
        for (int i = 0; i < count; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}

package Recursion;

public class Factorial {
    public static int factorial(int n) {
        if (n == 0) return 1; // Base case: 0! = 1
        return n * factorial(n - 1); // Recursive case
    }

    public static void main(String[] args) {
        int number = 5; // Example input
        int result = factorial(number);
        System.out.println("Factorial of " + number + " is: " + result);
    }
}

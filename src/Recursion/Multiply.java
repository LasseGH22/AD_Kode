package Recursion;

public class Multiply {

    public static int multiply(int a, int b) {
        if (b == 0) return 0; // Base case: Anything multiplied by 0 is 0
        return a + multiply(a, b - 1); // Add \( A \) recursively \( B \) times
    }

    public static void main(String[] args) {
        int a = 7, b = 3; // Example input
        int result = multiply(a, b);
        System.out.println(a + " multiplied by " + b + " is: " + result);
    }
}


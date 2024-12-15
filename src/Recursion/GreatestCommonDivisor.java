package Recursion;

public class GreatestCommonDivisor {
    public static int gcd(int a, int b) {
        if (b == 0) return a; // Base case
        return gcd(b, a % b); // Recursive call
    }

    public static void main(String[] args) {
        int a = 48, b = 18; // Example input
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
    }
}

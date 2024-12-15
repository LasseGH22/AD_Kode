package Recursion;

public class Power {
    public static int power(int a, int b) {
        if (b == 0) return 1;
        return a * power(a, b - 1);
    }

    public static void main(String[] args) {
        int a = 2, b = 8; // Example input
        int result = power(a, b);
        System.out.println(a + " raised to the power of " + b + " is: " + result);
    }
}

package Recursion;

public class CountDigitsinInteger {
    public static int countDigits(int n) {
        if (n == 0) return 0; // Base case: No digits left
        return 1 + countDigits(n / 10); // Remove the last digit and count
    }

    public static void main(String[] args) {
        int n = 123456; // Example input
        int result = countDigits(n);
        System.out.println("The number of digits in " + n + " is: " + result);
    }
}

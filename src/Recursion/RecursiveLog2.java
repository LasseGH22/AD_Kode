package Recursion;

public class RecursiveLog2 {
    public static void main(String[] args) {
        int n = 32;
        System.out.println("Log2 af " + n + " er " + logTo(n));

    }

    private static int logTo(int n) {
        if (n < 2) return 0;

        return 1 + logTo(n / 2);
    }
}

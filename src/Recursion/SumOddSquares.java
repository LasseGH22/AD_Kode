package Recursion;

public class SumOddSquares {
    public static void main(String[] args) {
        System.out.println(sumOddSquares(5));
    }

    public static int sumOddSquares(int n) {
        if (n <= 0) return 0;
        if (n % 2 == 0) {
            n--;
        }
        return n * n + sumOddSquares(n - 2);
    }
}

package Recursion;

public class ReverseNumber {
    public static int reverse(int number, int reversed) {
        if (number == 0) return reversed; // Base case
        return reverse(number / 10, reversed * 10 + number % 10); // Recursive call
    }

    public static void main(String[] args) {
        int number = 1234; // Example input
        int reversed = reverse(number, 0);
        System.out.println("Reversed Number: " + reversed);
    }
}

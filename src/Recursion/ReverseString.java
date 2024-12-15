package Recursion;

public class ReverseString {
    public static String reverse(String s) {
        if (s.isEmpty()) return s; // Base case: Empty string
        return reverse(s.substring(1)) + s.charAt(0); // Recursive case
    }

    public static void main(String[] args) {
        String input = "recursion"; // Example input
        String reversed = reverse(input);
        System.out.println("Original String: " + input);
        System.out.println("Reversed String: " + reversed);
    }
}

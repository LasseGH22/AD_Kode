package Recursion;

public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true; // Base case: Single character or empty string
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false; // Mismatch
        return isPalindrome(s.substring(1, s.length() - 1)); // Remove outer characters and recurse
    }

    public static void main(String[] args) {
        String s = "racecar"; // Example input
        boolean result = isPalindrome(s);
        System.out.println("The string \"" + s + "\" is a palindrome: " + result);
    }
}


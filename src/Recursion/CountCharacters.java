package Recursion;

public class CountCharacters {
    public static int countChar(String s, char c) {
        if (s.isEmpty()) return 0; // Base case: Empty string
        int count = (s.charAt(0) == c) ? 1 : 0; // Count if the first character matches
        return count + countChar(s.substring(1), c); // Recurse with the rest of the string
    }

    public static void main(String[] args) {
        String s = "recursion"; // Example input
        char c = 'r'; // Character to count
        int result = countChar(s, c);
        System.out.println("The character '" + c + "' appears " + result + " times in \"" + s + "\".");
    }
}

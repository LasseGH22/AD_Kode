package Recursion;

public class CountDown {
    public static void countDown(int number) {
        if (number < 0) return; // Base case: Stop when number is less than 0
        System.out.print(number + " "); // Print the current number
        countDown(number - 1); // Recursive call with decremented number
    }

    public static void main(String[] args) {
        int start = 3; // Example input
        System.out.print("Countdown: ");
        countDown(start);
    }
}

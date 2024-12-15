package Recursion;

public class BinarySearch {
    public static int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high) return -1; // Base case
        int mid = (low + high) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return binarySearch(arr, low, mid - 1, target); // Search left
        return binarySearch(arr, mid + 1, high, target); // Search right
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int target = 7;
        int result = binarySearch(arr, 0, arr.length - 1, target);
        System.out.println("Element found at index: " + result);
    }
}

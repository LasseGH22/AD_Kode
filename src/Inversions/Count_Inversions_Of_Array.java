package Inversions;

public class Count_Inversions_Of_Array {
    static int inversionCount(int arr[]) {
        int n = arr.length;
        int invCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                // If the current element is greater than the next,
                // increment the count
                if (arr[i] > arr[j])
                    invCount++;
            }
        }
        return invCount;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        System.out.println(inversionCount(arr));
    }
}

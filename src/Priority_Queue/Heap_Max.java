package Priority_Queue;
import java.util.ArrayList;

public class Heap_Max {

    // Main method to test the MaxHeap implementation
    public void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(30);
        maxHeap.insert(15);

        maxHeap.printHeap();

        System.out.println("Extracted max: " + maxHeap.extractMax());
        maxHeap.printHeap();

        System.out.println("Extracted max: " + maxHeap.extractMax());
        maxHeap.printHeap();
    }

    public class MaxHeap {
        private ArrayList<Integer> heap;

        // Constructor to initialize the heap
        public MaxHeap() {
            heap = new ArrayList<>();
        }

        // Helper method to get the parent index
        private int getParentIndex(int index) {
            return (index - 1) / 2;
        }

        // Helper method to get the left child index
        private int getLeftChildIndex(int index) {
            return 2 * index + 1;
        }

        // Helper method to get the right child index
        private int getRightChildIndex(int index) {
            return 2 * index + 2;
        }

        // Method to add an element to the heap
        public void insert(int value) {
            heap.add(value);
            heapifyUp(heap.size() - 1);
        }

        // Method to remove and return the maximum element (root) from the heap
        public int extractMax() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }

            int max = heap.get(0);
            int lastElement = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                heapifyDown(0);
            }

            return max;
        }

        // Method to perform heapify-up operation
        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = getParentIndex(index);
                if (heap.get(index) > heap.get(parentIndex)) {
                    swap(index, parentIndex);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }

        // Method to perform heapify-down operation
        private void heapifyDown(int index) {
            int size = heap.size();

            while (true) {
                int largest = index;
                int leftChildIndex = getLeftChildIndex(index);
                int rightChildIndex = getRightChildIndex(index);

                if (leftChildIndex < size && heap.get(leftChildIndex) > heap.get(largest)) {
                    largest = leftChildIndex;
                }

                if (rightChildIndex < size && heap.get(rightChildIndex) > heap.get(largest)) {
                    largest = rightChildIndex;
                }

                if (largest != index) {
                    swap(index, largest);
                    index = largest;
                } else {
                    break;
                }
            }
        }

        // Helper method to swap two elements in the heap
        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        // Method to print the heap
        public void printHeap() {
            System.out.println(heap);
        }
    }

}

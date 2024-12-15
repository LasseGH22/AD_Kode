package Priority_Queue;
import java.util.ArrayList;

public class Heap_Min {

    public void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(30);
        minHeap.insert(15);

        minHeap.printHeap();

        System.out.println("Extracted min: " + minHeap.extractMin());
        minHeap.printHeap();

        System.out.println("Extracted min: " + minHeap.extractMin());
        minHeap.printHeap();
    }

    public class MinHeap {
        private ArrayList<Integer> heap;

        public MinHeap() {
            heap = new ArrayList<>();
        }

        private int getParentIndex(int index) {
            return (index - 1) / 2;
        }

        private int getLeftChildIndex(int index) {
            return 2 * index + 1;
        }

        private int getRightChildIndex(int index) {
            return 2 * index + 2;
        }

        public void insert(int value) {
            heap.add(value);
            heapifyUp(heap.size() - 1);
        }

        // Method to remove and return the minimum element (root) from the heap
        public int extractMin() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }

            int min = heap.get(0);
            int lastElement = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                heapifyDown(0);
            }

            return min;
        }

        // Method to perform heapify-up operation
        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = getParentIndex(index);
                if (heap.get(index) < heap.get(parentIndex)) {
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
                int smallest = index;
                int leftChildIndex = getLeftChildIndex(index);
                int rightChildIndex = getRightChildIndex(index);

                if (leftChildIndex < size && heap.get(leftChildIndex) < heap.get(smallest)) {
                    smallest = leftChildIndex;
                }

                if (rightChildIndex < size && heap.get(rightChildIndex) < heap.get(smallest)) {
                    smallest = rightChildIndex;
                }

                if (smallest != index) {
                    swap(index, smallest);
                    index = smallest;
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

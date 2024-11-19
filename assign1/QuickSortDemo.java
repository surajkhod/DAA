import java.util.Random;

public class QuickSortDemo {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000); // Random integers from 0 to 999
        }
        return arr;
    }

    public static void main(String[] args) {
        int size = 500;
        int[] data = generateRandomArray(size);

        long startTime = System.nanoTime();
        quickSort(data, 0, data.length - 1);
        long endTime = System.nanoTime();

        System.out.println("Time taken by QuickSort: " + (endTime - startTime) + " ns");
    }
}

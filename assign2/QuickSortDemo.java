import java.util.Random;

public class QuickSortDemo {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = 500; // Size of the array
        int[] arrBestCase = new int[n];
        int[] arrWorstCase = new int[n];

        // Best case: Sorted array
        for (int i = 0; i < n; i++) {
            arrBestCase[i] = i;
        }

        // Worst case: Reverse sorted array
        for (int i = 0; i < n; i++) {
            arrWorstCase[i] = n - i;
        }

        // Measure time for best case
        long startBest = System.nanoTime();
        quickSort(arrBestCase, 0, arrBestCase.length - 1);
        long endBest = System.nanoTime();
        System.out.println("Time taken for best case: " + (endBest - startBest) + " nanoseconds");

        // Measure time for worst case
        long startWorst = System.nanoTime();
        quickSort(arrWorstCase, 0, arrWorstCase.length - 1);
        long endWorst = System.nanoTime();
        System.out.println("Time taken for worst case: " + (endWorst - startWorst) + " nanoseconds");
    }
}

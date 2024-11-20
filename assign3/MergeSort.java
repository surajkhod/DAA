// PART A : Implement Merge Sort using divide and conquer strategy. Note the time required
// for best and worst case situations on large number of values.
// PART B: Write a function to demonstrate Crossover of two chromosome representing
// solution of Traveling Salesperson Problem (TSP)


import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        // Generate more than 500 inputs for best and worst case scenarios
        int[] bestCase = generateBestCaseArray(1024);
        int[] worstCase = generateWorstCaseArray(1024);

        // Measure the time taken for best and worst cases
        measureTimeTaken(bestCase, "Best Case");
        measureTimeTaken(worstCase, "Worst Case");
    }

    private static void measureTimeTaken(int[] arr, String caseName) {
        long startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println(caseName + " Time Taken: " + duration + " nanoseconds");
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i, j, k;
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        i = 0;
        j = 0;
        k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    private static int[] generateBestCaseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateWorstCaseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
}
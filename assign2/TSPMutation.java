import java.util.Arrays;
import java.util.Random;

public class TSPMutation {
    public static void main(String[] args) {
        int[] chromosome = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println("Original Chromosome: " + Arrays.toString(chromosome));

        int[] mutatedChromosome = performMutation(chromosome);
        System.out.println("Mutated Chromosome: " + Arrays.toString(mutatedChromosome));
    }

    public static int[] performMutation(int[] chromosome) {
        int[] result = Arrays.copyOf(chromosome, chromosome.length);

        // Randomly select two indexes for mutation
        int index1 = getRandomIndex(result.length);
        int index2 = getRandomIndex(result.length);

        // Swap the values at the selected indexes
        swap(result, index1, index2);

        return result;
    }

    private static int getRandomIndex(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
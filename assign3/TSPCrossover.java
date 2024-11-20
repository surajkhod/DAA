// PART A : Implement Merge Sort using divide and conquer strategy. Note the time required
// for best and worst case situations on large number of values.
// PART B: Write a function to demonstrate Crossover of two chromosome representing
// solution of Traveling Salesperson Problem (TSP)


import java.util.Arrays;
import java.util.Random;

public class TSPCrossover {
    public static void main(String[] args) {
        int[] chromosome1 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] chromosome2 = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

        System.out.println("Chromosome 1: " + Arrays.toString(chromosome1));
        System.out.println("Chromosome 2: " + Arrays.toString(chromosome2));

        int[] offspring1 = performCrossover(chromosome1, chromosome2);
        int[] offspring2 = performCrossover(chromosome2, chromosome1);

        System.out.println("Offspring 1: " + Arrays.toString(offspring1));
        System.out.println("Offspring 2: " + Arrays.toString(offspring2));
    }

    public static int[] performCrossover(int[] parent1, int[] parent2) {
        int[] offspring = new int[parent1.length];
        boolean[] used = new boolean[parent1.length];

        // Randomly select a crossover point
        int crossoverPoint = getRandomIndex(parent1.length);

        // Copy the first part of parent1 to the offspring
        for (int i = 0; i < crossoverPoint; i++) {
            offspring[i] = parent1[i];
            used[parent1[i]] = true;
        }

        // Fill the remaining part of the offspring with unique values from parent2
        int j = 0;
        for (int i = crossoverPoint; i < parent1.length; i++) {
            while (used[parent2[j]]) {
                j++;
            }
            offspring[i] = parent2[j];
            used[parent2[j]] = true;
            j++;
        }

        return offspring;
    }

    private static int getRandomIndex(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
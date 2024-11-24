import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPGeneticAlgorithm {
    static int N = 4; // Number of cities
    static int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };
    static Random random = new Random();

    public static List<Integer> generateRandomRoute() {
        List<Integer> route = new ArrayList<>();
        for (int i = 0; i < N; i++) route.add(i);
        Collections.shuffle(route);
        return route;
    }

    public static int calculateCost(List<Integer> route) {
        int cost = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            cost += graph[route.get(i)][route.get(i + 1)];
        }
        cost += graph[route.get(route.size() - 1)][route.get(0)]; // Return to start
        return cost;
    }

    public static List<Integer> crossover(List<Integer> parent1, List<Integer> parent2) {
        int start = random.nextInt(N);
        int end = random.nextInt(N);
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        List<Integer> child = new ArrayList<>(Collections.nCopies(N, -1));
        for (int i = start; i <= end; i++) {
            child.set(i, parent1.get(i));
        }

        int index = 0;
        for (int i = 0; i < N; i++) {
            if (!child.contains(parent2.get(i))) {
                while (child.get(index) != -1) index++;
                child.set(index, parent2.get(i));
            }
        }
        return child;
    }

    public static void mutate(List<Integer> route) {
        int pos1 = random.nextInt(N);
        int pos2 = random.nextInt(N);
        Collections.swap(route, pos1, pos2);
    }

    public static List<Integer> geneticAlgorithm(int generations, int populationSize) {
        List<List<Integer>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            population.add(generateRandomRoute());
        }

        for (int generation = 0; generation < generations; generation++) {
            Collections.sort(population, (r1, r2) -> Integer.compare(calculateCost(r1), calculateCost(r2)));

            // Crossover and mutation
            for (int i = populationSize / 2; i < populationSize; i++) {
                List<Integer> parent1 = population.get(random.nextInt(populationSize / 2));
                List<Integer> parent2 = population.get(random.nextInt(populationSize / 2));
                List<Integer> child = crossover(parent1, parent2);
                if (random.nextDouble() < 0.2) { // Mutation probability
                    mutate(child);
                }
                population.set(i, child);
            }
        }

        // Return the best solution
        return population.get(0);
    }

    public static void main(String[] args) {
        int generations = 100;
        int populationSize = 10;

        List<Integer> bestRoute = geneticAlgorithm(generations, populationSize);
        System.out.println("Best route (Genetic Algorithm): " + bestRoute);
        System.out.println("Cost: " + calculateCost(bestRoute));
    }
}

import java.util.Arrays;

public class TSPBranchAndBound {
    static int N = 4; // Number of cities
    static int INF = Integer.MAX_VALUE;

    public static int tsp(int[][] graph) {
        boolean[] visited = new boolean[N];
        visited[0] = true; // Start from the first city
        return tspRec(graph, visited, 0, 1, 0);
    }

    private static int tspRec(int[][] graph, boolean[] visited, int currPos, int count, int cost) {
        if (count == N && graph[currPos][0] > 0) {
            return cost + graph[currPos][0]; // Return cost to return to the starting city
        }

        int minCost = INF;

        // Explore all cities
        for (int i = 0; i < N; i++) {
            if (!visited[i] && graph[currPos][i] > 0) {
                visited[i] = true;
                minCost = Math.min(minCost,
                        tspRec(graph, visited, i, count + 1, cost + graph[currPos][i]));
                visited[i] = false; // Backtrack
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        System.out.println("Minimum cost (Branch and Bound): " + tsp(graph));
    }
}

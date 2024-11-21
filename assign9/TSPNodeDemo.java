// For implementing Travelling Salesman problem using LC branch and bound technique, design

// the following 3 functions
// i) Generate the start (first) node for the LCBB TSP
// ii) Generate all the children of a given node
// iii) To check whether a given node is a leaf node


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPNodeDemo {

    static class Node {
        int[][] reducedMatrix; // Reduced cost matrix
        int cost;              // Cost of the current node
        int city;              // Current city represented by the node
        int level;             // Level of the node in the state space tree
        boolean[] visited;     // Array to track visited cities
        List<Integer> path;    // Path taken so far

        Node(int[][] reducedMatrix, int cost, int city, int level, boolean[] visited, List<Integer> path) {
            this.reducedMatrix = reducedMatrix;
            this.cost = cost;
            this.city = city;
            this.level = level;
            this.visited = visited.clone();
            this.path = new ArrayList<>(path);
        }
    }

    // Generate the start node
    public static Node generateStartNode(int[][] graph) {
        int n = graph.length;
        int[][] reducedMatrix = reduceMatrix(graph);
        int cost = calculateCost(graph, reducedMatrix);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        return new Node(reducedMatrix, cost, 0, 1, visited, path);
    }

    // Reduce the matrix and return the reduced matrix
    public static int[][] reduceMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] reducedMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            reducedMatrix[i] = Arrays.copyOf(matrix[i], n);
        }
        // Row reduction
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (reducedMatrix[i][j] < min) {
                    min = reducedMatrix[i][j];
                }
            }
            if (min != Integer.MAX_VALUE && min != 0) {
                for (int j = 0; j < n; j++) {
                    reducedMatrix[i][j] -= min;
                }
            }
        }
        // Column reduction
        for (int j = 0; j < n; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (reducedMatrix[i][j] < min) {
                    min = reducedMatrix[i][j];
                }
            }
            if (min != Integer.MAX_VALUE && min != 0) {
                for (int i = 0; i < n; i++) {
                    reducedMatrix[i][j] -= min;
                }
            }
        }
        return reducedMatrix;
    }

    // Calculate the cost of the reduced matrix
    public static int calculateCost(int[][] originalMatrix, int[][] reducedMatrix) {
        int cost = 0;
        for (int i = 0; i < reducedMatrix.length; i++) {
            for (int j = 0; j < reducedMatrix[i].length; j++) {
                if (reducedMatrix[i][j] != originalMatrix[i][j]) {
                    cost += originalMatrix[i][j] - reducedMatrix[i][j];
                }
            }
        }
        return cost;
    }

    // Generate all children of a given node
    public static List<Node> generateChildren(Node currentNode, int[][] graph) {
        List<Node> children = new ArrayList<>();
        int n = graph.length;

        for (int nextCity = 0; nextCity < n; nextCity++) {
            if (!currentNode.visited[nextCity]) {
                // Clone and prepare data for the new child node
                int[][] newMatrix = Arrays.stream(currentNode.reducedMatrix)
                                          .map(int[]::clone)
                                          .toArray(int[][]::new);

                // Block travel to/from the current city and the next city
                for (int i = 0; i < n; i++) {
                    newMatrix[currentNode.city][i] = Integer.MAX_VALUE;
                    newMatrix[i][nextCity] = Integer.MAX_VALUE;
                }
                newMatrix[nextCity][0] = Integer.MAX_VALUE;

                // Reduce the matrix for the child node
                int[][] reducedMatrix = reduceMatrix(newMatrix);
                int cost = currentNode.cost + graph[currentNode.city][nextCity] + calculateCost(graph, reducedMatrix);

                boolean[] visited = currentNode.visited.clone();
                visited[nextCity] = true;

                List<Integer> path = new ArrayList<>(currentNode.path);
                path.add(nextCity);

                children.add(new Node(reducedMatrix, cost, nextCity, currentNode.level + 1, visited, path));
            }
        }
        return children;
    }

    // Check if the given node is a leaf node
    public static boolean isLeafNode(Node node, int totalCities) {
        return node.level == totalCities;
    }

    // Main function to test the functions
    public static void main(String[] args) {
        // Example adjacency matrix (graph)
        int[][] graph = {
            {Integer.MAX_VALUE, 10, 15, 20},
            {10, Integer.MAX_VALUE, 35, 25},
            {15, 35, Integer.MAX_VALUE, 30},
            {20, 25, 30, Integer.MAX_VALUE}
        };

        // Generate the start node
        Node startNode = generateStartNode(graph);
        System.out.println("Start Node Cost: " + startNode.cost);
        System.out.println("Start Node Path: " + startNode.path);

        // Generate children of the start node
        List<Node> children = generateChildren(startNode, graph);
        for (Node child : children) {
            System.out.println("Child Node Cost: " + child.cost + ", Path: " + child.path);
        }

        // Check if a node is a leaf node
        System.out.println("Is Leaf Node (Start Node): " + isLeafNode(startNode, graph.length));
    }
}

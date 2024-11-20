// For implementing Travelling Salesman problem using LC branch and bound technique, design

// the following 3 functions
// i) Generate the start (first) node for the LCBB TSP
// ii) Generate all the children of a given node
// iii) To check whether a given node is a leaf node


class Node {
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

public static Node generateStartNode(int[][] graph) {
    int n = graph.length;

    // Create an initial reduced cost matrix
    int[][] reducedMatrix = reduceMatrix(graph);

    // Initial cost is the sum of reductions
    int cost = calculateCost(graph, reducedMatrix);

    // Start from the first city (city 0)
    boolean[] visited = new boolean[n];
    visited[0] = true;

    List<Integer> path = new ArrayList<>();
    path.add(0); // Start from city 0

    return new Node(reducedMatrix, cost, 0, 1, visited, path);
}

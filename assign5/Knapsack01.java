public class Knapsack01 {
    public static void main(String[] args) {
        int n = 4;
        int[] p = {10, 10, 12, 18};
        int[] w = {2, 4, 6, 9};
        int m = 15;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (w[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], p[i - 1] + dp[i - 1][j - w[i - 1]]);
                }
            }
        }

        System.out.println("Maximum value: " + dp[n][m]);
        printSolution(dp, w, p, n, m);
    }

    private static void printSolution(int[][] dp, int[] w, int[] p, int n, int m) {
        System.out.println("Selected items:");
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                System.out.println("Item " + i + ": weight = " + w[i - 1] + ", value = " + p[i - 1]);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
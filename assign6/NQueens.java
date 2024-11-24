package assign5;
public class NQueens {

    public static boolean solveNQueens(int[][] board, int col, int n) {
        if (col >= n) {
            return true; // All queens placed
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1; // Place queen
                if (solveNQueens(board, col + 1, n)) {
                    return true; // Recur to place next queen
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false; // No place for this queen
    }

    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check this row to the left
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) return false;
        }

        // Check upper diagonal on the left
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Check lower diagonal on the left
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    public static void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] sizes = {4, 5, 6, 7, 8};
        for (int n : sizes) {
            int[][] board = new int[n][n];
            long startTime = System.nanoTime();
            if (solveNQueens(board, 0, n)) {
                long endTime = System.nanoTime();
                System.out.println("\nSolution for " + n + "-Queens:");
                printBoard(board, n);
                System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
            } else {
                System.out.println("\nNo solution exists for " + n + "-Queens.");
            }
        }
    }
}

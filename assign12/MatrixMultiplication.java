import java.util.Random;

public class MatrixMultiplication {

    private static final int MATRIX_SIZE = 500; // Define matrix size

    // Sequential Matrix Multiplication
    public static int[][] sequentialMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // Multithreaded Matrix Multiplication
    static class Worker implements Runnable {
        private final int row;
        private final int[][] A;
        private final int[][] B;
        private final int[][] result;

        public Worker(int row, int[][] A, int[][] B, int[][] result) {
            this.row = row;
            this.A = A;
            this.B = B;
            this.result = result;
        }

        @Override
        public void run() {
            int n = A.length;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[row][j] += A[row][k] * B[k][j];
                }
            }
        }
    }

    public static int[][] parallelMultiply(int[][] A, int[][] B) throws InterruptedException {
        int n = A.length;
        int[][] result = new int[n][n];
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(new Worker(i, A, B, result));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return result;
    }

    // Generate random matrix
    public static int[][] generateMatrix(int size) {
        int[][] matrix = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] A = generateMatrix(MATRIX_SIZE);
        int[][] B = generateMatrix(MATRIX_SIZE);

        // Sequential Multiplication
        long startSequential = System.currentTimeMillis();
        sequentialMultiply(A, B);
        long endSequential = System.currentTimeMillis();

        System.out.println("Sequential Time: " + (endSequential - startSequential) + " ms");

        // Parallel Multiplication
        long startParallel = System.currentTimeMillis();
        parallelMultiply(A, B);
        long endParallel = System.currentTimeMillis();

        System.out.println("Parallel Time: " + (endParallel - startParallel) + " ms");
    }
}

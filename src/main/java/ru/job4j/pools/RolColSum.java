package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int i = 0; i < n; i++) {
            sums[i] = computeSums(matrix, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        CompletableFuture<Sums>[] futures = new CompletableFuture[n];
        for (int i = 0; i < n; i++) {
            int el = i;
            futures[i] = CompletableFuture.supplyAsync(() -> computeSums(matrix, el));
        }
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures);
        completableFuture.join();
        for (int i = 0; i < n; i++) {
            sums[i] = futures[i].join();
        }
        return sums;
    }

    private static Sums computeSums(int[][] matrix, int index) {
        int n = matrix.length;
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < n; j++) {
            rowSum += matrix[index][j];
            colSum += matrix[j][index];
        }
        return new Sums(rowSum, colSum);
    }
}
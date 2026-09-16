package ru.university.lab2.matrix;

public class MatrixTask {

    public void run() {
        int[][] m1 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] m2 = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        System.out.println("1. Матрица M1:");
        display(m1);

        System.out.println("2. Транспонированная M1:");
        display(transpose(m1));

        System.out.println("3. Умножение M1 * M2:");
        int[][] result = multiply(m1, m2);
        if (result != null) {
            display(result);
        }
    }

    private void display(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                // %4d 4 знакоместа
                System.out.printf("%4d", element);
            }
            System.out.println();
        }
    }

    private int[][] transpose(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = m[i][j];
            }
        }
        return result;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            System.out.println("Ошибка: количество столбцов A не равно количеству строк B!");
            return null;
        }

        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
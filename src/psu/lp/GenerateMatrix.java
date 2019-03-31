package psu.lp;

import java.io.*;
import java.util.*;

public class GenerateMatrix {

    public static void main(String[] args) throws IOException {
        File outputFile = new File("matrix.txt");

        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность: ");
        int n = in.nextInt();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = ((int) (Math.random() * n) % 2);
                matrix[i][j] = matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
        }
        printMatrix(n, matrix);
        writeToFile(outputFile, n, matrix);
        System.out.println("Матрица записана в файл!");
    }

    private static void writeToFile(File outputFile, int n, int[][] matrix) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)))) {
            String count = String.valueOf(n);
            writer.write(count, 0, count.length());
            writer.write('\n');
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - 1; j++) {
                    writer.write(String.valueOf(matrix[i][j]));
                    writer.write(' ');
                }
                writer.write(String.valueOf(matrix[i][n-1]));
                writer.write('\n');
            }
        }
    }

    private static void printMatrix(int n, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

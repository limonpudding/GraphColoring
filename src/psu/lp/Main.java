package psu.lp;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int nodeCount;
        int[][] matrix;

        File inputFile = new File("test.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)))) {
            String line = reader.readLine();
            nodeCount = Integer.parseInt(line);
            matrix = new int[nodeCount][nodeCount + 1];
            for (int i = 0; i < nodeCount; i++) {
                line = reader.readLine();
                String[] temp = line.split(" ");
                for (int j = 0; j < nodeCount; j++) {
                    matrix[i][j] = Integer.parseInt(temp[j]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ошибка чтения файла!");
            return;
        }
        for (int i = 0; i < nodeCount; i++) {
            matrix[i][i] = 1;
        }

        System.out.println("Считанная матрица:");
        printMatrix(nodeCount, matrix);

        Map<Integer, ArrayList<Integer>> colours = new HashMap<>(); //Массив, который хранит группы вершин с одинаковыми цветами

        for (int i = 0; i < nodeCount; i++) {
            if ((matrix[i][nodeCount] != 1)) { //если вершина окрашена, то ничего не делаем
                ArrayList<Integer> colorI = new ArrayList<>(); //список вершин одного цвета
                colorI.add(i);
                for (int j = 0; j < nodeCount; j++) {
                    if ((matrix[i][j] == 0) && (matrix[j][nodeCount] != 1)) {
                        colorI.add(j);
                        matrix[j][nodeCount] = 1;
                        for (int k = 0; k < nodeCount; k++) {
                            matrix[i][k] = (matrix[i][k] + matrix[j][k]) > 0 ? 1 : 0;
                        }
                    }
                }
                colours.put(i, colorI);
                matrix[i][nodeCount] = 1;
            }
        }
        printResultPretty(colours);
    }

    private static void printMatrix(int nodeCount, int[][] matrix) {
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount + 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printResultPretty(Map<Integer, ArrayList<Integer>> result) {
        int number = 0;
        for (Map.Entry entry : result.entrySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Color ").append(++number).append(": ");
            ArrayList<Integer> vertexes = (ArrayList<Integer>) entry.getValue();
            sb.append(vertexes.get(0));
            for (int i = 1; i < vertexes.size(); i++) {
                sb.append(", ")
                        .append(vertexes.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}

package ru.university.lab2.arrays;

import java.util.Random;
import java.util.Arrays;

public class ArrayTask {

    public void run() {
        int[] numbers = fillRandom(10);
        printArray("Исходный массив", numbers);
        printStats(numbers);
        sortArray(numbers);
        printArray("После сортировки", numbers);
        compareArrays();
    }

    private int[] fillRandom(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100); // Числа от 0 до 99
        }
        return arr;
    }

    private void printStats(int[] arr) {
        if (arr.length == 0) return;

        int min = arr[0];
        int max = arr[0];
        long sum = 0;

        for (int x : arr) {
            if (x < min) min = x;
            if (x > max) max = x;
            sum += x;
        }
        double avg = (double) sum / arr.length;

        System.out.println("2. Статистика:");
        System.out.println("   Min: " + min + ", Max: " + max + ", Avg: " + avg);
    }

    private void sortArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void compareArrays() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};

        System.out.println("4. Сравнение массивов {1,2,3} и {1,2,3}:");
        // сравнивает ссылки
        System.out.println("   arr1 == arr2: " + (arr1 == arr2));
        // результат false, так как это два разных объекта в памяти

        // .equals() у массивов работает так же, как ==
        System.out.println("   arr1.equals(arr2): " + arr1.equals(arr2));
        // результат false, массивы не переопределяют метод equals

        // Arrays.equals() сравнивает содержимое
        System.out.println("   Arrays.equals(arr1, arr2): " + Arrays.equals(arr1, arr2));
        // результат true, так как числа внутри одинаковые
    }

    private void printArray(String title, int[] arr) {
        System.out.print(title + ": [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : ", "));
        }
        System.out.println("]");
    }
}
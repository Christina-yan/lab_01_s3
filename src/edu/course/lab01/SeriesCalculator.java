package edu.course.lab01;

public class SeriesCalculator {

    private static final double EPSILON = 1e-6;

    public static void run() {
        double sum = 0.0;
        int n = 2;
        int count = 0;
        int lastN = 0;

        while (true) {
            double term = 1.0 / ((double) n * n + n - 2);

            if (Math.abs(term) < EPSILON) {
                break;
            }

            sum += term;
            lastN = n;
            count++;
            n++;
        }

        System.out.println("Сумма: " + sum);
        System.out.println("Последний добавленный n: " + lastN);
        System.out.println("Количество добавленных членов: " + count);
    }
}
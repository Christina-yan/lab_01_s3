package ru.university.lab2.methods;

public class MethodTask {

    public void run() {
        System.out.println("1. Перегрузка методов:");
        print(10);
        print(5.5);
        print("Hello");
        print(new int[]{1, 2, 3});

        System.out.println("\n2. Varargs (сумма):");
        System.out.println("Сумма (1,2,3): " + sum(1, 2, 3));
        System.out.println("Сумма (пусто): " + sum());

        System.out.println("\n3. Возведение в степень (2^10):");
        long resIter = powIterative(2, 10);
        long resRec = powRecursive(2, 10);
        System.out.println("Итеративно: " + resIter);
        System.out.println("Рекурсивно: " + resRec);
        System.out.println("Math.pow:   " + (long)Math.pow(2, 10));

        comparePerformance();
    }

    private void print(int i) { System.out.println("Метод int: " + i); }
    private void print(double d) { System.out.println("Метод double: " + d); }
    private void print(String s) { System.out.println("Метод String: " + s); }
    private void print(int[] arr) { System.out.println("Метод int[] (длина: " + arr.length + ")"); }

    private int sum(int... numbers) {
        int s = 0;
        for (int n : numbers) s += n;
        return s;
    }

    private long powIterative(int base, int exp) {
        long res = 1;
        for (int i = 0; i < exp; i++) res *= base;
        return res;
    }

    private long powRecursive(int base, int exp) {
        if (exp == 0) return 1;
        if (exp == 1) return base;
        return base * powRecursive(base, exp - 1);
    }

    private void comparePerformance() {
        /*
         * итеративная реализация (цикл) обычно работает быстрее и потребляет меньше памяти
         * при каждом рекурсивном вызове в памяти создается новый "фрейм" в стеке,
         * куда сохраняются аргументы и адрес возврата. если степень будет очень большой,
         * рекурсия приведет к ошибке StackOverflowError, в то время как цикл будет
         * просто работать, используя одну и ту же переменную счетчика.
         */
        System.out.println("\n4. Цикл быстрее рекурсии, так как нет накладных расходов на создание фреймов в стеке.");
    }
}
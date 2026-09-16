package ru.university.lab2.floatpoint;

public class FloatTask {
    public void run() {
        precisionError();
        sumTenTimes();
        comparisonWithEpsilon();
        specialValues();
        roundingDemo();
    }

    private void precisionError() {
        System.out.println("1. Ошибка точности: 0.1 + 0.2 = " + (0.1 + 0.2));
        // числа 0.1 и 0.2 не могут быть точно представлены в двоичном виде
        // из-за этого появляются ошибки в младших разрядах
    }

    private void sumTenTimes() {
        double sum = 0;
        for (int i = 0; i < 10; i++) sum += 0.1;
        System.out.println("2. 0.1 сложенное 10 раз == 1.0: " + (sum == 1.0));
        System.out.println("Результат суммы: " + sum);
    }

    private void comparisonWithEpsilon() {
        double a = 0.1 + 0.2;
        double b = 0.3;
        double epsilon = 1e-10;
        boolean isEqual = Math.abs(a - b) < epsilon;
        System.out.println("3. Сравнение с epsilon (1e-10): " + isEqual);
    }

    private void specialValues() {
        System.out.println("4. Специальные значения:");
        double inf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;
        System.out.println("Infinity: " + inf);
        System.out.println("NaN: " + nan);
        System.out.println("NaN == NaN: " + (nan == nan)); // false
    }

    private void roundingDemo() {
        double val = 2.7;
        double negVal = -2.7;
        System.out.println("5. Округление (2.7 / -2.7):");
        System.out.println("(int): " + (int)val + " / " + (int)negVal);
        System.out.println("round: " + Math.round(val) + " / " + Math.round(negVal));
        System.out.println("floor: " + Math.floor(val) + " / " + Math.floor(negVal));
        System.out.println("ceil:  " + Math.ceil(val) + " / " + Math.ceil(negVal));
    }
}
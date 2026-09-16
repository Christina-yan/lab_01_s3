package ru.university.lab2.numbers;

public class IntegerTask {

    public void run() {
        printLimits();
        overflowStep();
        multiplyComparison();
        divisionBehavior();
        castingImpact();
        charOperation();
        overflowMethodDemo();
    }

    private void printLimits() {
        System.out.println("1. Лимиты типов:");
        System.out.println("Byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        // эти значения определяются количеством выделенных бит в памяти
    }

    private void overflowStep() {
        int res = Integer.MAX_VALUE + 1;
        System.out.println("2. MAX_VALUE + 1 = " + res);
        // циклическое переполнение.
        // в двоичном представлении прибавление единицы к
        // максимальному числу дает минимальное.
    }

    private void multiplyComparison() {
        int iRes = Integer.MAX_VALUE * 2;
        long lRes = (long) Integer.MAX_VALUE * 2;
        System.out.println("3. Умножение на 2: int = " + iRes + ", long = " + lRes);
        // в int результат обрезался до 32 бит, получив -2
        // в long результат корректный, так как 64 бита вмещают это значение
    }

    private void divisionBehavior() {
        System.out.println("4. Деление:");
        System.out.println("5 / 2 = " + (5 / 2));     // 2
        System.out.println("-5 / 2 = " + (-5 / 2));     // -2
        System.out.println("5 % 2 = " + (5 % 2));     // 1
        System.out.println("-5 % 2 = " + (-5 % 2));     // -1
        // целочисленное деление отбрасывает дробную часть
        // остаток в java сохраняет знак делимого
    }

    private void castingImpact() {
        long big = Integer.MAX_VALUE + 10L;
        int small = (int) big;
        System.out.println("5. Приведение long к int: " + small);
        // при приведении типов (casting) старшие 32 бита отбросились
    }

    private void charOperation() {
        char c = 'A';
        System.out.println("6. Char арифметика: " + c + " + 1 = " + (char)(c + 1));
        // char — это 16-битное число Unicode, к нему можно прибавлять числа
    }

    private void overflowMethodDemo() {
        System.out.println("7. Проверка переполнения:");
        testSum(Integer.MAX_VALUE, 1);
        testSum(100, 200);
    }

    private void testSum(int a, int b) {
        boolean overflow = isOverflow(a, b);
        System.out.println(a + " + " + b + " -> Переполнение: " + overflow);
    }

    private boolean isOverflow(int a, int b) {
        int res = a + b;
        if (a > 0 && b > 0 && res < 0) return true;
        if (a < 0 && b < 0 && res >= 0) return true;
        return false;
    }
}
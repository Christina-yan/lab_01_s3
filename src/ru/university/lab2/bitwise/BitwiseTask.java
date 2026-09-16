package ru.university.lab2.bitwise;

public class BitwiseTask {
    public void run() {
        basicOps();
        shiftDifference();
        parityCheck();
        powerOfTwoCheck();
        xorSwap();
    }

    private void basicOps() {
        int a = 0b1100;  // 12
        int b = 0b1010; // 10
        System.out.println("1. Побитовые: AND=" + (a & b) + ", OR=" + (a | b) + ", XOR=" + (a ^ b));
    }

    private void shiftDifference() {
        int n = -8;
        System.out.println("2. Сдвиги для -8:");
        System.out.println(">> 1:  " + (n >> 1) + " (сохраняет знак)");
        System.out.println(">>> 1: " + (n >>> 1) + " (беззнаковый, заполняет нулем)");
    }

    private void parityCheck() {
        int x = 7;
        boolean isEven = (x & 1) == 0;
        System.out.println("3. Число 7 четное? " + isEven);
    }

    private void powerOfTwoCheck() {
        int n = 16;
        // у степеней двойки только один бит равен 1. (n & (n-1)) зануляет этот бит.
        boolean isPower = (n > 0) && ((n & (n - 1)) == 0);
        System.out.println("4. Число 16 - степень 2? " + isPower);
    }

    private void xorSwap() {
        int a = 5, b = 10;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("5. XOR Swap: a=" + a + ", b=" + b);
    }
}
package edu.course.lab01;

public class QuadraticEquation {

    public static void solve(double a, double b, double c) {
        if (a == 0.0) {
            System.out.println("Ошибка: коэффициент a не должен быть равен нулю "
                    + "(уравнение не является квадратным).");
            return;
        }

        double d = b * b - 4.0 * a * c;

        if (d > 0.0) {
            double sqrtD = Math.sqrt(d);
            double x1 = (-b + sqrtD) / (2.0 * a);
            double x2 = (-b - sqrtD) / (2.0 * a);
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else if (d == 0.0) {
            double x = -b / (2.0 * a);
            System.out.println("x = " + x);
        } else {
            System.out.println("Вещественных корней нет");
        }
    }
}
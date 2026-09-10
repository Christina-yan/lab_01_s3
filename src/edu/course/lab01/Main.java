package edu.course.lab01;

public class Main {

    private static final String USAGE =
            "Использование:\n" +
                    " fizzbuzz\n" +
                    " reverse <строка>\n" +
                    " quadratic <a> <b> <c>\n" +
                    " series\n" +
                    " palindrome <строка>";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println(USAGE);
            return;
        }

        String command = args[0];

        try {
            switch (command) {
                case "fizzbuzz":
                    if (args.length != 1) {
                        System.out.println(USAGE);
                        return;
                    }
                    FizzBuzz.run();
                    break;

                case "reverse":
                    if (args.length != 2) {
                        System.out.println(USAGE);
                        return;
                    }
                    System.out.println(TextTasks.reverse(args[1]));
                    break;

                case "quadratic":
                    if (args.length != 4) {
                        System.out.println(USAGE);
                        return;
                    }
                    double a = Double.parseDouble(args[1]);
                    double b = Double.parseDouble(args[2]);
                    double c = Double.parseDouble(args[3]);
                    QuadraticEquation.solve(a, b, c);
                    break;

                case "series":
                    if (args.length != 1) {
                        System.out.println(USAGE);
                        return;
                    }
                    SeriesCalculator.run();
                    break;

                case "palindrome":
                    if (args.length != 2) {
                        System.out.println(USAGE);
                        return;
                    }
                    System.out.println(TextTasks.isPalindrome(args[1]));
                    break;

                default:
                    System.out.println(USAGE);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: коэффициенты должны быть числами.");
        }
    }
}
package edu.course.lab01;

public class TextTasks {

    /**
     * Разворот строки с помощью цикла и charAt().
     * StringBuilder.reverse() и Collections.reverse() не используются.
     */
    public static String reverse(String s) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(s.length() - 1 - i);
        }
        return new String(result);
    }

    /**
     * Проверка на палиндром без создания очищенной строки.
     * Игнорируются регистр, пробелы, знаки препинания.
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char lc = s.charAt(left);
            char rc = s.charAt(right);

            if (!Character.isLetterOrDigit(lc)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(rc)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(lc) != Character.toLowerCase(rc)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}

package ru.university.lab2.strings;

public class StringTask {

    public void run() {
        demoPalindrome();
        demoReverseWords();
        demoCountChars();
        demoCaesar();
        demoLongestWord();
    }

    private void demoPalindrome() {
        String text = "А роза упала на лапу Азора!";
        boolean result = isPalindrome(text);
        System.out.println("1. Палиндром ли '" + text + "': " + result);
    }

    private boolean isPalindrome(String s) {
        if (s == null) return false;

        String clean = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
                    (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || (c >= '0' && c <= '9')) {
                if (c >= 'A' && c <= 'Z') c = (char) (c + 32);
                if (c >= 'А' && c <= 'Я') c = (char) (c + 32);
                clean += c;
            }
        }

        int left = 0;
        int right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    private void demoReverseWords() {
        String input = "кот съел мышь";
        System.out.println("2. Реверс слов: " + reverseWords(input));
    }

    private String reverseWords(String s) {
        String[] words = s.split(" ");
        String result = "";
        for (int i = words.length - 1; i >= 0; i--) {
            result += words[i];
            if (i > 0) result += " ";
        }
        return result;
    }

    private void demoCountChars() {
        String input = "Hello Java 25!";
        System.out.println("3. Анализ строки '" + input + "':");
        countAll(input);
    }

    private void countAll(String s) {
        int vowels = 0, consonants = 0, digits = 0, spaces = 0;
        String vList = "aeiouyAEIOUY";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                digits++;
            } else if (c == ' ') {
                spaces++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                boolean isVowel = false;
                for (int j = 0; j < vList.length(); j++) {
                    if (c == vList.charAt(j)) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) vowels++;
                else consonants++;
            }
        }
        System.out.println("   Гласных: " + vowels + ", Согласных: " + consonants +
                ", Цифр: " + digits + ", Пробелов: " + spaces);
    }

    private void demoCaesar() {
        String original = "Hello Caesar";
        int shift = 3;
        String encrypted = caesar(original, shift);
        String decrypted = caesar(encrypted, -shift);
        System.out.println("4. Шифр Цезаря (k=" + shift + "): " + original +
                " -> " + encrypted + " -> " + decrypted);
    }

    private String caesar(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                chars[i] = (char) ('a' + (c - 'a' + k + 26) % 26);
            } else if (c >= 'A' && c <= 'Z') {
                chars[i] = (char) ('A' + (c - 'A' + k + 26) % 26);
            }
        }
        return new String(chars);
    }

    private void demoLongestWord() {
        String input = "Поиск самого длинного слова в строке";
        System.out.println("5. Самое длинное слово: " + findLongestWord(input));
    }

    private String findLongestWord(String s) {
        String[] words = s.split(" ");
        String longest = "";
        for (String word : words) {
            String cleanWord = word.replace(",", "").replace(".", "");
            if (cleanWord.length() > longest.length()) {
                longest = cleanWord;
            }
        }
        return longest;
    }
}
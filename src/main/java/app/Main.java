package app;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== ЗАДАНИЕ 1: Рефлексия и аннотации ===");
        MyClass myClass = new MyClass();
        Invoker.invokeAnnotatedMethods(myClass);

        // Задание 2: файловая система
        FileSystemTask.execute();
    }
}
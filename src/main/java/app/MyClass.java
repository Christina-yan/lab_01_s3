package app;

public class MyClass {

    // Публичные методы
    public void publicMethod() {
        System.out.println("Вызван публичный метод");
    }

    // Защищенные методы
    @Repeat(times = 2)
    protected void protectedMethodWithParam(String text) {
        System.out.println("Защищенный метод с параметром: " + text);
    }

    protected void protectedMethod() {
        System.out.println("Защищенный метод без параметров");
    }

    // Приватные методы
    @Repeat(times = 3)
    private void privateMethodWithParams(int number, String text) {
        System.out.println("Приватный метод: число = " + number + ", текст = " + text);
    }

    @Repeat(times = 1)
    private void privateMethodNoParams() {
        System.out.println("Приватный метод без параметров");
    }
}
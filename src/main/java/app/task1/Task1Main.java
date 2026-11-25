package app.task1;

public class Task1Main {

    public static void main(String[] args) {
        Thread evenThread = new EvenThread();
        Thread oddThread = new Thread(new OddRunnable());

        evenThread.start();
        oddThread.start();
    }
}
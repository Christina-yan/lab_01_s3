package app.task1;

public class OddRunnable implements Runnable {

    @Override
    public void run() {
        int count = 0;
        int number = 1;
        while (count < 5) {
            if (number % 2 != 0) {
                System.out.println("OddRunnable: " + number);
                count++;
            }
            number++;
        }
    }
}
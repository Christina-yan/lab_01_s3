package app.task1;

public class EvenThread extends Thread {

    @Override
    public void run() {
        int count = 0;
        int number = 0;
        while (count < 5) {
            if (number % 2 == 0) {
                System.out.println("EvenThread: " + number);
                count++;
            }
            number++;
        }
    }
}
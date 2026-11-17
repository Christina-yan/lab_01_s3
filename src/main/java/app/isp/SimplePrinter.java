package app.isp;

public class SimplePrinter implements Printer {
    @Override
    public void print() {
        System.out.println("Простой принтер печатает документ.");
    }
}
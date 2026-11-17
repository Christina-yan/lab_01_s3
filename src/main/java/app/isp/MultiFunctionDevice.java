package app.isp;

public class MultiFunctionDevice implements Printer, Scanner, Fax {
    @Override
    public void print() {
        System.out.println("МФУ печатает документ.");
    }

    @Override
    public void scan() {
        System.out.println("МФУ сканирует документ.");
    }

    @Override
    public void fax() {
        System.out.println("МФУ отправляет факс.");
    }
}
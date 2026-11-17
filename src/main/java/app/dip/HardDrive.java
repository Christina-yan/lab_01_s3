package app.dip;

public class HardDrive implements Storage {
    @Override
    public void saveData() {
        System.out.println("Сохранение данных на жесткий диск (HDD).");
    }
}
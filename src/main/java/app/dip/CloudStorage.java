package app.dip;

public class CloudStorage implements Storage {
    @Override
    public void saveData() {
        System.out.println("Сохранение данных в облако (Cloud).");
    }
}
package app.dip;

public class Computer {
    private final Storage storage;

    public Computer(Storage storage) {
        this.storage = storage;
    }

    public void saveToStorage() {
        storage.saveData();
    }
}
package app.lsp;

public class Car implements EngineVehicle {
    @Override
    public void startEngine() {
        System.out.println("Двигатель автомобиля заведен.");
    }
}
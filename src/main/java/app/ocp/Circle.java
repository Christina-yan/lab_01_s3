package app.ocp;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Рисуется круг.");
    }
}
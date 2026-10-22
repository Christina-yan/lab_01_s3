package test.java.geometry2d;

import main.java.exceptions.InvalidFigureParameterException;

import java.util.Objects;

public class Circle implements Figure {

    private static final long serialVersionUID = 1L;

    private final double radius;

    public Circle(double radius) {
        validateRadius(radius);
        this.radius = radius;
    }

    private static void validateRadius(double radius) {
        if (!Double.isFinite(radius)) {
            throw new InvalidFigureParameterException("Радиус должен быть конечным числом, получено: " + radius);
        }
        if (radius <= 0) {
            throw new InvalidFigureParameterException("Радиус должен быть положительным, получено: " + radius);
        }
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle{radius=%.4f}", radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle circle)) return false;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }
}
package main.java.geometry2d;

import main.java.exceptions.InvalidFigureParameterException;

import java.util.Objects;

public class Rectangle implements Figure {

    private static final long serialVersionUID = 1L;

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        validateSide(width, "Ширина");
        validateSide(height, "Высота");
        this.width = width;
        this.height = height;
    }

    private static void validateSide(double value, String name) {
        if (!Double.isFinite(value)) {
            throw new InvalidFigureParameterException(name + " должна быть конечным числом, получено: " + value);
        }
        if (value <= 0) {
            throw new InvalidFigureParameterException(name + " должна быть положительной, получено: " + value);
        }
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return String.format("Rectangle{width=%.4f, height=%.4f}", width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle rectangle)) return false;
        return Double.compare(rectangle.width, width) == 0
                && Double.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
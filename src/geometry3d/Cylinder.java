package geometry3d;

import exceptions.InvalidFigureParameterException;
import geometry2d.Figure;

public class Cylinder {

    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height) {
        if (base == null) {
            throw new InvalidFigureParameterException("Основание цилиндра не должно быть null");
        }
        if (!Double.isFinite(height)) {
            throw new InvalidFigureParameterException("Высота должна быть конечным числом, получено: " + height);
        }
        if (height <= 0) {
            throw new InvalidFigureParameterException("Высота должна быть положительной, получено: " + height);
        }
        this.base = base;
        this.height = height;
    }

    public Figure getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return String.format("Cylinder{base=%s, height=%.4f}", base, height);
    }
}
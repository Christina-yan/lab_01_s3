package geometry3d;

import exceptions.InvalidFigureParameterException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    private static final double EPS = 1e-9;

    @Test
    void volumeWithCircleBase() {
        Cylinder cylinder = new Cylinder(new Circle(1.0), 5.0);
        // Объём = π * 1² * 5 = 5π
        assertEquals(5 * Math.PI, cylinder.volume(), EPS);
    }

    @Test
    void volumeWithRectangleBase() {
        Cylinder cylinder = new Cylinder(new Rectangle(3.0, 4.0), 2.0);
        // Объём = 12 * 2 = 24
        assertEquals(24.0, cylinder.volume(), EPS);
    }

    @Test
    void getBaseReturnsSameObject() {
        Figure base = new Circle(2.0);
        Cylinder cylinder = new Cylinder(base, 1.0);
        assertSame(base, cylinder.getBase());
    }

    @Test
    void getHeightReturnsConstructorValue() {
        Cylinder cylinder = new Cylinder(new Circle(1.0), 3.5);
        assertEquals(3.5, cylinder.getHeight(), EPS);
    }

    @Test
    void constructorRejectsNullBase() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(null, 1.0));
    }

    @Test
    void constructorRejectsZeroHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(new Circle(1.0), 0.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(new Circle(1.0), -2.0));
    }

    @Test
    void constructorRejectsNaNHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(new Circle(1.0), Double.NaN));
    }

    @Test
    void constructorRejectsInfiniteHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Cylinder(new Circle(1.0), Double.POSITIVE_INFINITY));
    }

    @Test
    void toStringContainsTypeAndParameters() {
        Cylinder cylinder = new Cylinder(new Circle(1.0), 2.0);
        String s = cylinder.toString();
        assertTrue(s.contains("Cylinder"));
        assertTrue(s.contains("Circle"));
        assertTrue(s.contains("2.0") || s.contains("2,0"));
    }
}
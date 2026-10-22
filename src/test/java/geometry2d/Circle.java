package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private static final double EPS = 1e-9;

    @Test
    void areaOfRadiusOneIsPi() {
        Circle circle = new Circle(1.0);
        assertEquals(Math.PI, circle.area(), EPS);
    }

    @Test
    void perimeterOfRadiusOneIsTwoPi() {
        Circle circle = new Circle(1.0);
        assertEquals(2 * Math.PI, circle.perimeter(), EPS);
    }

    @Test
    void areaOfRadiusTwo() {
        Circle circle = new Circle(2.0);
        assertEquals(4 * Math.PI, circle.area(), EPS);
    }

    @Test
    void constructorRejectsZeroRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(0.0));
    }

    @Test
    void constructorRejectsNegativeRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(-1.0));
    }

    @Test
    void constructorRejectsNaN() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.NaN));
    }

    @Test
    void constructorRejectsInfiniteRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }

    @Test
    void getterReturnsRadius() {
        Circle circle = new Circle(3.5);
        assertEquals(3.5, circle.getRadius(), EPS);
    }

    @Test
    void toStringContainsTypeAndRadius() {
        Circle circle = new Circle(3.5);
        String s = circle.toString();
        assertTrue(s.contains("Circle"));
        assertTrue(s.contains("3.5") || s.contains("3,5"));
    }
}
package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    private static final double EPS = 1e-9;

    // --- Задание № 2: Площадь и периметр ---

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
    void areaAndPerimeterOfRadiusTwo() {
        Circle circle = new Circle(2.0);
        assertEquals(4 * Math.PI, circle.area(), EPS); // Pi * r^2
        assertEquals(4 * Math.PI, circle.perimeter(), EPS); // 2 * Pi * r
    }

    // --- Задание № 3: Негативные сценарии ---

    @Test
    void constructorRejectsZeroRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(0.0));
    }

    @Test
    void constructorRejectsNegativeRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(-1.0));
    }

    @Test
    void constructorRejectsNaNRRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityRadius() {
        assertThrows(InvalidFigureParameterException.class, () -> new Circle(Double.NEGATIVE_INFINITY));
    }

    // --- Задание № 4: Геттеры, toString, equals, hashCode ---

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

    @Test
    void equalsAndHashCodeWorkCorrectly() {
        Circle c1 = new Circle(5.0);
        Circle c2 = new Circle(5.0);
        Circle c3 = new Circle(4.0);

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1, c3);
    }
}
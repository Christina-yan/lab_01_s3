package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private static final double EPS = 1e-9;

    @Test
    void areaOfThreeByFourRectangleIsTwelve() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(12.0, rectangle.area(), EPS);
    }

    @Test
    void perimeterOfThreeByFourRectangleIsFourteen() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(14.0, rectangle.perimeter(), EPS);
    }

    @Test
    void squareAreaAndPerimeter() {
        Rectangle square = new Rectangle(5.0, 5.0);
        assertEquals(25.0, square.area(), EPS);
        assertEquals(20.0, square.perimeter(), EPS);
    }

    @Test
    void constructorRejectsZeroWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(0.0, 4.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, -4.0));
    }

    @Test
    void constructorRejectsNaNW() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.NaN, 4.0));
    }

    @Test
    void constructorRejectsInfiniteWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.POSITIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsNaNH() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, Double.NaN));
    }

    @Test
    void gettersReturnConstructorValues() {
        Rectangle rectangle = new Rectangle(2.5, 3.5);
        assertEquals(2.5, rectangle.getWidth(), EPS);
        assertEquals(3.5, rectangle.getHeight(), EPS);
    }

    @Test
    void toStringContainsTypeAndParameters() {
        Rectangle rectangle = new Rectangle(2.5, 3.5);
        String s = rectangle.toString();
        assertTrue(s.contains("Rectangle"));
        assertTrue(s.contains("2.5") || s.contains("2,5"));
        assertTrue(s.contains("3.5") || s.contains("3,5"));
    }
}
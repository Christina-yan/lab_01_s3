package geometry2d;

import exceptions.InvalidFigureParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private static final double EPS = 1e-9;

    // --- Задание № 2: Площадь и периметр ---

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
    void areaAndPerimeterOfSquare() {
        Rectangle square = new Rectangle(5.0, 5.0);
        assertEquals(25.0, square.area(), EPS);
        assertEquals(20.0, square.perimeter(), EPS);
    }

    // --- Задание № 3: Негативные сценарии ---

    @Test
    void constructorRejectsZeroWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(0.0, 4.0));
    }

    @Test
    void constructorRejectsZeroHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, 0.0));
    }

    @Test
    void constructorRejectsNegativeWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(-1.0, 4.0));
    }

    @Test
    void constructorRejectsNegativeHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, -4.0));
    }

    @Test
    void constructorRejectsNaNWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.NaN, 4.0));
    }

    @Test
    void constructorRejectsNaNHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, Double.NaN));
    }

    @Test
    void constructorRejectsPositiveInfinityWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.POSITIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsNegativeInfinityWidth() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(Double.NEGATIVE_INFINITY, 4.0));
    }

    @Test
    void constructorRejectsPositiveInfinityHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, Double.POSITIVE_INFINITY));
    }

    @Test
    void constructorRejectsNegativeInfinityHeight() {
        assertThrows(InvalidFigureParameterException.class, () -> new Rectangle(3.0, Double.NEGATIVE_INFINITY));
    }

    // --- Задание № 4: Геттеры, toString, equals, hashCode ---

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
        // Проверяем наличие параметров, игнорируя возможные запятые/точки
        assertTrue(s.contains("2.5") || s.contains("2,5"));
        assertTrue(s.contains("3.5") || s.contains("3,5"));
    }

    @Test
    void equalsAndHashCodeWorkCorrectly() {
        Rectangle r1 = new Rectangle(3.0, 4.0);
        Rectangle r2 = new Rectangle(3.0, 4.0);
        Rectangle r3 = new Rectangle(4.0, 3.0); // Другой прямоугольник

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, r3);
    }
}
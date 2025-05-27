// CalculatorTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator(); // Instance for tests

    @Test
    void testAdd() {
        assertEquals(8, calculator.add(5, 3));
        assertEquals(-8, calculator.add(-5, -3));
        assertEquals(-2, calculator.add(-5, 3));
        assertEquals(5, calculator.add(5, 0));
    }

    @Test
    void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-2, calculator.subtract(-5, -3));
        assertEquals(-8, calculator.subtract(-5, 3));
        assertEquals(5, calculator.subtract(5, 0));
        assertEquals(-5, calculator.subtract(0, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3));
        assertEquals(15, calculator.multiply(-5, -3));
        assertEquals(-15, calculator.multiply(-5, 3));
        assertEquals(0, calculator.multiply(5, 0));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(2, calculator.divide(-6, -3));
        assertEquals(-2, calculator.divide(-6, 3));
        assertEquals(2.5, calculator.divide(5, 2));
        assertEquals(0, calculator.divide(0, 5));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(0, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(-5, 0);
        });
    }
}

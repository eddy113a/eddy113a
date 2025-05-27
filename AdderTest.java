// AdderTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdderTest {

    private final Adder adder = new Adder(); // Instance for tests

    @Test
    void testAdd() {
        assertEquals(8.0, adder.add(5.0, 3.0), "Positive numbers sum");
        assertEquals(-8.0, adder.add(-5.0, -3.0), "Negative numbers sum");
        assertEquals(-2.0, adder.add(-5.0, 3.0), "Mixed numbers sum (negative result)");
        assertEquals(2.0, adder.add(5.0, -3.0), "Mixed numbers sum (positive result)");
        assertEquals(5.0, adder.add(5.0, 0.0), "Adding zero to positive");
        assertEquals(-5.0, adder.add(-5.0, 0.0), "Adding zero to negative");
        assertEquals(0.0, adder.add(0.0, 0.0), "Adding zero to zero");
        assertEquals(0.0, adder.add(-5.0, 5.0), "Sum resulting in zero");
        // Optional: Add a delta for double comparisons if precision issues are a concern
        // assertEquals(0.3, adder.add(0.1, 0.2), 0.0000001, "Floating point sum");
    }
}

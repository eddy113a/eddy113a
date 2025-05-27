import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Assuming NumberDivider.java is in the same directory or package
// import NumberDivider; // Not strictly necessary if in the same default package and compiled together

/**
 * JUnit 5 Test class for NumberDivider.
 *
 * To compile and run these tests if JUnit 5 is not part of a build system (e.g., Maven, Gradle):
 * 1. Download the JUnit Platform JAR files (e.g., junit-platform-console-standalone.jar)
 *    from an official source like Maven Central.
 * 2. Compile: javac -cp path/to/junit-platform-console-standalone.jar:. NumberDivider.java NumberDividerTest.java
 * 3. Run:    java -jar path/to/junit-platform-console-standalone.jar -cp . --select-class NumberDividerTest
 *
 * If using Maven, add to pom.xml:
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter-api</artifactId>
 *     <version>5.10.0</version> <!-- Use the latest version -->
 *     <scope>test</scope>
 * </dependency>
 * <dependency>
 *     <groupId>org.junit.jupiter</groupId>
 *     <artifactId>junit-jupiter-engine</artifactId>
 *     <version>5.10.0</version>
 *     <scope>test</scope>
 * </dependency>
 *
 * If using Gradle, add to build.gradle:
 * testImplementation 'org.junit.jupiter:junit-jupiter-api:5.10.0' // Use the latest version
 * testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.10.0'
 * test {
 *     useJUnitPlatform()
 * }
 */
public class NumberDividerTest {

    private static final double DELTA = 0.00001; // Precision for double comparisons

    @Test
    @DisplayName("Test divide positive numbers resulting in a whole number")
    void testDivide_positiveNumbers_wholeResult() {
        assertEquals(5.0, NumberDivider.divide(10.0, 2.0), DELTA, "10.0 / 2.0 should be 5.0");
    }

    @Test
    @DisplayName("Test divide positive numbers resulting in a fractional number")
    void testDivide_positiveNumbers_fractionalResult() {
        assertEquals(3.5, NumberDivider.divide(7.0, 2.0), DELTA, "7.0 / 2.0 should be 3.5");
    }

    @Test
    @DisplayName("Test divide negative numerator by positive denominator")
    void testDivide_negativeNumerator_positiveDenominator() {
        assertEquals(-5.0, NumberDivider.divide(-10.0, 2.0), DELTA, "-10.0 / 2.0 should be -5.0");
    }

    @Test
    @DisplayName("Test divide positive numerator by negative denominator")
    void testDivide_positiveNumerator_negativeDenominator() {
        assertEquals(-5.0, NumberDivider.divide(10.0, -2.0), DELTA, "10.0 / -2.0 should be -5.0");
    }

    @Test
    @DisplayName("Test divide negative numerator by negative denominator")
    void testDivide_negativeNumerator_negativeDenominator() {
        assertEquals(5.0, NumberDivider.divide(-10.0, -2.0), DELTA, "-10.0 / -2.0 should be 5.0");
    }

    @Test
    @DisplayName("Test divide zero numerator by positive denominator")
    void testDivide_zeroNumerator_positiveDenominator() {
        assertEquals(0.0, NumberDivider.divide(0.0, 5.0), DELTA, "0.0 / 5.0 should be 0.0");
    }

    @Test
    @DisplayName("Test divide zero numerator by negative denominator")
    void testDivide_zeroNumerator_negativeDenominator() {
        assertEquals(0.0, NumberDivider.divide(0.0, -5.0), DELTA, "0.0 / -5.0 should be 0.0 (or 0.0)");
        // Note: -0.0 is a valid double, but assertEquals treats 0.0 and -0.0 as equal.
    }

    @Test
    @DisplayName("Test division by zero throws IllegalArgumentException")
    void testDivide_byZero_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NumberDivider.divide(10.0, 0.0);
        }, "Dividing by zero should throw IllegalArgumentException");
        assertEquals("Denominator cannot be zero for division.", exception.getMessage());
    }

    @Test
    @DisplayName("Test division of zero by zero throws IllegalArgumentException")
    void testDivide_zeroByZero_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            NumberDivider.divide(0.0, 0.0);
        }, "Dividing 0.0 by 0.0 should throw IllegalArgumentException");
        assertEquals("Denominator cannot be zero for division.", exception.getMessage());
    }

    @Test
    @DisplayName("Test divide small number by large number")
    void testDivide_smallNumberByLargeNumber() {
        assertEquals(0.01, NumberDivider.divide(1.0, 100.0), DELTA, "1.0 / 100.0 should be 0.01");
    }

    @Test
    @DisplayName("Test divide large number by small number")
    void testDivide_largeNumberBySmallNumber() {
        assertEquals(200.0, NumberDivider.divide(100.0, 0.5), DELTA, "100.0 / 0.5 should be 200.0");
    }
}

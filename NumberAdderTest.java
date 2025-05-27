import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Assuming NumberAdder.java is in the same directory or package
// import NumberAdder; // Not strictly necessary if in the same default package and compiled together

/**
 * JUnit 5 Test class for NumberAdder.
 *
 * To compile and run these tests if JUnit 5 is not part of a build system (e.g., Maven, Gradle):
 * 1. Download the JUnit Platform JAR files (e.g., junit-platform-console-standalone.jar)
 *    from an official source like Maven Central.
 * 2. Compile: javac -cp path/to/junit-platform-console-standalone.jar:. NumberAdder.java NumberAdderTest.java
 * 3. Run:    java -jar path/to/junit-platform-console-standalone.jar -cp . --select-class NumberAdderTest
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
public class NumberAdderTest {

    @Test
    @DisplayName("Test sum of two positive numbers")
    void testSum_twoPositiveNumbers() {
        assertEquals(12, NumberAdder.sum(5, 7), "5 + 7 should be 12");
    }

    @Test
    @DisplayName("Test sum of a positive and a negative number")
    void testSum_positiveAndNegative() {
        assertEquals(7, NumberAdder.sum(10, -3), "10 + (-3) should be 7");
    }

    @Test
    @DisplayName("Test sum of a negative and a positive number")
    void testSum_negativeAndPositive() {
        assertEquals(7, NumberAdder.sum(-5, 12), "-5 + 12 should be 7");
    }

    @Test
    @DisplayName("Test sum of two negative numbers")
    void testSum_twoNegativeNumbers() {
        assertEquals(-12, NumberAdder.sum(-5, -7), "-5 + (-7) should be -12");
    }

    @Test
    @DisplayName("Test sum of a positive number and zero")
    void testSum_positiveAndZero() {
        assertEquals(10, NumberAdder.sum(10, 0), "10 + 0 should be 10");
    }

    @Test
    @DisplayName("Test sum of a negative number and zero")
    void testSum_negativeAndZero() {
        assertEquals(-8, NumberAdder.sum(-8, 0), "-8 + 0 should be -8");
    }

    @Test
    @DisplayName("Test sum of zero and zero")
    void testSum_zeroAndZero() {
        assertEquals(0, NumberAdder.sum(0, 0), "0 + 0 should be 0");
    }

    @Test
    @DisplayName("Test sum of numbers resulting in zero")
    void testSum_numbersResultingInZero() {
        assertEquals(0, NumberAdder.sum(5, -5), "5 + (-5) should be 0");
    }

    @Test
    @DisplayName("Test sum with larger numbers")
    void testSum_largerNumbers() {
        assertEquals(100000, NumberAdder.sum(50000, 50000), "50000 + 50000 should be 100000");
    }

    @Test
    @DisplayName("Test sum with one number being Integer.MAX_VALUE (potential overflow not tested here)")
    void testSum_maxValue() {
        assertEquals(Integer.MAX_VALUE, NumberAdder.sum(Integer.MAX_VALUE - 1, 1), "Integer.MAX_VALUE - 1 + 1 should be Integer.MAX_VALUE");
    }
    
    @Test
    @DisplayName("Test sum with one number being Integer.MIN_VALUE (potential underflow not tested here)")
    void testSum_minValue() {
        assertEquals(Integer.MIN_VALUE + 1, NumberAdder.sum(Integer.MIN_VALUE, 1), "Integer.MIN_VALUE + 1 should be Integer.MIN_VALUE + 1");
    }
}

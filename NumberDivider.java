public class NumberDivider {

    /**
     * Divides a numerator by a denominator.
     *
     * @param numerator   The number to be divided.
     * @param denominator The number to divide by.
     * @return The result of the division (numerator / denominator).
     * @throws IllegalArgumentException if the denominator is zero.
     */
    public static double divide(double numerator, double denominator) {
        if (denominator == 0.0) {
            throw new IllegalArgumentException("Denominator cannot be zero for division.");
        }
        return numerator / denominator;
    }

    /**
     * Main method to demonstrate the divide function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Demonstrate successful division
        double num1 = 10.0;
        double num2 = 2.0;
        try {
            double result1 = divide(num1, num2);
            System.out.println(num1 + " / " + num2 + " = " + result1);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        double num3 = 7.0;
        double num4 = 2.0;
        try {
            double result2 = divide(num3, num4);
            System.out.println(num3 + " / " + num4 + " = " + result2);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        double num5 = -8.0;
        double num6 = 4.0;
        try {
            double result3 = divide(num5, num6);
            System.out.println(num5 + " / " + num6 + " = " + result3);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Demonstrate division by zero
        double num7 = 5.0;
        double num8 = 0.0;
        System.out.println("\nAttempting to divide " + num7 + " by " + num8 + ":");
        try {
            double result3 = divide(num7, num8); // This should throw an exception
            System.out.println(num7 + " / " + num8 + " = " + result3); // This line should not be reached
        } catch (IllegalArgumentException e) {
            System.err.println("Caught expected error: " + e.getMessage());
        }
        
        // Demonstrate with another zero denominator case
        double num9 = 0.0;
        double num10 = 0.0;
        System.out.println("\nAttempting to divide " + num9 + " by " + num10 + ":");
        try {
            double result4 = divide(num9, num10);
            System.out.println(num9 + " / " + num10 + " = " + result4);
        } catch (IllegalArgumentException e) {
            System.err.println("Caught expected error: " + e.getMessage());
        }
    }
}

public class NumberAdder {

    /**
     * Calculates the sum of two integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The sum of a and b.
     */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Main method to demonstrate the sum function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // First example
        int num1 = 5;
        int num2 = 7;
        int result1 = sum(num1, num2);
        System.out.println("The sum of " + num1 + " and " + num2 + " is " + result1 + ".");

        // Second example with a negative number
        int num3 = 10;
        int num4 = -3;
        int result2 = sum(num3, num4);
        System.out.println("The sum of " + num3 + " and " + num4 + " is " + result2 + ".");

        // Third example with zero
        int num5 = 0;
        int num6 = 42;
        int result3 = sum(num5, num6);
        System.out.println("The sum of " + num5 + " and " + num6 + " is " + result3 + ".");
    }
}

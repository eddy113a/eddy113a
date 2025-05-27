// Adder.java
import java.util.Scanner;

public class Adder {

    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Adder adderInstance = new Adder();

        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        double sum = adderInstance.add(num1, num2);
        System.out.println("The sum is: " + sum);

        scanner.close();
    }
}

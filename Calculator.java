// Calculator.java
import java.util.Scanner;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        System.out.print("Enter an operation (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        double result;

        try {
            switch (operation) {
                case '+':
                    result = calculator.add(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = calculator.subtract(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = calculator.multiply(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    result = calculator.divide(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                default:
                    System.out.println("Invalid operation.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}

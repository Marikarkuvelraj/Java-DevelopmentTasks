import java.util.Scanner;

public  class Calculator {

    
    static void add(double a, double b) {
        System.out.println("Addition = " + (a + b));
    }

    
    static void subtract(double a, double b) {
        System.out.println("Subtraction = " + (a - b));
    }

   
    static void multiply(double a, double b) {
        System.out.println("Multiplication = " + (a * b));
    }

    static void divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
        } else {
            System.out.println("Division = " + (a / b));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        if (!sc.hasNextDouble()) {
            System.out.println("Error: Please enter a valid first number.");
            sc.close();
            return;
        }
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        if (!sc.hasNextDouble()) {
            System.out.println("Error: Please enter a valid second number.");
            sc.close();
            return;
        }
        double num2 = sc.nextDouble();

        System.out.println("\nChoose an operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        System.out.print("Enter your choice (1-4): ");
        if (!sc.hasNextInt()) {
            System.out.println("Error: Please enter a valid operation choice.");
            sc.close();
            return;
        }
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                add(num1, num2);
                break;

            case 2:
                subtract(num1, num2);
                break;

            case 3:
                multiply(num1, num2);
                break;

            case 4:
                divide(num1, num2);
                break;

            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
} 
    


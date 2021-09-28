/*********************************************************************************************************************
 *CMSC 335
 * Project 1
 * David Mejia
 * This class contains static methods that will be used for the main method to
 * correctly display the menu and its results.
 * *******************************************************************************************************************/
package project1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static boolean isValid = true;
    static boolean isRight = true;
    static boolean isNotCorrectInput = true;
    static Scanner sc = new Scanner(System.in);

/*********************************************************************************************************************
 *
 *                                                 STATIC METHODS
 *
 * *******************************************************************************************************************/

    /**
     * This method will allow the program to check if the input from the user is a number.  if not a number then
     * it will alert the user to try again.
     * @param scanner
     */
    static void isNumeric(Scanner scanner){
        while (!scanner.hasNextDouble()){
            System.out.println("Input must be a number");
            scanner.next();
        }
    }


    /**
     * this method will display the menu to the user and will also calculate the area
     * or volume based on the user selection.  simple while loop will allow the program to restart
     * once user chooses to continue working with program.
     * @throws InputMismatchException
     */
    static void displayAndCalculate() throws InputMismatchException{
        while(isValid){
            System.out.println("****************Welcome to the Java OO Shapes Program*********************");
            System.out.println("\nSelect from the menu below:\n");
            System.out.println("1. Construct a Circle");
            System.out.println("2. Construct a Rectangle");
            System.out.println("3. Construct a Square");
            System.out.println("4. Construct a Triangle");
            System.out.println("5. Construct a Sphere");
            System.out.println("6. Construct a Cube");
            System.out.println("7. Construct a Cone");
            System.out.println("8. Construct a Cylinder");
            System.out.println("9. Construct a Torus");
            System.out.println("10. Exit the Program");
            System.out.println("\nPlease Enter your selection: ");
            //this part will check if the input from the user is a number
            while (!sc.hasNextInt()){
                System.out.println("Input is not an integer\nPlease select from the menu:");
                sc.next();
            }
            int selection = sc.nextInt();
            // based on the menu, the number should be between 1 and 10
            if(selection > 0 && selection <=10){
                switch (selection) {
                    case 1:
                        System.out.println("You have selected a Circle");
                        System.out.println("What is the Radius:");
                        isNumeric(sc);
                        double radius = sc.nextDouble();
                        Circle circle = new Circle(radius);
                        displayResult(circle.calculateArea(), "Area", circle.getShapeName());
                        break;
                    case 2:
                        System.out.println("You have selected a Rectangle");
                        System.out.println("What is the Length:");
                        isNumeric(sc);
                        double length = sc.nextDouble();
                        System.out.println("What is the Width:");
                        isNumeric(sc);
                        double width = sc.nextDouble();
                        Rectangle rect = new Rectangle(length,width);
                        displayResult(rect.calculateArea(), "Area", rect.getShapeName());
                        break;
                    case 3:
                        System.out.println("You have selected a Square");
                        System.out.println("What is the Length:");
                        isNumeric(sc);
                        double squareSide = sc.nextDouble();
                        Square sqr = new Square(squareSide);
                        displayResult(sqr.calculateArea(), "Area", sqr.getShapeName());
                        break;
                    case 4:
                        System.out.println("You have selected a Triangle");
                        System.out.println("What is the base:");
                        isNumeric(sc);
                        double base = sc.nextDouble();
                        System.out.println("What is the Height:");
                        isNumeric(sc);
                        double height = sc.nextDouble();
                        Triangle triangle = new Triangle(base, height);
                        displayResult(triangle.calculateArea(), "Area", triangle.getShapeName());
                        break;
                    case 5:
                        System.out.println("You have selected a Sphere");
                        System.out.println("What is the Radius:");
                        isNumeric(sc);
                        double sphereRadius = sc.nextDouble();
                        Sphere sphere = new Sphere(sphereRadius);
                        displayResult(sphere.calculateVolume(), "Volume", sphere.getShapeName());
                        break;
                    case 6:
                        System.out.println("You have selected a Cube");
                        System.out.println("What is the length:");
                        isNumeric(sc);
                        double cubeLength = sc.nextDouble();
                        Cube cube = new Cube(cubeLength);
                        displayResult(cube.calculateVolume(), "Volume", cube.getShapeName());
                        break;
                    case 7:
                        System.out.println("You have selected a Cone");
                        System.out.println("What is the radius:");
                        isNumeric(sc);
                        double coneRadius = sc.nextDouble();
                        System.out.println("What is the height:");
                        isNumeric(sc);
                        double coneHeight = sc.nextDouble();
                        Cone cone  = new Cone(coneRadius, coneHeight);
                        displayResult(cone.calculateVolume(), "Volume", cone.getShapeName());
                        break;
                    case 8:
                        System.out.println("You have selected a Cylinder");
                        System.out.println("What is the radius:");
                        isNumeric(sc);
                        double cylRadius = sc.nextDouble();
                        System.out.println("What is the height:");
                        isNumeric(sc);
                        double cylHeight = sc.nextDouble();
                        Cylinder cylinder  = new Cylinder(cylRadius, cylHeight);
                        displayResult(cylinder.calculateVolume(), "Volume", cylinder.getShapeName());
                        break;
                    case 9:
                        System.out.println("You have selected a Torus");
                        System.out.println("What is the small radius:");
                        isNumeric(sc);
                        double smallRadius = sc.nextDouble();
                        System.out.println("What is the major radius:");
                        isNumeric(sc);
                        double majorRadius = sc.nextDouble();
                        Torus torus  = new Torus(smallRadius, majorRadius);
                        displayResult(torus.calculateVolume(), "Volume", torus.getShapeName());
                        break;
                    case 10:
                        isValid = false;
                        break;
                }
            }else{
                //is user enter any other number then this message will be displayed
                System.out.println("Please check your selection");
                isValid = true;
            }
        }
        if(!isValid){
            displayTime();
        }
    }

    /**
     * This method will allow the program to display the correct time at the end
     * of the session.
     */
    static void displayTime(){
        Date today = new Date();
        String now = today.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MM'/'dd 'at' hh:mm a");
        System.out.println("Thanks for using the program. today is " + dateFormat.format(today) + "\n");
    }

    /**
     * This method allows the program to correctly display the result of the calculation. it is also responsible
     * for asking if the user want to continue using the program.
     * @param result
     * @param areaOrVol
     * @param nameOfShape
     */
    static void displayResult(double result, String areaOrVol, String nameOfShape){
        System.out.printf("The " + areaOrVol + " of the " + nameOfShape + " is  %.2f %n", result);
        char cont;
        while(isRight) {
            try {
                System.out.println("\nWould you like to continue?");
                cont = sc.next().toLowerCase().charAt(0);
                if (cont == 'y') {
                    isValid = true;
                    isRight = false;
                }
                else if (cont == 'n') {
                    isValid = false;
                    isRight = false;
                } else {
                    isRight = true;
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Sorry I do not undestand. Enter Y or N");
            }
        }
        isRight = true;
    }


/*********************************************************************************************************************
 *
 *                                                 MAIN METHOD
 *
 * *******************************************************************************************************************/
    static public void main(String[] args) {
        do {
            try {
                displayAndCalculate();
                isNotCorrectInput = false;
            //if anything goes super bad then this message will be displayed back to user
            } catch (Exception e) {
                System.out.println("Fatal Error, Please try again");
                displayTime();
                break;
            }
        }while(isNotCorrectInput);
    }
}



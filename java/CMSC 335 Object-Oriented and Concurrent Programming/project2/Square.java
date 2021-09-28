/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a square is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

public class Square extends TwoDimensionalShape {
    /**
     * constructor
     * @param length
     */
    public Square(double length) {
        super(length);
        setShapeName("Square");
    }

    @Override
    public double calculateArea() {
        return getLength() * getLength();
    }
}

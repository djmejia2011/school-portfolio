/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a triangle is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

public class Triangle extends TwoDimensionalShape {

    /********************************************************************************************************
     *                                             Constructor
     * *******************************************************************************************************/
    public Triangle(double base, double height) {
        super(base, height);
        setShapeName("Triangle");
    }
    /********************************************************************************************************
     *                                             Method
     * *******************************************************************************************************/
    @Override
    public double calculateArea() {
        return (getLength() * getWidth())/2;
    }
}

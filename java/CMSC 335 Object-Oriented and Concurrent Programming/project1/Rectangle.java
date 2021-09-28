/*********************************************************************************************************************
 * CMSC 335
 * Project 1
 * David Mejia
 * This class allows the program to understand what a rectangle is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/

package project1;

/**
 * CONSTRUCTOR
 */
public class Rectangle extends TwoDimensionalShape{
    public Rectangle(double length, double width) {
        super(length, width);
        setShapeName("Rectangle");
    }
}

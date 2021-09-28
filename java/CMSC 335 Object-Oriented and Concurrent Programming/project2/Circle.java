/*********************************************************************************************************************
 *CMSC 335
 * Project 2
 * David Mejia
 * This Circle class is a Two Dimensional Shape. Polymorphism is used when calculateArea() was overwritten to
 * calculate the area of the circle.
 * *******************************************************************************************************************/
package project2;

public class Circle extends TwoDimensionalShape {

    /**
     * Constructor
     * @param radius
     */
    public Circle(double radius) {
        super(radius);
        setShapeName("Circle");
    }

    /**
     * this method need to be change to correctly calculate the
     * area of a circle
     * @return
     */
    @Override
    public double calculateArea() {
        return getPI() * getLength() * getLength();
    }
}

/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a cylinder is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

public class Cylinder extends ThreeDimensionalShape {
    private double height;

    /**
     * CONSTRUCTOR
     * @param base
     * @param height
     */
    public Cylinder(double base, double height) {
        super(base);
        this.height = height;
        setShapeName("Cylinder");
    }

    @Override
    public double calculateVolume() {
        return getPI() * getLength() * getLength() * height;
    }
}

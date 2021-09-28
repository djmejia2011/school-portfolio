/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a sphere is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

public class Sphere extends ThreeDimensionalShape {
    /**
     * constructor
     * @param radius
     */
    public Sphere(double radius) {
        super(radius);
        setShapeName("Sphere");
    }

    @Override
    public double calculateVolume() {
        return (4 * getPI() * getLength() * getLength() * getLength())/3;
    }
}

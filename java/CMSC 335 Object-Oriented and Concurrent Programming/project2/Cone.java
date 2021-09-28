/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a cone is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

public class Cone extends ThreeDimensionalShape {
    private double height;

    /**
     * Constructor
     * @param radius
     * @param height
     */
    public Cone(double radius, double height) {
        super(radius);
        this.height = height;
        setShapeName("Cone");
    }
    //GETTERS
    public double getHeight() {
        return height;
    }

    @Override
    public double calculateVolume() {
        return (getHeight()/3 * getPI() * getLength() * getLength());
    }
}

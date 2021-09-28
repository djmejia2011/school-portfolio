/*********************************************************************************************************************
 * CMSC 335
 * Project 1
 * David Mejia
 * This class allows the program to understand what a torus is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project1;

public class Torus extends ThreeDimensionalShape{
    private double largeRadius;

    /********************************************************************************************************
     *                                             Constructor
     * *******************************************************************************************************/
    public Torus(double smallRadius, double largeRadius) {
        super(smallRadius);
        this.largeRadius = largeRadius;
        setShapeName("Torus");
    }
    /********************************************************************************************************
     *                                             Getter
     * *******************************************************************************************************/
    public double getLargeRadius() {
        return largeRadius;
    }

    /********************************************************************************************************
     *                                             Method
     * *******************************************************************************************************/
    @Override
    public double calculateVolume() {
        double volume = (getPI() * getLength() * getLength()) * (2 * getPI() * getLargeRadius());
        return volume;

    }
}

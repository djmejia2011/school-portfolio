/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to understand what a cube is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project2;

/**
 * constructor
 */
public class Cube extends ThreeDimensionalShape {
    public Cube(double length) {
        super(length);
        setShapeName("Cube");
    }

    @Override
    public double calculateVolume() {
        return getLength()*getLength()*getLength();
    }
}

/*********************************************************************************************************************
 * CMSC 335
 * Project 1
 * David Mejia
 * This class allows the program to understand what a ThreeDimensionalShape is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project1;

public class ThreeDimensionalShape extends Shape{
    private double length, width, height;

    /********************************************************************************************************
     *                                             Constructors
     * *******************************************************************************************************/
    public ThreeDimensionalShape(double length){
        this.length = length;
    }

    public ThreeDimensionalShape(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /********************************************************************************************************
    *                                             getters
    * *******************************************************************************************************/
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    /********************************************************************************************************
     *                                             Methods
     * *******************************************************************************************************/
    public double calculateVolume(){
        return  getLength() * getWidth() * getHeight();
    }
}

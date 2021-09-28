/*********************************************************************************************************************
 * CMSC 335
 * Project 1
 * David Mejia
 * This class allows the program to understand what a TwoDimensionalShape is and to correctly
 * calculate the volume.
 * *******************************************************************************************************************/
package project1;

public class TwoDimensionalShape extends Shape {
    private double length;
    private double width;
    /********************************************************************************************************
     *                                             Constructors
     * *******************************************************************************************************/
    public TwoDimensionalShape(double length){
        this.length = length;
    }
    public TwoDimensionalShape(double length, double width) {
        this.length = length;
        this.width = width;
    }
    /********************************************************************************************************
     *                                             Getters
     * *******************************************************************************************************/
    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }

    /********************************************************************************************************
     *                                             Methods
     * *******************************************************************************************************/
    public double calculateArea(){
        double area = length * width;
        return area;
    }
}

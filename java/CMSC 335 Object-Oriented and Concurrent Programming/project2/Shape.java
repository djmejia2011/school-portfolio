/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program understand the basics of a shape, TwoDimensionalShape and ThreeDimensionalShape
 * will inherit from the shape class.
 * *******************************************************************************************************************/

package project2;

public class Shape{
private final double PI=3.14;
private String shapeName;// every shape will have a name to later use when displaying results

    public double getPI() {
        return PI;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }
}

/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class allows the program to draw the correct 2d shape based on the selection of the user.
 * *******************************************************************************************************************/

package project2;

import javax.swing.*;
import java.awt.*;

public class ShapeBuilder extends JPanel {
    private String nameOfShape;
    private TwoDimensionalShape twoDShape;

    /*Constructor*/
    public ShapeBuilder(String nameOfShape, TwoDimensionalShape twoDShape) throws Exception{
        this.nameOfShape = nameOfShape;
        this.twoDShape = twoDShape;
    }

    /*GETTERS*/
    public String getNameOfShape() {
        return nameOfShape;
    }
    public TwoDimensionalShape getTwoDShape(){
        return twoDShape;
    }

    /*method that calls on paintComponent() whenever the button "draw" is clicked*/
    public void drawShape(){
        setLayout(null);
        repaint();
    }

    /**
     * This method will be responsible for creating the shapes based on the input
     * from the user.
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if(getNameOfShape()=="Circle"){
            int radius = (int) getTwoDShape().getLength();
            g.drawOval(5,5,radius*10,radius*10);
        }else if(getNameOfShape()=="Square"){
            int side = (int) getTwoDShape().getLength();
            g.drawRect(5,5,side*10,side*10);
        }else if(getNameOfShape()=="Triangle"){
            int base = (int) getTwoDShape().getLength();
            int height = (int) getTwoDShape().getWidth();
            //50 was added because the triangle is too small in the frame
            g.drawPolygon(new int[] {0*50, base/2*50, base*50}, new int[] {height*50, height/2*50, height*50}, 3);
        }else if(getNameOfShape()=="Rectangle"){
            int length = (int) getTwoDShape().getLength();
            int width = (int) getTwoDShape().getWidth();
            g.drawRect(5,5,length*10,width*10);
        }
    }
}

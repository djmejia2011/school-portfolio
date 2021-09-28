/*********************************************************************************************************************
 * CMSC 335
 * Project 2
 * David Mejia
 * This class will be responsible for creating the panel that will display the options the user
 * as the user makes shape selection the rest of the fields will be set to visible. this class will then call
 * on either JOptionPane or ShapeBuilder to display a visual representation of the shape that was selected
 * *******************************************************************************************************************/
package project2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIPanel extends JPanel{
    private String response;

    public GUIPanel() throws Exception{
        //Components that will be used inside the panel
        String[] shapes = {"Circle", "Square", "Triangle", "Rectangle", "Sphere", "Cube", "Cone", "Cylinder", "Torus"};
        JLabel shapeSelection = new JLabel("Select a Shape");
        JButton draw = new JButton("Draw Shape");
        draw.setVisible(false);
        JLabel numberOne = new JLabel("");
        JLabel numberTwo = new JLabel("");
        JLabel results = new JLabel("");
        JTextField firstNumber= new JTextField(10);
        firstNumber.setVisible(false);
        JTextField secondNumber= new JTextField(10);
        secondNumber.setVisible(false);
        JTextField calcResult= new JTextField(10);
        calcResult.setVisible(false);
        setLayout(new GridLayout(0,2,15,25));

        //Every label, textfield and button will be set to invisible
        //until user makes shape selection.
        JComboBox shapeDropDown = new JComboBox(shapes);
        shapeDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*set everything to invisible*/
                numberOne.setText("");
                numberTwo.setText("");
                firstNumber.setVisible(false);
                firstNumber.setText("");
                secondNumber.setVisible(false);
                secondNumber.setText("");
                results.setText("");
                calcResult.setVisible(false);
                calcResult.setText("");
                calcResult.setEditable(false);
                draw.setVisible(false);
                //the selection from the user will be stored in a variable
                //tp avoid repeating code.
                response = shapeDropDown.getSelectedItem().toString();
                draw.setVisible(true);
                if(response.equals("Circle")){
                    numberOne.setText("Enter Radius:");
                    firstNumber.setVisible(true);

                }else if(response.equals("Square")){
                    numberOne.setText("Enter Side:");
                    firstNumber.setVisible(true);

                }else if(response.equals("Triangle")){
                    numberOne.setText("Enter Base:");
                    numberTwo.setText("Enter Height:");
                    firstNumber.setVisible(true);
                    secondNumber.setVisible(true);

                }else if(response.equals("Rectangle")){
                    numberOne.setText("Enter Length:");
                    numberTwo.setText("Enter Width:");
                    firstNumber.setVisible(true);
                    secondNumber.setVisible(true);

                }else if(response.equals("Sphere")){
                    numberOne.setText("Enter Radius:");
                    firstNumber.setVisible(true);

                }else if(response.equals("Cube")){
                    numberOne.setText("Enter Side:");
                    firstNumber.setVisible(true);

                }else if(response.equals("Cone")){
                    numberOne.setText("Enter Radius:");
                    numberTwo.setText("Enter Height:");
                    firstNumber.setVisible(true);
                    secondNumber.setVisible(true);

                }else if(response.equals("Cylinder")){
                    numberOne.setText("Enter Radius:");
                    numberTwo.setText("Enter Height:");
                    firstNumber.setVisible(true);
                    secondNumber.setVisible(true);

                }else if(response.equals("Torus")){
                    numberOne.setText("Enter Small Radius:");
                    numberTwo.setText("Enter Large Radius:");
                    firstNumber.setVisible(true);
                    secondNumber.setVisible(true);

                }
            }
        });
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameDraw = new JFrame("Shape Drawing");
                frameDraw.setSize(400, 400);
                frameDraw.setLocationRelativeTo(null);
                calcResult.setVisible(true);
                try {
                    if (response.equals("Circle") || response.equals("Square") || response.equals("Triangle")
                            || response.equals("Rectangle")) {
                        /*All 2D shapes will display area as results and will use ShapeBuilder Class to
                         * draw shapes*/
                        results.setText("Area");

                        if (response.equals("Circle")) {
                            Circle circle = new Circle(Double.parseDouble(firstNumber.getText()));
                            calcResult.setText(String.valueOf(circle.calculateArea()));
                            ShapeBuilder circleShape = new ShapeBuilder("Circle", circle);
                            frameDraw.add(circleShape);
                            circleShape.drawShape();
                            frameDraw.setVisible(true);

                        } else if (response.equals("Square")) {
                            Square square = new Square(Double.parseDouble(firstNumber.getText()));
                            calcResult.setText(String.valueOf(square.calculateArea()));
                            ShapeBuilder squareShape = new ShapeBuilder("Square", square);
                            frameDraw.add(squareShape);
                            squareShape.drawShape();
                            frameDraw.setVisible(true);

                        } else if (response.equals("Triangle")) {
                            double base = Double.parseDouble(firstNumber.getText());
                            double height = Double.parseDouble(secondNumber.getText());
                            Triangle triangle = new Triangle(base, height);
                            calcResult.setText(String.valueOf(triangle.calculateArea()));
                            ShapeBuilder triangleShape = new ShapeBuilder("Triangle", triangle);
                            frameDraw.add(triangleShape);
                            triangleShape.drawShape();
                            frameDraw.setVisible(true);

                        } else if (response.equals("Rectangle")) {
                            double length = Double.parseDouble(firstNumber.getText());
                            double width = Double.parseDouble(secondNumber.getText());
                            Rectangle rectangle = new Rectangle(length, width);
                            calcResult.setText(String.valueOf(rectangle.calculateArea()));
                            ShapeBuilder rectShape = new ShapeBuilder("Rectangle", rectangle);
                            frameDraw.add(rectShape);
                            rectShape.drawShape();
                            frameDraw.setVisible(true);
                        }

                    } else if (response.equals("Sphere") || response.equals("Cube") || response.equals("Cone")
                            || response.equals("Cylinder") || response.equals("Torus")) {

                        results.setText("Volume");
                        /*For 3D shapes, displayImg will be used to display a JOptionPane with an image of the shape*/
                        if (response.equals("Sphere")) {
                            Sphere sphere = new Sphere(Double.parseDouble(firstNumber.getText()));
                            calcResult.setText(String.valueOf(sphere.calculateVolume()));
                            displayImg(response, "src/project2/sphere.jpg");

                        } else if (response.equals("Cube")) {
                            Cube cube = new Cube(Double.parseDouble(firstNumber.getText()));
                            calcResult.setText(String.valueOf(cube.calculateVolume()));
                            displayImg(response, "src/project2/cube.png");

                        } else if (response.equals("Cone")) {
                            double radius = Double.parseDouble(firstNumber.getText());
                            double height = Double.parseDouble(secondNumber.getText());
                            Cone cone = new Cone(radius, height);
                            calcResult.setText(String.valueOf(cone.calculateVolume()));
                            displayImg(response, "src/project2/cone.png");

                        } else if (response.equals("Cylinder")) {
                            double radius = Double.parseDouble(firstNumber.getText());
                            double height = Double.parseDouble(secondNumber.getText());
                            Cylinder cylinder = new Cylinder(radius, height);
                            calcResult.setText(String.valueOf(cylinder.calculateVolume()));
                            displayImg(response, "src/project2/cylinder.png");

                        } else if (response.equals("Torus")) {
                            double smallRadius = Double.parseDouble(firstNumber.getText());
                            double largeRadius = Double.parseDouble(secondNumber.getText());
                            Torus torus = new Torus(smallRadius, largeRadius);
                            calcResult.setText(String.valueOf(torus.calculateVolume()));
                            displayImg(response, "src/project2/torus.png");
                        }
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null,"Please Check Your Input");
                }
            }
        });

        /**
         * Add all components to the panel
         */
        add(shapeSelection);
        add(shapeDropDown);
        add(numberOne);
        add(firstNumber);
        add(numberTwo);
        add(secondNumber);
        add(results);
        add(calcResult);
        add(draw);

    }

    /**
     * This method will allow the program to display the correct image on all 3D shapes
     * @param nameOfShape
     * @param imagePath
     */
    private static void displayImg(String nameOfShape, String imagePath){
        ImageIcon imageName = new ImageIcon(imagePath);
        JOptionPane.showMessageDialog(null,"",nameOfShape,JOptionPane.INFORMATION_MESSAGE,imageName);
    }

}

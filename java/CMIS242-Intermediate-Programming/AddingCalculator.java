/**
 * Author: David Mejia
 * Date: 11/13/2019
 * Course: CMIS242
 * Professor:Andrew Seely
 * Purpose: To display a simple GUI for week 4 discussion
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddingCalculator extends JFrame implements ActionListener{
	JLabel userInput = new JLabel("Enter two Numbers:");
	JLabel calculation = new JLabel("Results:");
	JTextField numOne = new JTextField(10);
	JTextField numTwo = new JTextField(10);
	JButton equals = new JButton("Equals");
	JTextField result = new JTextField(20);
	
	final int WIDTH = 450;
	final int HEIGHT= 150;
	
	public AddingCalculator(){
		super("Simple Adding Calculator");
		setForeground(Color.red);
		setSize(WIDTH, HEIGHT);
		setLayout(new FlowLayout());
		add(userInput);
		add(numOne);
		add(numTwo);
		add(equals);
		add(calculation);
		add(result);
		result.setEditable(false);
		equals.setBackground(Color.red);
		setBackground(Color.red);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		equals.addActionListener(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.green);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent action){
		int number1;
		int number2;
		int results;
		try {
			
			number1 = Integer.parseInt(numOne.getText());
			number2 = Integer.parseInt(numTwo.getText());
			results = number1 + number2;
			result.setText(number1 + " + " + number2 + " = " + Integer.toString(results));
		} catch (Exception e) {
			result.setText("Please enter integers only.");
			
		}
	}
	
	public static void main(String[] args){
		AddingCalculator frame = new AddingCalculator();
		frame.repaint();
		frame.setForeground(Color.red);
		frame.setBackground(Color.red);
		
		frame.setVisible(true);
		
		}
	
}
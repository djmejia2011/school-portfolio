/**
 * @author David Mejia
 * Date: 11/15/2019
 * Course: CMIS242
 * Professor:Andrew Seely
 * Purpose: To create GUI and all action handlers and main method
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ATMMachine extends JFrame implements ActionListener{
	//Width and Height of ATMMachine frame
	final private int WIDTH = 250;
	final private int HEIGHT= 250;
	
	//all object needed for the class to work
	Account checkings = new Account();
	Account savings = new Account();
	JButton withdraw = new JButton(" Withdraw ");
	JButton deposit = new JButton("  Deposit   ");
	JButton transferTo = new JButton("Transfer to");
	JButton balance = new JButton("  Balance  ");
	JTextField userInput = new JTextField(18);
	JLabel notSelected = new JLabel("You Must Select Account to Continue!");
	JRadioButton checkingsButton = new JRadioButton("Checkings");
	JRadioButton savingsButton = new JRadioButton("Savings");
	
	/**
	 * Constructor, sets name of frame to ATM Machine
	 */
	public ATMMachine(){
		super("ATM Machine");
		setSize(WIDTH,HEIGHT);
		setLayout(new FlowLayout());	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(withdraw);
		add(deposit);
		add(transferTo);
		add(balance);
		add(checkingsButton);
		add(savingsButton);
		add(userInput);
		add(notSelected);
		notSelected.setVisible(false);
		setResizable(false);
		//action listeners
		withdraw.addActionListener(this);
		deposit.addActionListener(this);
		transferTo.addActionListener(this);
		balance.addActionListener(this);
		checkingsButton.addActionListener(this);
		savingsButton.addActionListener(this);
	}
	
	/**
	 * Action handler for all the bottons in the frame
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object objSource = e.getSource();
		
		try {
			//WITHDRAW BUTTON PRESSED
			if(objSource == withdraw) {
				if (checkingsButton.isSelected()) {
					checkings.substractBalance(Double.parseDouble(userInput.getText()));
					if ((Double.parseDouble(userInput.getText()))%20==0) {
						if (checkings.isThereEnough(Double.parseDouble(userInput.getText()))) {
							JOptionPane.showMessageDialog(userInput, "Withdraw of $" + userInput.getText()
									+ " Complete\nCheckings Balance: $" + checkings.getBalance());
						}else {
							JOptionPane.showMessageDialog(userInput, "Withdraw of $" + userInput.getText()
							+ " Failed Not Enough Funds\nCheckings Balance: $" + checkings.getBalance());
						}
					}					
				}if(savingsButton.isSelected()) {
					savings.substractBalance(Double.parseDouble(userInput.getText()));
					if ((Double.parseDouble(userInput.getText()))%20==0) {
						if (savings.isThereEnough(Double.parseDouble(userInput.getText()))) {
							JOptionPane.showMessageDialog(userInput, "Withdraw of  $" + userInput.getText()
									+ " Complete\nSavings Balance: $" + savings.getBalance());
						}else {
							JOptionPane.showMessageDialog(userInput, "Withdraw of $" + userInput.getText()
							+ " Failed Not Enough Funds\nCheckings Balance: $" + savings.getBalance());
						}
					}
				}
			//DEPOSIT BUTTON PRESSED	
			}if(objSource == deposit) {
				if (checkingsButton.isSelected()) {
					checkings.addBalance(Double.parseDouble(userInput.getText()));
					JOptionPane.showMessageDialog(userInput,"Deposit Complete\nCheckings Balance: $" + checkings.getBalance());
				}if(savingsButton.isSelected()) {
					savings.addBalance(Double.parseDouble(userInput.getText()));
					JOptionPane.showMessageDialog(userInput,"Deposit Complete\nSavings Balance: $" + savings.getBalance());
				}
			//TRANSFER BUTTON PRESSED	
			}if(objSource == transferTo) {
				if (checkingsButton.isSelected()) {
					if (savings.isThereEnough(Double.parseDouble(userInput.getText()))) {
						checkings.transferFrom(savings, Double.parseDouble(userInput.getText()));
						JOptionPane.showMessageDialog(userInput,
								"Transfer Complete\nCheckings Balance: $" + checkings.getBalance());
					}else {
						JOptionPane.showMessageDialog(userInput,"Not enough money on your saving account to transfer");
					}
				}if(savingsButton.isSelected()) {
					if (checkings.isThereEnough(Double.parseDouble(userInput.getText()))) {
						savings.transferFrom(checkings, Double.parseDouble(userInput.getText()));
						JOptionPane.showMessageDialog(userInput,
								"Transfer Complete\nSavings Balance: $" + savings.getBalance());
					}else {
						JOptionPane.showMessageDialog(userInput,"Not enough money on your checking account to transfer");
					}
				}
			//BALANCE BUTTON PRESSED
			}if(objSource == balance) {
				if (checkingsButton.isSelected()) {
					JOptionPane.showMessageDialog(userInput,"Checkings Balance: $" + checkings.getBalance());
				}if(savingsButton.isSelected()) {
					JOptionPane.showMessageDialog(userInput,"Savings Balance: $" + savings.getBalance());
				}
			//UNSELECTS OTHER BOTTONS IF PRESSED	
			}if (objSource == checkingsButton) {			
				savingsButton.setSelected(false);
				notSelected.setVisible(false);
			}if (objSource == savingsButton) {			
				checkingsButton.setSelected(false);	
				notSelected.setVisible(false);
			}
			//IF NO RADIO BOTTONS PRESSED THEN DISPLAY ERROR MESSAGE TO USER.
			if ((!checkingsButton.isSelected()&&!savingsButton.isSelected())) {			
				notSelected.setVisible(true);		
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(userInput,"Input is not a number");
		}
	}		
	
	//main method
	public static void main(String[] args){
		
		//creates a new atm frame and sets it to display
		ATMMachine atmFrame = new ATMMachine();	
		atmFrame.setVisible(true);		
	}




















	

}

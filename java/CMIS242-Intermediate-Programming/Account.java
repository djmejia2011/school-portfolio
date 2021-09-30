/**
 * @author David Mejia
 * Date: 11/15/2019
 * Course: CMIS242
 * Professor:Andrew Seely
 * Purpose: to create a class called Account to create methods that will be used inside 
 * ATMMachine class.
 */
import javax.swing.JOptionPane;

public class Account {
	//variables needed for the Account class
	private double balance;
	private static int count = 0; //to keep count of withdrawals from accounts
	final private static double SERVICEFEE = 1.50;
	
	/**
	 * default constructor. sets all new accounts to 0 dollars
	 */
	public Account() {
		this.balance =0; //inital value for all acounts will be 0 dollars
	}
	/**
	 * This will transfer and check if enough money is available
	 * Example: Receivingaccount.transferFrom(fromwhataccount, amounttotransfer);
	 * @param fromAccount
	 * @param transferAmount
	 */
	public void transferFrom(Account fromAccount, double transferAmount) {
		if (fromAccount.isThereEnough(transferAmount)) {
			fromAccount.transferBalance(transferAmount);
			this.addBalance(transferAmount);
		}else {
			try {
				throw new InsufficientFunds();
			}catch(InsufficientFunds e) {
				JOptionPane.showMessageDialog(null,(e.getMessage()));
			}
		}
	}

	//gets balance amount from the object
	public double getBalance() {
		return balance;
	}
	
	//adds the amount passed by the user to the objects balance
	public void addBalance(double amount) {
		this.balance += amount;
	}
	
	/*
	 * This subtracts the amount from the balance and checks if the 
	 * amount passed in divisible by $20 bills and if
	 *there is enough money in the account
	 *it also keeps count of withdrawals
	 */	
	public void substractBalance(double amount) {
		try {
			if (isIncrement20(amount)) {
				if (this.isThereEnough(amount)) {
					if (count < 4) {
						this.balance -= amount;
					} else {
						if (this.isThereEnough(amount + getSERVICEFEE())) {
							this.balance -= amount + getSERVICEFEE();
						} else {
							throw new InsufficientFunds();
						}
					}
					count++;
				} else {
					throw new InsufficientFunds();
				} 
			}
		} catch (InsufficientFunds e) {
			JOptionPane.showMessageDialog(null,(e.getMessage()));
		}
	}
	
	/*
	 * This method checks if there is enough money in the sending account
	 * and then transfer balance.
	 */
	public void transferBalance(double amount) {
		try {
			if (this.isThereEnough(amount)) {					
				this.balance -= amount;
			} else {
				throw new InsufficientFunds();
			} 
			
		} catch (InsufficientFunds e) {
			JOptionPane.showMessageDialog(null,(e.getMessage()));
		}
	}
	/*
	 * This method gets the number of times the user has withdrawn money
	 */
	public static int getCount() {
		return count;
	}
	
	
	public static double getSERVICEFEE() {
		return SERVICEFEE;
	}
	
	/**
	 * checks if the user response is a number
	 * @param o
	 * @return true if is number
	 */
	public static boolean isNumeric(Object object) {
		if (object instanceof Integer || object instanceof Double) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * checks if the passed amount by the user
	 * is divisible by $20 dollar bills
	 */
	public static boolean isIncrement20(double wAmount) {
		if (wAmount%20==0) {
			return true;
		}else{
			JOptionPane.showMessageDialog(null,"Not divisible by $20 dollar bills");
			return false;
		}
	}
	/*
	 * checks if there is enough money in object's balance
	 * 
	 */
	public boolean isThereEnough(double wAmount){
		if (this.balance<wAmount) {			
			return false;
		}else {
			return true;
		}
	}
}

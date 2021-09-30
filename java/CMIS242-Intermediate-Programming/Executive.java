/*
*Program: Executive.java
*Author: David Mejia
*Course: CMIS 242
*Date: 11/2/2019
*Instructor: Andrew Seely
*Purpose: to create a class that extends employee, stock price was added to the constructor
*/
public class Executive extends Employee {
	//instance variable
	private int stockPrice = 0;
	
	/**
	 * constructor
	 * @param name
	 * @param monthlySalary
	 * @param stockPrice
	 */
	public Executive(String name, int monthlySalary, int stockPrice) {
		super(name, monthlySalary);
		this.stockPrice = stockPrice;
	}
	
	//setters and getters
	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public int annualSalary() {
		int totalAnnualPay;
		if (stockPrice > 50) {
			totalAnnualPay = (this.getMonthlySalary()*12)+30000;
		}else {
			totalAnnualPay = this.getMonthlySalary()*12;
		}
		return (int) totalAnnualPay;

	}
	
	public String toString() {
		return super.toString() + "\nStock Price:\t$" + this.getStockPrice();
	}
	
}

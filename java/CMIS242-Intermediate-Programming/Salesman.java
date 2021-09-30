/*
*Program: Salesman.java
*Author: David Mejia
*Course: CMIS 242
*Date: 11/2/2019
*Instructor: Andrew Seely
*Purpose: to create a class that extends employee, annual sales was added to the constructor
*/
public class Salesman extends Employee {
	//instance variable
	private int annualSales;
	
	/**
	 * constructor
	 * @param name
	 * @param monthlySalary
	 * @param annualSales
	 */
	public Salesman(String name, int monthlySalary, int annualSales) {
		super(name, monthlySalary);
		this.annualSales = annualSales;
	}
	//override superclass method
	@Override
	public int annualSalary() {
		double comRate = .02;
		double commission = 0;
		double totalAnnualPay = 0;
		
		commission = this.getAnnualSales()*comRate;
		if (commission <= 20000) {
			totalAnnualPay = (this.getMonthlySalary()*12)+commission;
		}else {
			totalAnnualPay = (this.getMonthlySalary()*12)+20000;
		}
		return (int) totalAnnualPay;
		
	}
	//getters and setters
	public int getAnnualSales() {
		return annualSales;
	}
	public void setAnnualSales(int annualSales) {
		this.annualSales = annualSales;
	}
	
	public String toString() {
		return super.toString() + "\nAnnual Sales:\t$" + this.getAnnualSales();
	}
	
}//end of salesman

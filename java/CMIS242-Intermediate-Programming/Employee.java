/*
*Program: Employee.java
*Author: David Mejia
*Course: CMIS 242
*Date: 11/2/2019
*Instructor: Andrew Seely
*Purpose: to create a class called employee so that we can extend other classes using this one.
*/
public class Employee {
	//instance variables for employee
	private String name;	
	private int monthlySalary;
	
	/**
	 * constructor
	 * @param name
	 * @param monthlySalary
	 */
	public Employee(String name,int monthlySalary) {
		this.name = name;
		
		this.monthlySalary = monthlySalary;
	}
	
	//calculates the annual salary of the employee using the monthly salary
	public int annualSalary() {
		return this.monthlySalary*12;
	}
	
	//getters and setters
	public int getMonthlySalary() {
		return this.monthlySalary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	
	
	public String toString() {
		return "\nEmployee Name:\t" + this.getName() + 
				"\nMonthly Salary:\t$" + this.getMonthlySalary();
	}
}

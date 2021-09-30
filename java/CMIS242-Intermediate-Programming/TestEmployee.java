/*
*Program: TestEmployee.java
*Author: David Mejia
*Course: CMIS 242
*Date: 11/2/2019
*Instructor: Andrew Seely
*Purpose: to create a class to test the employee, salesman and executive classes
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestEmployee {

	public static void main(String[] args) throws IOException {
		double average2014 = 0;
		double average2015 = 0;
		double sumForAvg2014= 0;
		double sumForAvg2015= 0;
		
		//create list for each employee and year
		ArrayList<String> employeesInfo = new ArrayList<String>();
		ArrayList<Employee> employee2014 = new ArrayList<Employee>();
		ArrayList<Employee> employee2015 = new ArrayList<Employee>();
		
		
		/**
		 * TEST TXT FILE GOES HERE
		 */
		File newFile = new File("testcase3.txt");
		
		
		//only if the file name is found otherwise error message display
		if(newFile.exists()) {
			Scanner reader = new Scanner(newFile).useDelimiter("\\s*\\n");
			
			//read the information from the file
			while(reader.hasNext()) {
				employeesInfo.add(reader.next());
				
			}	
			reader.close();
			//this will split each row that was read from the file
			//and set to each column
			for (int i = 0;i<employeesInfo.size();i++) {
				String[] column = employeesInfo.get(i).split(" ");
				/* column[0] = year
				 * column[1] = employee type
				 * column[2] = name
				 * column[3] = monthly salary
				 * column[4] = annual sales or stock,
				 */
				//create and add correct employee type into corresponding year.
				if (column[1].equalsIgnoreCase("employee")&&column[0].equalsIgnoreCase("2014")) {
					employee2014.add(new Employee(column[2],Integer.parseInt(column[3])));
				}
				else if (column[1].equalsIgnoreCase("salesman")&&column[0].equalsIgnoreCase("2014")) {
					employee2014.add(new Salesman(column[2],Integer.parseInt(column[3]),Integer.parseInt(column[4])));
				}
				else if (column[1].equalsIgnoreCase("executive")&&column[0].equalsIgnoreCase("2014")) {
					employee2014.add(new Executive(column[2],Integer.parseInt(column[3]),Integer.parseInt(column[4])));
				}
				else if (column[1].equalsIgnoreCase("executive")&&column[0].equalsIgnoreCase("2015")) {
					employee2015.add(new Executive(column[2],Integer.parseInt(column[3]),Integer.parseInt(column[4])));
				}
				else if (column[1].equalsIgnoreCase("employee")&&column[0].equalsIgnoreCase("2015")) {
					employee2015.add(new Employee(column[2],Integer.parseInt(column[3])));
				}
				else if (column[1].equalsIgnoreCase("salesman")&&column[0].equalsIgnoreCase("2015")) {
					employee2015.add(new Salesman(column[2],Integer.parseInt(column[3]),Integer.parseInt(column[4])));
				}
				
			}
			//Display employee information separated by year
			System.out.println("**********************************************");
			System.out.println("Employees for year 2014:");
			for (int i = 0;i<employee2014.size();i++) {
				System.out.println(employee2014.get(i).toString());
				System.out.println("Yearly Salary:\t$"+employee2014.get(i).annualSalary());
				sumForAvg2014 += employee2014.get(i).annualSalary();
				average2014 = sumForAvg2014/employee2014.size();			
			}
			System.out.printf("\nThe average salary for 2014 was: $%.2f.%n", average2014);
			System.out.println("**********************************************");
			System.out.println("Employees for year 2015:");
			for (int i = 0;i<employee2015.size();i++) {
				System.out.println(employee2015.get(i).toString());
				System.out.println("Yearly Salary:\t$"+employee2015.get(i).annualSalary());
				sumForAvg2015 += employee2015.get(i).annualSalary();
				average2015 = sumForAvg2015/employee2015.size();
				}
			System.out.printf("\nThe average salary for 2015 was: $%.2f.%n", average2015);
		}
		else {
			//if no file exist then error message appears
			System.out.println("*************************************");
			System.out.println("File: '" + newFile + "' doesn't exist");
			System.out.println("Please check and run program again");
			System.out.println("*************************************");
		}
		
	}//end of main
}//end of TestEmployee

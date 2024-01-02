package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		List<Employee> employe = new ArrayList<>();
		
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Enter full file path : ");
		String path = sc.nextLine();
		
		try	(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while (line != null) {
				
				String [] fields = line.split(",");
				
				String name =  fields[0];
				String email =  fields[1];
				double empSalary =  Double.parseDouble(fields[2]);
				
				Employee emp = new Employee(name, email, empSalary);
				employe.add(emp);
				line = br.readLine();
				
			}
			
			System.out.print("Enter Salary: ");
			double salary = sc.nextDouble();
			
			System.out.printf("Email of people whose salary is more than %.2f:%n", salary);
			
			List<String> email = employe.stream()
					.filter(emp -> emp.getSalary() > salary)
					.map(Employee::getEmail)
					.sorted()
					.toList();
			
			email.forEach(System.out::println);
			
			double sumSalaryM = employe.stream()
					.filter(emp -> emp.getName().charAt(0) == 'M')
					.mapToDouble(Employee::getSalary)
					.sum();
			
			System.out.printf("Sum of salary of people whose name starts with 'M' : %.2f", sumSalaryM);
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		sc.close();
	}

}

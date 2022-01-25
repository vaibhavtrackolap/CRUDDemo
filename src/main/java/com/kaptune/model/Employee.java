package com.kaptune.model;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(noClassnameStored = true)
public class Employee {
	@Id
	private int id;
	private String empName;
	private String department;
	private int salary;

	public Employee(int id, String empName, String department, int salary) {
		super();
		this.id = id;
		this.empName = empName;
		this.department = department;
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", Department=" + this.department + ", Salary=" + this.salary
				+ "]";
	}

}

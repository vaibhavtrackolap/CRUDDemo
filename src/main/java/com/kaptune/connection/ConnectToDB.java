package com.kaptune.connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.kaptune.model.Employee;
import com.mongodb.MongoClient;
import com.mongodb.connection.Stream;


public class ConnectToDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating a Mongo client
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.kaptune.connection");
		Datastore datastore = morphia.createDatastore(new MongoClient(), "Kaptune");
		createEmployees(datastore);
		getHighestSalary(datastore);
		getLowestSalary(datastore);
		updateName(datastore);
		updateSalary(datastore);
	}

	private static void createEmployees(Datastore datastore) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(101, "Vaibhav", "Operations", 200000));
		employees.add(new Employee(102, "Ram Sharma", "HR", 300000));
		employees.add(new Employee(103, "Shayam Sharma", "Sales", 500000));
		employees.add(new Employee(104, "Rahul Sharma", "Sales", 100000));
		
		datastore.save(employees);
		
	}
	
	
	private static void getHighestSalary(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		
		qr.order("-salary").limit(1);
		System.out.println("Details of employee with highest salary");
		System.out.println(qr.asList());
	}
	
	private static void getLowestSalary(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		
		qr.order("salary").limit(1);
		System.out.println("Details of employee with lowest salary");
		System.out.println(qr.asList());
	}
	
	private static void updateName(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		UpdateOperations<Employee> updates = datastore.createUpdateOperations(Employee.class);
		
		qr.field("empName").contains("Vaibhav");
		updates.set("empName", "MS Dhoni");
		
		datastore.update(qr, updates);
		
		List<Employee> ls = (List<Employee>) datastore.createQuery(Employee.class)
				  .field("empName")
				  .contains("MS Dhoni")
				  .asList();
		System.out.println("Name after updation");
		System.out.println(ls);
	}
	
	private static void updateSalary(Datastore datastore)
	{
		Query<Employee> qr = datastore.createQuery(Employee.class);
		UpdateOperations<Employee> updates = datastore.createUpdateOperations(Employee.class);
		
		qr.field("department").contains("Sales");
		updates.inc("salary", 100);
		
		datastore.update(qr, updates);
		
		List<Employee> ls = (List<Employee>) datastore.createQuery(Employee.class)
				  .field("department")
				  .contains("Sales")
				  .asList();
		System.out.println("Salary after updation");
		System.out.println(ls);
			
	}

}

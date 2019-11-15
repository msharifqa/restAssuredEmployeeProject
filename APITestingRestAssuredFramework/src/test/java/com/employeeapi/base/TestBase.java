package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="97489"; //HardCoded - Input for Get Details of Single Employee & Update Employee
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		logger= Logger.getLogger("EmployeesRestApi"); //added logger
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}

}

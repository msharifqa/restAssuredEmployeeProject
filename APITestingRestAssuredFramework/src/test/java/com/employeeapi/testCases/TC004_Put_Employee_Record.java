package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC004_Put_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException {
		logger.info("******************** Started TC004_Put_Employee_Records ********************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using teh put Mehtod
		//{"name":"MdShar", "salary":"70000", "age":"30"}
		
		JSONObject requestParams = new JSONObject();
		logger.info("New Employee_Name => "+empName);
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		

		//Adde a header Stating the Request Body is a JSON
		httpRequest.header("Content-Type", "application/json");
		
		//Add the JSON to the Body of the Request
		httpRequest.body(requestParams.toJSONString());
		response=httpRequest.request(Method.PUT, "/update/"+empID);
		
		Thread.sleep(4000);
		
	}
	
	//employee_name":"aj96671","employee_salary":"101010","employee_age":"1","profile_image":""},{"id":"96672",
	//employee_name":"aj96671","employee_salary":"101010","employee_age":"1","profile_image":""},{"id":"96672",
	//"employee_name":"aj96674","employee_salary":"101010","employee_age":"1","profile_image":""},{"id":"96675",
	//"employee_name":"MdShard","employee_salary":"23284","employee_age":"6","profile_image":""},{"id":"96676",
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
		
	}
	
	@Test
	void checkStatusCode() {
		logger.info("********** Checking Status Code *********");
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test
	void checkStatusLine() {
		logger.info("******** Checking Status Line ********");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	
	@Test
	void checkContentType() {
		logger.info("******** Checking Content-Type ********");
		
		String contentType=response.header("Content-Type");
		logger.info("Content-Type is ==> "+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	@Test
	void checkServerType() {
		logger.info("******** Checking Server Type ********");
		
		String serverType=response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("******** Checking Content Encoding ********");
		
		String contentEncoding=response.header("Content-Encoding");
		logger.info("Content Encoding is ==> "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	void checkContentLength() {
		logger.info("******** Checking Content Lenght ********");
		
		String contentLenght=response.header("Content-Length");
		logger.warn("Content Lenght is ==> "+ contentLenght);
		
		if(Integer.parseInt(contentLenght)<1500);
		Assert.assertTrue(Integer.parseInt(contentLenght)<1500);
		
	}
	
	@Test 
	void checkCookies(){
		logger.info("******** Checking Cookies ********");
		String cookies=response.getCookie("PHPSESSID");
		logger.info("Value of the Cookies is ==> "+cookies);
		
	}
	
	@AfterClass
	void tearDown() {
		
		logger.info("******************** Finished TC004_Put_Employee_Records ********************");
	}

}

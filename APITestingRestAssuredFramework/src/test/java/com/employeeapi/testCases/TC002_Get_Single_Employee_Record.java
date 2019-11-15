package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_Record extends TestBase {
	
	

	@BeforeClass
	void getSingleEmployee() throws InterruptedException {
		logger.info("******************** Started TC002_Get_Single_Employees_Records ********************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response=httpRequest.request(Method.GET, "/employees/"+empID);  //this ID is coming from TestBase class  
		
		Thread.sleep(4000);
		
	}
	
	@Test
	void checkResposeBody() {
		logger.info("************ Checking Response Body ************");
		
		String responseBody = response.getBody().asString();
		//logger.info("Response Body ====> "+responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);

	}
	
	@Test
	void checkStatusCode() {
		logger.info("********** Checking Status Code *********");
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime() {
		
		logger.info("******** Checking Response Time ********");
		
		long responseTime=response.getTime();
		logger.info("Response Time is ==> "+responseTime);

		Assert.assertTrue(responseTime<8000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("******** Checking Status Line ********");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 [200 OK]");
	}
	
	
	@Test
	void checkContentType() {
		logger.info("******** Checking Content-Type ********");
		
		String contentType=response.header("Content-Type");
		logger.info("Content-Type is ==> "+contentType);
		Assert.assertEquals(contentType, "text/html;charset=UTF-8");
		
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
		
		logger.info("******************** Finished TC002_Get_Single_Employee_Records ********************");
	}
	

}

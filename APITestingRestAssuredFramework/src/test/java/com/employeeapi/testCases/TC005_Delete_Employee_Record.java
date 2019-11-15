package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delete_Employee_Record extends TestBase{
	
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("******************** Started TC005_Delete_Employee_Records ********************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator=response.jsonPath();

		//Capture ID
		String empID=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE, "/update/"+empID); //Pass ID to delete record
		
		Thread.sleep(4000);
		
	}
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
		
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
	void checkCookies(){
		logger.info("******** Checking Cookies ********");
		String cookies=response.getCookie("PHPSESSID");
		logger.info("Value of the Cookies is ==> "+cookies);
		
	}
	
	@AfterClass
	void tearDown() {
		
		logger.info("******************** Finished TC005_Delete_Employee_Records ********************");
	}

}





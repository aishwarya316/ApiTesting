package com.qa.tests;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.test;
import com.qa.client.RestClient;

public class GetApiTest extends test{
test testBase;
RestClient restClient;
String url;


@BeforeMethod
public void setUp() throws ClientProtocolException, IOException{
	test testBase= new test();
	String serviceUrl=prop.getProperty("URL");
	String apiUrl= prop.getProperty("serviceURL");
	
	 url=serviceUrl+apiUrl;
	}

	@Test
	public void apiTest() throws ClientProtocolException, IOException
	{
	 restClient=new RestClient();
		restClient.get(url);	
	}
}

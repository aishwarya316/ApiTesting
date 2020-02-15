package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.test;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends test {
	test testBase;
	RestClient restClient;
	String url;
	CloseableHttpResponse closeableHTTPResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		test testBase= new test();
		String serviceUrl=prop.getProperty("URL");
		String apiUrl= prop.getProperty("serviceURL");
		
		 url=serviceUrl+apiUrl;
		}

@Test
public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException
{
	restClient= new RestClient();
	
	       //to get headers
			HashMap<String,String> headerMap= new HashMap<String, String>();
			headerMap.put("Content-Type","application/json; charset=utf-8");
			
			//Jackson API
			ObjectMapper mapper= new ObjectMapper();
			
			Users users= new Users("morpheus","leader");
			
			//marshalling---java obj to json file
			mapper.writeValue(new File("F:\\API_testing\\restapi\\src\\main\\java\\com\\qa\\data\\Users.json"), users);
			
			//marshalling--->java object to json string
			String jsonAsString =mapper.writeValueAsString(users);
			System.out.println(jsonAsString);
			
			closeableHTTPResponse=restClient.post(url,jsonAsString,headerMap);
			
			//1.status code
			int statusCode=closeableHTTPResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode,RESPONSE_CODE_201);
			
			//2.Json String
			String reponseString=EntityUtils.toString(closeableHTTPResponse.getEntity(),"UTF-8");
		    JSONObject responseJson=new JSONObject(reponseString);
		    System.out.println(" response from JSON" +responseJson);
		    
		    //Json to java object
		   Users userObj= mapper.readValue(reponseString, Users.class);
		    System.out.println(userObj);
		    
		    System.out.println(users.getName().equals(userObj.getName()));
		    System.out.println(users.getJob().equals(userObj.getJob()));
}
}

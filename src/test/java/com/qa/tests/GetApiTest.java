package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.test;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;


public class GetApiTest extends test{
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
	public void apiTest() throws ClientProtocolException, IOException
	{
	 restClient=new RestClient();
	 closeableHTTPResponse=restClient.get(url);
		
		//b. status code 
				int statusCode=closeableHTTPResponse.getStatusLine().getStatusCode();
			    System.out.println(statusCode);
			    Assert.assertEquals(statusCode,RESPONSE_CODE_200);
			    
			    //c. json string
			    String reponseString=EntityUtils.toString(closeableHTTPResponse.getEntity(),"UTF-8");
			    JSONObject responseJson=new JSONObject(reponseString);
			    System.out.println(" response from JSON" +responseJson);
			    
			    //page number
			   String page= TestUtil.getValueByJPath(responseJson,"/page");
			   System.out.println("page number is ---->"+page);
			   Assert.assertEquals(Integer.parseInt(page),1); 
			   
			   //total pages number 
			   String totalpages= TestUtil.getValueByJPath(responseJson,"/total_pages");
			   System.out.println("total number of pages is---->"+totalpages);
			   Assert.assertEquals(Integer.parseInt(totalpages),2); 
			   
			   //Get value from JSON ARRAY:
			   String FirstName=TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");
			   String lastName=TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
			   String Avatar=TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
			   String ID=TestUtil.getValueByJPath(responseJson,"/data[0]/id");

			   System.out.println(FirstName);
			   System.out.println(lastName);
			   System.out.println("avatar is "+Avatar);
			   System.out.println("id is "+ID);



			   
			   
			   
			   
			    //d. all headers
			    Header[] headerArray=closeableHTTPResponse.getAllHeaders();
			    HashMap<String,String> allHeaders= new HashMap<String,String>();
			     
			    for(Header header: headerArray){
			    	allHeaders.put(header.getName(),header.getValue());
			    }
			    System.out.println("headers Array---->"+allHeaders);
	}
	@Test
	public void GetapiTestWithHeaders() throws ClientProtocolException, IOException
	{
	 restClient=new RestClient();
		restClient.get(url);
		
		//to get headers
		HashMap<String,String> headerMap= new HashMap<String, String>();
		headerMap.put("page","2");
		
	}
}

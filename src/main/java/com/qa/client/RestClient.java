package com.qa.client;

import java.io.IOException;
import java.util.HashMap;



import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//GET Method
	public void get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient= HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		
		//b. status code 
		CloseableHttpResponse closeableHTTPResponse =httpClient.execute(httpget);
		int statusCode=closeableHTTPResponse.getStatusLine().getStatusCode();
	    System.out.println(statusCode);
	    
	    //c. json string
	    String reponseString=EntityUtils.toString(closeableHTTPResponse.getEntity(),"UTF-8");
	    JSONObject responseJson=new JSONObject(reponseString);
	    System.out.println(" response from JSON" +responseJson);
	    
	    //d. all headers
	    Header[] headerArray=closeableHTTPResponse.getAllHeaders();
	    HashMap<String,String> allHeaders= new HashMap<String,String>();
	     
	    for(Header header: headerArray){
	    	allHeaders.put(header.getName(),header.getValue());
	    }
	    System.out.println("headers Array---->"+allHeaders);
	    }
	    
	}
	




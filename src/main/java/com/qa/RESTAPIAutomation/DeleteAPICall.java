package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class DeleteAPICall
{
    @Test
	public static void deleteAPI() 
	{
    	CloseableHttpClient client=HttpClientBuilder.create().build();
    	
 	    //Delete API call for
    	HttpDelete deleteapi=new HttpDelete("https://developer.twitter.com/en");
    	
 	    CloseableHttpResponse reposnse=null;
 	  
 	    
 	    //Calling and hitting the API to execute delete API
 	    try
 	    {   deleteapi.setHeader("Authorization","Bearer _sdgdsfgdfgdsfgtrerwtrew");
 	    	reposnse=client.execute(deleteapi);
 	    	
 	    }catch(ClientProtocolException e)
 	    {
 	    	e.printStackTrace();
 	    }catch(IOException ex)
 	    {
 	    	ex.printStackTrace();
 	    }
 	      //Reading the response  and status body.
 	      int i= reposnse.getStatusLine().getStatusCode();
 		  System.out.println(i);
 		  Assert.assertEquals(200, i);
 			  
 		//Print the response for the body and store the response in string, here you cannot directly store the response and print.
		  HttpEntity entity=  reposnse.getEntity();
		  String data=null;
		  try
		  {
			   data=EntityUtils.toString(entity);
			 
		  }catch(Exception e)
		  {
			  
		  }
		  System.out.println(data);
		  
		  //GetJson value using Rest Assured Jsonpath value
		  JsonPath json=new JsonPath(data);
		  String dataapi=json.getString("Meta_.id");
		  System.out.println(dataapi);
	}
	
}

package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.path.json.JsonPath;

public class Postcall 
{
	
	    @Test
		public void post_Client() throws ClientProtocolException, IOException
	  {
		  //1 *****Opening the httpClinet Connection one more method********/
	      CloseableHttpClient httpclinet2=HttpClientBuilder.create().build();
	      
	      // 2 Url purpose for Post
	      HttpPost post= new HttpPost("https://reqres.in");
	     
	      // 3 Add Header
		  post.addHeader("Authorization","Bearer_ HGHKHGKHGKGKHGHGHJGKHGK");
		  // 3 Add Header one more way
		  post.setHeader("Content-Type","application/json");
		  
		  PojoTemplate pojoobject=new PojoTemplate("Christopher", "Rohan","male","02-02-1986","koko1k@yahoo.com",
					"+9845281948","https://www.naveenautomation.com","123,new Jercy","active");
		  
		  ObjectMapper map=new ObjectMapper();
			String jsondata=map.writeValueAsString(pojoobject);
			System.out.println(jsondata);
			
			//Adding body to the post call after creating the pojo object with objectmapper class
			StringEntity body=new StringEntity(jsondata);
			
		  post.setEntity(body);
		  
		  //4 execute the API and store response
		   CloseableHttpResponse response= httpclinet2.execute(post);
		   
		   
		  
		  // Read the status code
		   int i= response.getStatusLine().getStatusCode();
		  System.out.println(i);
		  Assert.assertEquals(200, i);
		  
		  //Print the response and store the response in string, here you cannot directly store the response and print.
		  HttpEntity entity=  response.getEntity();
		  String data=EntityUtils.toString(entity);
		  System.out.println(data);
		  
		  //GetJson value using Rest Assured Jsonpath value
		  JsonPath json=new JsonPath(data);
		  String dataapi=json.getString("Meta_.id");
		  System.out.println(dataapi); 

	  }
	}



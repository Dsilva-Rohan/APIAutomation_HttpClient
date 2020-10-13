package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;

public class CallingPutCall
{
  @Test
  public static void putcall() throws ClientProtocolException, IOException
  {
	  CloseableHttpClient httpclinet2=HttpClientBuilder.create().build();
      
      // 2 Url purpose for Post
      HttpPut putcall= new HttpPut("https://reqres.in");
     
      // 3 Add Header
      putcall.addHeader("Authorization","Bearer_ HGHKHGKHGKGKHGHGHJGKHGK");
	  // 3 Add Header one more way
      putcall.setHeader("Content-Type","application/json");
	  
	  PojoTemplate pojoobject=new PojoTemplate("Christopher", "Rohan","male","02-02-1986","koko1k@yahoo.com",
				"+9845281948","https://www.naveenautomation.com","123,new Jercy","active");
	  
	  pojoobject.setstatus("InActive");
	  
	  ObjectMapper map=new ObjectMapper();
		String jsondata=map.writeValueAsString(pojoobject);
		System.out.println(jsondata);
		
		//Adding body to the post call after creating the pojo object with objectmapper class
		StringEntity body=new StringEntity(jsondata);
		
		putcall.setEntity(body);
	  
	  //4 execute the API and store response
	   CloseableHttpResponse response= httpclinet2.execute(putcall);
	   
	   
	  
	  // Read the status code
	   int i= response.getStatusLine().getStatusCode();
	  System.out.println(i);
	  Assert.assertEquals(200, i);
	  
	  //Print the response and store the response in string, here you cannot directly store the response and print.
	  HttpEntity entity=  response.getEntity();
	  String data=null;
	  try
	  {
	   data=EntityUtils.toString(entity);
	  }catch(Exception e)
	  {
		 e.printStackTrace();
	  }
	 
	  System.out.println(data);
	  
	  //GetJson value using Rest Assured Jsonpath value
	  JsonPath json=new JsonPath(data);
	  String dataapi=json.getString("Meta_.id");
	  System.out.println(dataapi);
	  Assert.assertEquals(dataapi,pojoobject.getstatus());

  }
 }


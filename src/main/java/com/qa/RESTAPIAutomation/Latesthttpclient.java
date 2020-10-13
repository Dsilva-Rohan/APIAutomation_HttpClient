package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Latesthttpclient
{
    @Test
	public void latest_Client() throws ClientProtocolException, IOException
  {
	  //1 *****Opening the httpClinet Connection one more method********/
      CloseableHttpClient httpclinet2=HttpClientBuilder.create().build();
      
      // 2 Url purpose
      HttpGet httpget= new HttpGet("https://reqres.in");
     
      // 3 Add Header
	  httpget.addHeader("Authorization","Bearer_ HGHKHGKHGKGKHGHGHJGKHGK");
	 
	  /*********4 execute the API and store response************/ 
	   CloseableHttpResponse response= httpclinet2.execute(httpget);
	  
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
	  
	  //working on Jway API alternative of jpath to retrieve the value.
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
  }
}

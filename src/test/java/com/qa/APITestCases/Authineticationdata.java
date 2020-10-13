package com.qa.APITestCases;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Authineticationdata
{
   CredentialsProvider cred=new BasicCredentialsProvider();
   
   @Test
   public void credentilas() throws ClientProtocolException, IOException
   {
	   cred.setCredentials(new AuthScope("httpbin.org", 80),
			   new UsernamePasswordCredentials("User","passwd"));
	   
	   CloseableHttpClient data=HttpClients.custom().setDefaultCredentialsProvider(cred).build();
	   
	   HttpGet geturl=new HttpGet("http://httpbin.org/basic-auth/user/passwd");
	   CloseableHttpResponse response=data.execute(geturl);
	   //Print the response and store the response in string, here you cannot directly store the response and print.
		  HttpEntity entity=  response.getEntity();
		  String datas=EntityUtils.toString(entity);
		  System.out.println(datas);
		  
		  //GetJson value using Rest Assured Jsonpath value
		  JsonPath json=new JsonPath(datas);
		  String dataapi=json.getString("");
		  System.out.println(dataapi);
		  
	   
   }
   
  
}

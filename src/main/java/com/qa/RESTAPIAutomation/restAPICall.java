package com.qa.RESTAPIAutomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class restAPICall 

{
	String keyvalueString;
	String value;
	
	public static CloseableHttpResponse gethttpmethod(String url) throws ClientProtocolException, IOException
	 {
	   /*****Opening the httpClinet Connection********/
       CloseableHttpClient httpclinet=HttpClients.createDefault();
       
       /*****Opening the httpClinet Connection one more method********/
       CloseableHttpClient httpclinet2=HttpClientBuilder.create().build();
       
       /*****  Calling the URL with Get HTTP Method *****/
	   HttpGet httpget= new HttpGet(url);
	   CloseableHttpResponse response=httpclinet.execute(httpget);
	   
	   return response;
	 }
	
	public static CloseableHttpResponse gethttpmethod(String url,HashMap<String,String> mapheader) throws ClientProtocolException, IOException
	 {
	   /*****Opening the httpClinet Connection********/
      CloseableHttpClient httpclinet=HttpClients.createDefault();
      
      /*****  Calling the URL with Get HTTP Method *****/
	   HttpGet httpget= new HttpGet(url);
	   for(Map.Entry<String, String> entry: mapheader.entrySet())
	   httpget.addHeader(entry.getKey(),entry.getValue());
		   
	   CloseableHttpResponse response=httpclinet.execute(httpget);
	   
	   return response;
	 } 
}
 
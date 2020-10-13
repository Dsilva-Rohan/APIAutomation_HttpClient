package com.qa.APITestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.APITestBase.TestBase;
import com.qa.APIUtils.APIJsonUtility;
import com.qa.RESTAPIAutomation.*;

public class APIGetMethodTestcase 
{

	TestBase base;
	String uri;
	String keyvalue;
	String value;
	@BeforeMethod()
	public void Setup() throws IOException 
	{
		 base=new TestBase();
		String firsturl=base.property.getProperty("url");
		String secondurl=base.property.getProperty("serviceurl");
		 uri=firsturl+secondurl;
	}
	
	@Test(priority=1)
	public void restapicall() throws ClientProtocolException, IOException
	{
		
		SoftAssert asserts=new SoftAssert();
		CloseableHttpResponse response=restAPICall.gethttpmethod(uri);
		
		
		 /******* Taking the Response code ****************/   
		int responsecode= response.getStatusLine().getStatusCode();
		int statuscodevalue=Integer.parseInt(base.property.getProperty("statuscode200"));		
		System.out.println("Response code of the API is :"+responsecode);
		asserts.assertEquals(responsecode,statuscodevalue);
		
		/*******Taking the Json from the response ******/
		String responsejson=EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responsejsonobject=new JSONObject(responsejson);
		
		String perpage=APIJsonUtility.getValueByJPath(responsejsonobject, "/per_page"); 
		int jsonvalue=Integer.parseInt(perpage);
		System.out.println("Response Json from the API is:"+perpage );
		asserts.assertEquals(jsonvalue,6);
		
		String email=APIJsonUtility.getValueByJPath(responsejsonobject, "/data[0]/email"); 
	    
		System.out.println("Response Json from the API is email:"+email );
		asserts.assertEquals(email,"george.bluth@reqres.in");
		
       String ad=APIJsonUtility.getValueByJPath(responsejsonobject, "/ad/company"); 
	    
	  System.out.println("Response Json from the API is AD:"+ad );
	  asserts.assertEquals(ad,"StatusCode Weekly");
		
		   /*************HeaderSection***************/
		   Header headersection[]=response.getAllHeaders();
		   HashMap<String, String> headermap=new HashMap<String,String>();
		   for(Header headerresponse:headersection)
		   {
			   headermap.put(headerresponse.getName(), headerresponse.getValue());
			  
			   
		   }
		     System.out.println("Header Section of API call:"+headermap);
				  HashMap<String, String> cloneheader=new HashMap<String,String>();
				  cloneheader=(HashMap<String, String>) headermap.clone();
				  for(Entry	s:cloneheader.entrySet())
				  { 
					   keyvalue=(String) s.getKey();
				       value=(String) s.getValue();
				      System.out.println("keyvalueString::::::"+keyvalue);
				      System.out.println("value::::::"+value); 
				      
				  }
				  String abc=cloneheader.get("CF-Cache-Status");
				  asserts.assertTrue(cloneheader.containsKey("Transfer-Encoding"));        
	              asserts.assertTrue(cloneheader.containsValue("chunked"));
	              System.out.println("value Transfer-Encoding::::::"+abc); 
		          asserts.assertAll();
		
}
	@Test(priority=2)
	public void RestAPIcallwithHeader() throws ClientProtocolException, IOException
	{
		
		SoftAssert asserts=new SoftAssert();
		HashMap<String,String> headermaps=new HashMap<String,String>();
		
		headermaps.put(base.property.getProperty("headerKey"), base.property.getProperty("headervalue"));
		
		
		CloseableHttpResponse response=restAPICall.gethttpmethod(uri,headermaps);
		
		
		 /******* Taking the Response code ****************/   
		int responsecode= response.getStatusLine().getStatusCode();
		int statuscodevalue=Integer.parseInt(base.property.getProperty("statuscode200"));		
		System.out.println("Response code of the API is :"+responsecode);
		asserts.assertEquals(responsecode,statuscodevalue);
		
		/*******Taking the Json from the response ******/
		String responsejson=EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responsejsonobject=new JSONObject(responsejson);
		
		String perpage=APIJsonUtility.getValueByJPath(responsejsonobject, "/per_page"); 
		int jsonvalue=Integer.parseInt(perpage);
		System.out.println("Response Json from the API is:"+perpage );
		asserts.assertEquals(jsonvalue,6);
		
		String email=APIJsonUtility.getValueByJPath(responsejsonobject, "/data[0]/email"); 
	    
		System.out.println("Response Json from the API is email:"+email );
		asserts.assertEquals(email,"george.bluth@reqres.in");
		
       String ad=APIJsonUtility.getValueByJPath(responsejsonobject, "/ad/company"); 
	    
	  System.out.println("Response Json from the API is AD:"+ad );
	  asserts.assertEquals(ad,"StatusCode Weekly");
		
		   /*************HeaderSection***************/
		   Header headersection[]=response.getAllHeaders();
		   HashMap<String, String> headermap=new HashMap<String,String>();
		   for(Header headerresponse:headersection)
		   {
			   headermap.put(headerresponse.getName(), headerresponse.getValue());
			  
			   
		   }
		     System.out.println("Header Section of API call:"+headermap);
				  HashMap<String, String> cloneheader=new HashMap<String,String>();
				  cloneheader=(HashMap<String, String>) headermap.clone();
				  for(Entry	s:cloneheader.entrySet())
				  { 
					   keyvalue=(String) s.getKey();
				       value=(String) s.getValue();
				      System.out.println("keyvalueString::::::"+keyvalue);
				      System.out.println("value::::::"+value); 
				      
				  }
				  String abc=cloneheader.get("CF-Cache-Status");
				  asserts.assertTrue(cloneheader.containsKey("Transfer-Encoding"));        
	              asserts.assertTrue(cloneheader.containsValue("chunked"));
	              System.out.println("value Transfer-Encoding::::::"+abc); 
		          asserts.assertAll();
		
}

		
	}


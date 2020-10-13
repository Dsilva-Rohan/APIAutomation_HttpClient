package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class Authontication1
{
     @Test
	public static void auth1() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException
	{
	    String ConsumerKey="abcsdeddfdsgdsgadg";
	    String ConsumerScecret="wdadsfadsfasdgasfg";
	    String AccessToken="sefadsgsdgsdfgfdg";
	    String AccessTokenScecret="sdfdsfsdfgsd";
	    
	   //Passing OAuth1.0;
	    OAuthConsumer consumer=new CommonsHttpOAuthConsumer(ConsumerKey,ConsumerScecret);
	    consumer.setTokenWithSecret(AccessToken, AccessTokenScecret);
	   
	    //Creating the client to know what is the client to ue call the API
	    CloseableHttpClient client=HttpClientBuilder.create().build();
	    HttpPost post=new HttpPost("https://developer.twitter.com/en");
	    
	    //Passing the data for the Oauth data 
	    consumer.sign(post);
	    
	    //Calling and hitting the API to execute
	    CloseableHttpResponse reposnse=client.execute(post);
	    
	     //Reading the response  
	      int i= reposnse.getStatusLine().getStatusCode();
		  System.out.println(i);
		  Assert.assertEquals(200, i);
		  
		  //Print the response for the body and store the response in string, here you cannot directly store the response and print.
		  HttpEntity entity=  reposnse.getEntity();
		  String data=EntityUtils.toString(entity);
		  System.out.println(data);
		  
		  //GetJson value using Rest Assured Jsonpath value
		  JsonPath json=new JsonPath(data);
		  String dataapi=json.getString("Meta_.id");
		  System.out.println(dataapi);
	    
	  
	}

}

package com.qa.RESTAPIAutomation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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

public class GetAPIAuthCall
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
	    CloseableHttpClient client=HttpClientBuilder.create().build();
	    HttpGet getcall=new HttpGet("https://developer.twitter.com/en");
	    consumer.sign(getcall);
	    CloseableHttpResponse reposnse=client.execute(getcall);
	    
	    int i= reposnse.getStatusLine().getStatusCode();
		  System.out.println(i);
		  Assert.assertEquals(200, i);
		  
		  //Print the response and store the response in string, here you cannot directly store the response and print.
		  HttpEntity entity=  reposnse.getEntity();
		  String data=EntityUtils.toString(entity);
		  System.out.println(data);
		  
		  //GetJson value using Rest Assured Jsonpath value
		  JsonPath json=new JsonPath(data);
		  String dataapi=json.getString("Meta_.id");
		  System.out.println(dataapi);
	}
	
}

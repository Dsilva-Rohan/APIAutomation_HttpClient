package com.qa.APITestBase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase
{
	public Properties property;
	
	public TestBase() throws IOException
  {
	  try
	  {  
	    property=new Properties();
	    FileInputStream file=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/qa/APIconfig/config.properties");
	    property.load(file);
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Given configuration file not found");
		  e.printStackTrace();
	  }
	  catch(IOException e)
	  {
		  System.out.println("There is no Data in the file");
		  e.printStackTrace();
	  }
		/*
		 * finally { System.out.println("Final Block"); }
		 */
	  
  }
} 
 

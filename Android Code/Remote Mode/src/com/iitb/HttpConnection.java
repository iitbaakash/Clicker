/*
 This class provides the facility for accessing records from the server.
 */

//package name of the project
package com.iitb;

//Importing Java packages. 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


//HttpConnection class begins.
public class HttpConnection 
{
	private HttpClient client;
	private HttpPost httppost;
	private HttpResponse response;
	private InputStream instream;
	 List<NameValuePair> nameValuePairs;
	 private StringBuilder sb;
	 String url;

	 //Method to handle connection establishment with server and return response.
	 public  HttpResponse Establish_Connection(String serverip,List<NameValuePair> nameValuePairs,String centre)
	{
		
		 
		 try  // try-catch block to handle exception
		{
			 client = new DefaultHttpClient();//initialize object of HttpClient class.

			 if(centre.equals("Local"))//check for the centre if is Local.
			 {	 
		       //  url = "http://"+serverip+"/Clicker.php";//url to access server.
				// url = "http://"+serverip+":8080/AakashClickerV3/StudentLoginVerification";//url to access server.
				 url = "http://"+serverip+":8080/RemoteAakashClicker/StudentLoginVerification";//url to access server.
			 }
			 

			 if(centre.equals("Remote"))//check for the centre if is Remote.
			 {	 
                 // url = "http://"+serverip+"/RemoteClicker.php";//url to access server.
				 url = "http://"+serverip+":8080/RemoteAakashClicker/ParticipantLoginVerification";//url to access server.
			 }
			 
			httppost = new HttpPost(url);// initialize object of HttpPost class.
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));// a method that transform key-data value pair in something intelligible by an http server.
		
		    response = client.execute(httppost);// execute the httppost request.
		
			
		}
		catch (Exception e) 
		{
		
		}
		
		return response;// return the Http response.
		
		
	}
	
	
	//Method to handle data to be send on URL.
	 public  String getConnection(String serverip,String tabletid,String macaddress,String ipaddress,int flag,String centre)
	 {	 
		  String res="HTTP/1.1 404 Not Found"; // HTTP/1.1 4XX status code to show client error.
            try
            {
				nameValuePairs = new ArrayList<NameValuePair>(4);// initialize object of NameValuePairs.
				nameValuePairs.add(new BasicNameValuePair("EnrollmentID", String.valueOf(tabletid)));//adding TabletID to nameValuePairs object.
				nameValuePairs.add(new BasicNameValuePair("MacAddress", macaddress));//adding MacAddress to nameValuePairs object.
				nameValuePairs.add(new BasicNameValuePair("IPAddress", ipaddress));//adding IP Address to nameValuePairs object.
				nameValuePairs.add(new BasicNameValuePair("Flag", String.valueOf(flag)));//adding Flag to nameValuePairs object
				response=Establish_Connection(serverip,nameValuePairs,centre);//call Establish_Connection method();
           
				  return response.getStatusLine().toString();// return string representation of response.
            }
            
            catch(Exception e)
            {
            	
            }
            
            return res;//return "HTTP/1.1 404 Not Found" response.
         
		
	}
	 
	 // Method to handle data received from server.
	 public String getData()
	 {
		
		 try //try-catch block to handle Exception.
		{
		  instream = response.getEntity().getContent();// read the content of response and initialize to input stream.
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(instream));// initialize  Buffer Reader with Input Stream.
	
			sb = new StringBuilder();// initialize String Builder object.
			sb.append(reader.readLine());//reading buffer and appending to string builder.
			instream.close();//close the input stream.
		
         return sb.toString();// return the string builder.
			
		}
		
		catch(Exception e)
		{
			
		}
		 
		 return null;// return null string.
	
	 }
	 
	 
	
	 

}

//HttpConnection class ends.


// This activity opens the help screen during registration  when user clicks on Help Menu.
 


package com.iitb.localclicker;//package of the project.

//List of necessary android and input-output java classes.
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;


//Registration_helpActivtiy begins.
public class Registration_helpActivtiy extends Activity
{
	/** Called when the activity is first created. */
	public void onCreate(Bundle save)
	{
		super.onCreate(save);// Always call the superclass method first.
		setContentView(R.layout.help);// refer to help.xml .
	
		InputStream in=getResources().openRawResource(R.raw.registration_help);//open the registration_help.txt file present in raw folder.
		
	    String text = loadFile(in);// call loadFile() method. 
	    TextView content = (TextView)findViewById(R.id.help_content);//refer to textview having help_content id in help.xml.
	   
	    content.setMovementMethod(new ScrollingMovementMethod());// set scroll movement to Text View
	    content.setText(text);//set the text of registration_help.txt.
	}
	
	//Method to handle file input/output operation.
	
	public String loadFile(InputStream inputStream)
	{
		 ByteArrayOutputStream b = new ByteArrayOutputStream();// initialize ByteArrayOutputStream object.
		 byte[] bytes = new byte[4096];// initialize byte object.
		
		int read;
		  while(true)// loop until file operation terminates.
		  {
			  
			  try // try-catch block for handling IO Exceptions.
			  {
				read = inputStream.read(bytes);//read bytes of file.
				if (read == -1)//check if file is empty
			        break;
			      
			     b.write(bytes, 0, read);//write bytes to byte array.
			     return new String(b.toByteArray(), "UTF-8");//return the string representation of byte array.
			} 
			  catch (IOException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		  }
		
		return null;//return null if file is empty/exception occurs.
		
		}
}
//Registration_helpActivtiy ends.



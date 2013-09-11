package groundcontrol.communication;

import java.sql.Date;
import java.util.Calendar;

/******************************************************************
 * The CommunicationObject is used to store command type and values
 * to be placed into a queue and acted upon when the system is
 * ready.
 *****************************************************************/
public class CommunicationObject {
	private int identifier;
	private float value;
	private String source;
	private java.util.Date now;
	
	/******************************************************************
	 * Default constructor builds the CommunicationObject.
	 *****************************************************************/
	public CommunicationObject( int theIdentifier, int theValue, 
			String theSource){
		now = Calendar.getInstance().getTime();
		identifier = theIdentifier;
		value = theValue;
		source = theSource;
	}
	
	/******************************************************************
	 * Returns the command value.
	 *****************************************************************/
	public int getIdentifier(){ return identifier; } 
	
	/******************************************************************
	 * Returns the value of the command.
	 *****************************************************************/
	public float getValue(){ return value; }
	
	/******************************************************************
	 * Returns the source of the com object.
	 *****************************************************************/
	public String getSource(){ return source; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public String getString(){
		return " ";
	}
	
}

package groundcontrol.communication;

import java.sql.Date;
import java.util.Calendar;

/******************************************************************
 * The CommunicationObject is used to store command type and values
 * to be placed into a queue and acted upon when the system is
 * ready.
 * 
 * Command values:
 * 0  = ACK - should never be placed in object
 * 1  = Desired throttle
 * 2  = Actual throttle
 * 3  = Desired pitch
 * 4  = Actual pitch
 * 5  = Desired roll
 * 6  = Actual roll
 * 7  = Desired yaw
 * 8  = Actual yaw
 * 9  = Desired airspeed
 * 10 = Actual airspeed
 * 11 = Desired barometric altitude
 * 12 = Actual barometric altitude
 * 
 *****************************************************************/
public class CommunicationObject {
	private int command;
	private int value;
	private String source;
	private java.util.Date now;
	
	/******************************************************************
	 * Default constructor builds the CommunicationObject.
	 *****************************************************************/
	public CommunicationObject( int theCommand, int theValue, 
			String theSource){
		now = Calendar.getInstance().getTime();
		command = theCommand;
		value = theValue;
		source = theSource;
	}
	
	/******************************************************************
	 * Returns the command value.
	 *****************************************************************/
	public int getCommand(){ return command; } 
	
	/******************************************************************
	 * Returns the value of the command.
	 *****************************************************************/
	public int getValue(){ return value; }
	
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

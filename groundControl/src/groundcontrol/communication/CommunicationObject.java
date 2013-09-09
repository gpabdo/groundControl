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
 * 9  = airspeed
 * 10 = 
 * 
 *****************************************************************/
public class CommunicationObject {
	private int command;
	private int value;
	private java.util.Date now;
	
	/******************************************************************
	 * Default constructor builds the CommunicationObject.
	 *****************************************************************/
	public CommunicationObject( int theCommand, int theValue){
		now = Calendar.getInstance().getTime();
		command = theCommand;
		value = theValue;
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
	 * 
	 *****************************************************************/
	public String getString(){
		return " ";
	}
	
}

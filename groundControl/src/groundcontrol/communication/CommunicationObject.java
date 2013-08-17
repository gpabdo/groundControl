package groundcontrol.communication;

/******************************************************************
 * The CommunicationObject is used to store command type and values
 * to be placed into a queue and acted upon when the system is
 * ready.
 * 
 * Command values:
 * 1 = throttle
 * 2 = throttle trim
 * 3 = pitch
 * 4 = pitch trim
 * 5 = roll
 * 6 = roll trim
 * 7 = yaw
 * 8 = yaw trim
 * 
 *****************************************************************/
public class CommunicationObject {
	private int command;
	private int value;
	
	/******************************************************************
	 * Default constructor builds the CommunicationObject.
	 *****************************************************************/
	public CommunicationObject( int theCommand, int theValue){
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
	
}

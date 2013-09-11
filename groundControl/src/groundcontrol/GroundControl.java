package groundcontrol;

import groundcontrol.dashboard.*;
import groundcontrol.communication.*;
import groundcontrol.input.*;
import groundcontrol.state.*;

import java.util.LinkedList;

/******************************************************************
 * 
 *****************************************************************/
public class GroundControl{
	Dashboard dash;			// Dashboard object.
	Communication com;		// Communication object.
	Input human;			// Input object.
	State stateLog;			// State object.
	
	boolean run;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public GroundControl(){
		dash = new Dashboard(stateLog);
		com = new Communication();
		human = new Input(stateLog);
		stateLog = new State(com);
		run = true;
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	public void start() {
		Thread comThread = new Thread(com);		// Create com thread.
		Thread inputThread = new Thread(human); // Create input thread.
		comThread.start();						// Start com thread.
		inputThread.start();					// Start input thread.
		
		while(run){
			
		}
	}
}

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
	
	LinkedList<CommunicationObject> txqueue;
	LinkedList<CommunicationObject> rxqueue;
	
	boolean run;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public GroundControl(){
		dash = new Dashboard(txqueue, stateLog);
		com = new Communication(txqueue, rxqueue);
		human = new Input(stateLog);
		stateLog = new State(txqueue);
		txqueue = new LinkedList<CommunicationObject>();
		rxqueue = new LinkedList<CommunicationObject>();
		run = true;
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	public void start() {
		Thread comThread = new Thread(com);		// Create com thread.
		comThread.start();						// Start com thread.
		Thread inputThread = new Thread(human); // Create input thread.
		inputThread.start();					// Start input thread.
		
		while(run){
			
		}
	}
}

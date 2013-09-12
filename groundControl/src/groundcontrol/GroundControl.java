package groundcontrol;

import groundcontrol.dashboard.*;
import groundcontrol.input.*;
import groundcontrol.state.*;

/******************************************************************
 * 
 *****************************************************************/
public class GroundControl{
	Dashboard dash;			// Dashboard object.
	Input human;			// Input object.
	State droneState;		// State object.
	boolean run;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public GroundControl(){
		droneState = new State();
		dash = new Dashboard(droneState);
		human = new Input(droneState);
		run = true;
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	public void start() {
		Thread stateThread = new Thread(droneState);
		Thread inputThread = new Thread(human); 		// Create input thread.
		stateThread.start();
		inputThread.start();							// Start input thread.

		
		while(run){
			
		}
	}
}

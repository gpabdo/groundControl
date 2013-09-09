package groundcontrol.input;

import groundcontrol.communication.CommunicationObject;
import groundcontrol.state.*;

/******************************************************************
 * 
 *****************************************************************/
public class Input implements Runnable{
	State stateLog;			// State object.
	boolean run;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public Input(State theStateLog){
		stateLog = theStateLog;
		run = true;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	@Override
	public void run() {
		
		while(run){
			getState();
			// Sleep for around 1/4 second.
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void getState(){
		int command = 0;
		int value = 0;
		
		// TODO: joystick stuff.
		
		CommunicationObject newState = new CommunicationObject(command, value);
		stateLog.setState(newState);
	}
}
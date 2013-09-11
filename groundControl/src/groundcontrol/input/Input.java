package groundcontrol.input;

import groundcontrol.communication.CommunicationObject;
import groundcontrol.state.*;

/******************************************************************
 * 
 *****************************************************************/
public class Input implements Runnable{
	State currentState;			// State object.
	boolean run;
	
	controlObject throttle;
	controlObject pitch;
	controlObject roll;
	controlObject yaw;
	
	public static final int THROTTLE		= 0;
	public static final int PITCH			= 2;
	public static final int ROLL			= 4;
	public static final int YAW				= 6;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public Input(State theState){
		currentState = theState;
		
		throttle = new controlObject(THROTTLE);
		pitch = new controlObject(PITCH);
		roll = new controlObject(ROLL);
		yaw = new controlObject(YAW);
		
		run = true;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	@Override
	public void run() {
		
		while(run){
			getState();
			// Sleep for around 1/8 second.
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void getState(){

		throttle.setValue(0);
		currentState.desiredStateChange(throttle.getCommunicationObject());
		
		// TODO: joystick stuff.
		

		
		
	}
}
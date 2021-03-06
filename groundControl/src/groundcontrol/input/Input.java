package groundcontrol.input;

import groundcontrol.communication.CommunicationObject;
import groundcontrol.state.*;
import net.java.games.input.*;

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
	
	public static final int THROTTLE		= 10;
	public static final int PITCH			= 12;
	public static final int ROLL			= 14;
	public static final int YAW				= 16;
	
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
		
		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

		if( ca != null ){
			for(int i = 0; i < ca.length ; i++)
			{
				System.out.println("Found input device: " + ca[i].getName());
			}
		}

		while(run){
			//getState();
			// Sleep for around 1/8 second.
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void getState(){
		
		throttle.setValue(0);
		currentState.submitStateChange(throttle.getCommunicationObject());
		
		// TODO: joystick stuff.
		
	}
}
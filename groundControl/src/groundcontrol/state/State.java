package groundcontrol.state;

import java.util.LinkedList;
import groundcontrol.communication.*;

/******************************************************************
 * The state class is used to keep track for the current state of 
 * all aspects of the drone. When the state of a variable is 
 * changed, that change is pushed out to the txqueue. 
 *****************************************************************/
public class State {
	private LinkedList<LinkedList<CommunicationObject>> currentState;
	private Communication com;
	private boolean[] update;

	private static final int LENGTH = 20;
	private static final int WRITE_LIST_SIZE = 2000;
	private static final int TRIM_LIST_SIZE = 100;
	
	public static final int ACK 						= 0;
	public static final int DESIRED_THROTTLE 			= 1;
	public static final int ACTUAL_THROTTLE 			= 2;
	public static final int DESIRED_PITCH 				= 3;
	public static final int ACTUAL_PITCH				= 4;
	public static final int DESIRED_ROLL				= 5;
	public static final int ACTUAL_ROLL					= 6;
	public static final int DESIRED_YAW					= 7;
	public static final int ACTUAL_YAW					= 8;
	public static final int DESIRED_AIRSPEED			= 9;
	public static final int ACTUAL_AIRSPEED				= 10;
	public static final int DESIRED_BAROMETRIC_ALTITUDE	= 11;
	public static final int ACTUAL_BAROMETRIC_ALTITUDE	= 12;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public State(){
		update = new boolean[LENGTH];
		currentState = new LinkedList<LinkedList<CommunicationObject>>();
		for( int i = 0; i < LENGTH; i++){
			currentState.add(new LinkedList<CommunicationObject>());
			currentState.get(i).add(new CommunicationObject(i, 0, "GROUND"));
			update[i] = true;
		}
			
		com = new Communication();
	}
	
	/******************************************************************
	 * Connect to the drone.
	 *****************************************************************/
	public void connect(){
		Thread comThread = new Thread(com);		// Create com thread.
		comThread.start();						// Start com thread.
	}
	
	/******************************************************************
	 * This method takes a communication object and checks it against
	 * the current state of the drone. If this is a change in state, 
	 * the state change is logged and true is returned.
	 *****************************************************************/
	public void desiredStateChange(CommunicationObject state){
		
		if( getCurrentState(state.getIdentifier()) != state.getValue()){
			currentState.get( state.getIdentifier() ).add(state);	
			com.txqueue.add(state);
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void droneStateChange(CommunicationObject state){
		
		if( currentState.get( state.getIdentifier() ).getFirst().getValue() != state.getValue()){
			currentState.get( state.getIdentifier() ).add(state);
			saveState(currentState.get(state.getIdentifier()));
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void saveState(LinkedList<CommunicationObject> theList){

		if(theList.size() < WRITE_LIST_SIZE)
			return;
		
		for( int i = WRITE_LIST_SIZE -1; i > TRIM_LIST_SIZE; i--)
		{
			CommunicationObject temp = theList.remove(i);
			temp.getString();
		}
			
		// TODO save state list to history file and posibly extract video.
	}
	
	/******************************************************************
	 * These methods are used to get current information about the
	 * state of the drone.
	 *****************************************************************/
	public float getCurrentState(int identifier){ 
		update[identifier] = false;
		return currentState.get(identifier).getFirst().getValue();
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	public float getAirspeed(){ return getCurrentState(ACTUAL_AIRSPEED); }
	
}

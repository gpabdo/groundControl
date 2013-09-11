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
	private boolean log;

	private static final int LENGTH 					= 20;
	private static final int TRIM_LIST_SIZE 			= 100;
	private static final int WRITE_LIST_SIZE 			= 1000;

	public static final int DESIRED_THROTTLE 			= 0;
	public static final int ACTUAL_THROTTLE 			= 1;
	public static final int DESIRED_PITCH 				= 2;
	public static final int ACTUAL_PITCH				= 3;
	public static final int DESIRED_ROLL				= 4;
	public static final int ACTUAL_ROLL					= 5;
	public static final int DESIRED_YAW					= 6;
	public static final int ACTUAL_YAW					= 7;
	public static final int DESIRED_AIRSPEED			= 8;
	public static final int ACTUAL_AIRSPEED				= 9;
	public static final int DESIRED_LANDSPEED			= 10;
	public static final int ACTUAL_LANDSPEED			= 11;
	public static final int DESIRED_BAROMETRIC_ALTITUDE	= 12;
	public static final int ACTUAL_BAROMETRIC_ALTITUDE	= 13;
	public static final int ACK 						= 255;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public State(){
		update = new boolean[LENGTH];
		setLog( false );
		currentState = new LinkedList<LinkedList<CommunicationObject>>();
		
		for( int i = 0; i < LENGTH; i++){
			currentState.add(new LinkedList<CommunicationObject>());
			currentState.get(i).add(new CommunicationObject(i, 0, "GROUND"));
			update[i] = false;
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
	public void StateChange(CommunicationObject state){
		int id = state.getIdentifier();
		if( getCurrentState(id) != state.getValue()){
			currentState.get(id).add(state);	
			update[id] = true;
			
			if(state.getSource() == "PILOT"){
				com.txqueue.add(state);
				update[id] = false;
			}
			saveState(currentState.get(id));
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
			
			if(log){
				// TODO write to log files here.
			}	
		}	
	}
	
	/******************************************************************
	 * These methods are used to get current information about the
	 * state of the drone.
	 *****************************************************************/
	public float getCurrentState(int identifier){ 
		update[identifier] = false;
		return currentState.get(identifier).getFirst().getValue();
	}

	public void setLog( boolean setLog){ log = setLog; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public float getAirspeed(){ return getCurrentState(ACTUAL_AIRSPEED); }
	
}

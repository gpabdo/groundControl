package groundcontrol.state;

import java.util.LinkedList;

import groundcontrol.communication.*;

/******************************************************************
 * The state class is used to keep track for the current state of 
 * all aspects of the drone. When the state of a variable is 
 * changed, that change is pushed out to the txqueue. 
 *****************************************************************/
public class State implements Runnable {
	private LinkedList<LinkedList<CommunicationObject>> currentState;
	private LinkedList<CommunicationObject> stateQueue;
	private Communication drone;
	private boolean[] update;
	private boolean log;
	private boolean run;

	private static final int LENGTH 					= 20;
	private static final int TRIM_LIST_SIZE 			= 100;
	private static final int WRITE_LIST_SIZE 			= 1000;

	public static final int DRONE_IP_ADDRESS			= 0;
	public static final int DRONE_PORT					= 1;
	public static final int CONNECTED					= 2;
	public static final int DESIRED_THROTTLE 			= 10;
	public static final int ACTUAL_THROTTLE 			= 11;
	public static final int DESIRED_PITCH 				= 12;
	public static final int ACTUAL_PITCH				= 13;
	public static final int DESIRED_ROLL				= 14;
	public static final int ACTUAL_ROLL					= 15;
	public static final int DESIRED_YAW					= 16;
	public static final int ACTUAL_YAW					= 17;
	public static final int DESIRED_AIRSPEED			= 18;
	public static final int ACTUAL_AIRSPEED				= 19;
	public static final int DESIRED_LANDSPEED			= 20;
	public static final int ACTUAL_LANDSPEED			= 21;
	public static final int DESIRED_BAROMETRIC_ALTITUDE	= 22;
	public static final int ACTUAL_BAROMETRIC_ALTITUDE	= 23;
	public static final int ACK 						= 255;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public State(){
		drone = new Communication();
		update = new boolean[LENGTH];
		currentState = new LinkedList<LinkedList<CommunicationObject>>();
		stateQueue = new LinkedList<CommunicationObject>();
		setLog( false );
		run = true;
		
		for( int i = 0; i < LENGTH; i++){
			currentState.add(new LinkedList<CommunicationObject>());
			currentState.get(i).add(new CommunicationObject(i, 0, "PILOT"));
			update[i] = false;
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	@Override
	public void run() {
		
		while(run){
			if(stateQueue.size() != 0)
				stateChange(stateQueue.pop());
		}
		
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void submitStateChange(CommunicationObject newState){
		stateQueue.add(newState);
	}
	
	/******************************************************************
	 * Connect to the drone.
	 *****************************************************************/
	private void connect(){
		Thread comThread = new Thread(drone);	// Create com thread.
		comThread.start();						// Start com thread.
	}
	
	/******************************************************************
	 * This method takes a communication object and checks it against
	 * the current state of the drone. If this is a change in state, 
	 * the state change is logged and true is returned.
	 *****************************************************************/
	private void stateChange(CommunicationObject state){
		int id = state.getIdentifier();
		if( getCurrentState(id) != state.getValue()){
			currentState.get(id).add(state);	
			update[id] = true;
			
			if(state.getSource() == "PILOT"){
				drone.txqueue.add(state);
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

	/******************************************************************
	 * 
	 *****************************************************************/
	public void setLog( boolean setLog){ log = setLog; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public float getAirspeed(){ return getCurrentState(ACTUAL_AIRSPEED); }

	
}

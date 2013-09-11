package groundcontrol.state;

import java.util.LinkedList;
import groundcontrol.communication.*;

/******************************************************************
 * The state class is used to keep track for the current state of 
 * all aspects of the drone. When the state of a variable is 
 * changed, that change is pushed out to the txqueue. 
 *****************************************************************/
public class State {
	int[][] theState;
	LinkedList<CommunicationObject> stateHx;	// queue to keep hx.
	Communication com;

	/******************************************************************
	 * 
	 *****************************************************************/
	public State(){
		theState = new int[30][30];
		stateHx = new LinkedList<CommunicationObject>();
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
		
		if( theState[state.getCommand()][0] != state.getValue()){
			theState[state.getCommand()][0] = state.getValue();
			com.txqueue.add(state);
			saveState(state);
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void droneStateChange(CommunicationObject state){
		
		if( theState[state.getCommand()][0] != state.getValue()){
			theState[state.getCommand()][0] = state.getValue();
			theState[state.getCommand()][1] = 1;
			saveState(state);
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void saveState(CommunicationObject state){
		stateHx.add(state);
		
		//if( stateHx.size() >= 1000)
		
		// TODO save state list to history file and posibly extract video.
	}
	
	/******************************************************************
	 * These methods are used to get current information about the
	 * state of the drone.
	 *****************************************************************/
	public int getState(int command){ 
		theState[command][1] = 0; 		// Clear state changed.
		return theState[command][0]; 	// Return the state.
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	public int[][] getState(){ 
		return theState; 				// Return entire state.
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int[][] getChanged(){
		LinkedList<int[]> updatedStates = new LinkedList<int[]>();
		for(int i = 0; i < theState.length; i++)
		{
			if(theState[i][1] == 1)
			{
				int[] value = new int[2];
				value[0] = i;
				value[1] = theState[i][0];
				updatedStates.add(value);
				theState[i][1] = 0;			// Clear state changed. 
			}
		}
		
		int[][] returnArray = new int[updatedStates.size()][updatedStates.size()];
		for( int i = 0; i < returnArray.length; i++)
		{
			returnArray[i][0] = updatedStates.pop()[0];
			returnArray[i][1] = updatedStates.pop()[1];
		}
		return returnArray;	
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int getAirspeed(){
		theState[10][1] = 0;			// Clear state changed.
		return theState[10][0]; 		// Return the state.
	}
	
}

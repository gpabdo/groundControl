package groundcontrol.state;

import java.util.LinkedList;
import groundcontrol.communication.*;

/******************************************************************
 * The state class is used to keep track for the current state of 
 * all aspects of the drone. When the state of a variable is 
 * changed, that change is pushed out to the txqueue. 
 *****************************************************************/
public class State {
	int[] theState;
	LinkedList<CommunicationObject> stateHx;	// queue to keep hx.

	/******************************************************************
	 * 
	 *****************************************************************/
	public State(Communication comObject){
		theState = new int[30];
		stateHx = new LinkedList<CommunicationObject>();
	}
	
	/******************************************************************
	 * This method takes a communication object and checks it against
	 * the current state of the drone. If this is a change in state, 
	 * the state change is logged and true is returned.
	 *****************************************************************/
	public boolean stateChange(CommunicationObject state){
		
		if( theState[state.getCommand()] != state.getValue()){
			theState[state.getCommand()] = state.getValue();
			saveState(state);
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int getState(int command){ return theState[command]; }

	public int[] getState(){ return theState; }
	
	public int getAltitue(){ return theState[0]; }
	
	
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void saveState(CommunicationObject state){
		stateHx.add(state);
		
		//if( stateHx.size() >= 1000)
		// TODO save state list to history file
	}
	
	
}

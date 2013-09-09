package groundcontrol.state;

import java.util.LinkedList;

import groundcontrol.communication.*;

/******************************************************************
 * 
 *****************************************************************/
public class State {
	int[] theState;
	LinkedList<CommunicationObject> stateHx;
	LinkedList<CommunicationObject> txqueue;

	/******************************************************************
	 * 
	 *****************************************************************/
	public State(LinkedList<CommunicationObject> theTXqueue){
		theState = new int[30];
		stateHx = new LinkedList<CommunicationObject>();
		txqueue = theTXqueue;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setState(CommunicationObject state){
		
		if( theState[state.getCommand()] != state.getValue()){
			theState[state.getCommand()] = state.getValue();
			txqueue.add(state);
			saveState(state);
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int getState(int command){
		return theState[command];
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int[] getState(){
		return theState;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void saveState(CommunicationObject state){
		stateHx.add(state);
		
		//if( stateHx.size() >= 1000)
	}
	
	
}

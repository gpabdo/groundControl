package groundcontrol.communication;

import java.util.LinkedList;
import groundcontrol.state.*;

/******************************************************************
 * 
 *****************************************************************/
public class Communication implements Runnable {
	public LinkedList<CommunicationObject> txqueue;
	public LinkedList<CommunicationObject> rxqueue;
	private boolean run;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public Communication(){
		txqueue = new LinkedList<CommunicationObject>();
		rxqueue = new LinkedList<CommunicationObject>();
		run = true;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	@Override
	public void run() {
		
		while(run){
			if(!txqueue.isEmpty())
				transmit();
			
			receive();
			
		} // close while
	}
	
	/******************************************************************
	 * Pulls a communication object off the tx queue and checks to see 
	 * if it will change the current state. If so, it will be 
	 * Transmitted.
	 *****************************************************************/
	private void transmit(){
		
	}

	/******************************************************************
	 * When a packet is received, send the object to the state object 
	 * to be placed in the drones current state.
	 *****************************************************************/
	private void receive(){

	}
}

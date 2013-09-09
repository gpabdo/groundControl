package groundcontrol.communication;

import java.util.LinkedList;

/******************************************************************
 * 
 *****************************************************************/
public class Communication implements Runnable {
	LinkedList<CommunicationObject> txqueue;
	LinkedList<CommunicationObject> rxqueue;
	boolean run;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public Communication(LinkedList<CommunicationObject> txqueue2, 
			LinkedList<CommunicationObject> rxqueue2){
		txqueue = txqueue2;
		rxqueue = rxqueue2;
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
			
				
		} // close while
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	private void transmit(){
		
	}

	/******************************************************************
	 * 
	 *****************************************************************/
	private void receive(){
		
	}
}

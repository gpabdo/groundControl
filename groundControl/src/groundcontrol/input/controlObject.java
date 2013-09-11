package groundcontrol.input;

import groundcontrol.communication.*;
/******************************************************************
 * 
 *****************************************************************/
public class controlObject {
	int type;		
	int value;		// The Value.
	int minValue;	// Smallest number value can hold.
	int maxValue;	// Largest number value can hold.
	int trim;		// Adjust new input by trim.
	int reverse; 	// reverse = -1, normal = 1;
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public controlObject(int theType){
		setType(theType);
		setReverse(false);
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setType(int theType){
		type = theType;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setValue(int theValue){
		if((theValue * reverse + trim) < maxValue &&
				(theValue * reverse + trim) > minValue){
			value = theValue;
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void adjustValue(int theValue){
		if((theValue * reverse + value + trim) < maxValue &&
				(theValue * reverse + value + trim) > minValue){
			value += theValue;
		}
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setTrim( int theTrim ){
		if((theTrim + value) < maxValue && (theTrim + value) > minValue)
			trim = theTrim;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void adjustTrim(int theTrim){
		if((theTrim + value + trim) < maxValue &&
				(theTrim + value + trim) > minValue)
			value += theTrim;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setMin(int theMin){ minValue = theMin; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setMax(int theMax){ maxValue = theMax; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public void setReverse( boolean rev){
		if(rev)
			reverse = -1;
		else
			reverse = 1;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public CommunicationObject getCommunicationObject(){
		CommunicationObject temp = new CommunicationObject(
				type, value, "PILOT");
		return temp;
	}
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int getValue(){ return value; }
	
	/******************************************************************
	 * 
	 *****************************************************************/
	public int getType() { return type; }
	

}

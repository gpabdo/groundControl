package groundcontrol;

import groundcontrol.dashboard.*;
import groundcontrol.communication.*;

public class GroundControl {
	Dashboard dash;
	Communication com;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public GroundControl(){
		dash = new Dashboard();
		com = new Communication();
	}
}

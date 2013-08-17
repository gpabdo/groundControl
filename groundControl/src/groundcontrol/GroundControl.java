package groundcontrol;

import groundcontrol.dashboard.*;
import groundcontrol.communication.*;
import groundcontrol.input.*;

public class GroundControl {
	Dashboard dash;
	Communication com;
	Input human;
	
	/******************************************************************
	 * Default constructor sets up the required objects.
	 *****************************************************************/
	public GroundControl(){
		dash = new Dashboard();
		com = new Communication();
		human = new Input();
	}
}

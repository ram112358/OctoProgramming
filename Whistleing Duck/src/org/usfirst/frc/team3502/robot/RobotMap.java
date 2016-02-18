package org.usfirst.frc.team3502.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
	//Motor Ports
	public static int
		topDuckOnePort = 1,
		topDuckTwoPort = 2,
		bottomDuckOnePort = 3,
		bottomDuckTwoPort = 4,
    	intakePort = 5,
		rightOnePort = 6,
		rightTwoPort = 7,
		leftOnePort = 8,
		leftTwoPort = 9;
    
    //Joystick Ports
    public static int
    	duckJoyPort = 1,
    	rightJoyPort = 2,
    	leftJoyPort = 3;
    
    //Buttons
    public static int
    	bothDuckButtonPort = 1,
    	bottomDuckButtonPort = 3,
    	topDuckButtonPort = 5,
    	intakeInButtonPort = 4,
    	intakeOutButtonPort = 6,
    	bottomFullUpButtonPort = 10,
    	bottomTimedFullUpButtonPort = 11,
    	climbingButton = 7,
    	rightIntakeInButtonPort = 2,
    	rightIntakeOutButtonPort = 3,
    	clearEncButtonPort = 7;
}

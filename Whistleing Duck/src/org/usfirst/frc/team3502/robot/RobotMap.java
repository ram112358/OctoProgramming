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
    	topDuckPort = 1,
    	bottomDuckPort = 2,
    	intakePort = 3,
		rightOnePort = 4,
		rightTwoPort = 5,
		leftOnePort = 6,
		leftTwoPort = 7;
    
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
    	intakeOutButtonPort = 6;
}

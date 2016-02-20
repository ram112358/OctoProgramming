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
		topDuckAuxPort = 2,
		bottomDuckPort = 3,
		bottomDuckAuxPort = 4,
    	intakePort = 5,
		rightPort = 6,
		rightAuxPort = 7,
		leftPort = 8,
		leftAuxPort = 9;
    
    //Joystick Ports
    public static int
    	duckJoyPort = 1,
    	rightJoyPort = 2,
    	leftJoyPort = 3;
    
    //Buttons
    public static int
    	bothDuckButton = 1,
    	bottomDuckButton = 3,
    	topDuckButton = 5,
    	intakeInButton = 4,
    	intakeOutButton = 6,
    	bottomFullUpButton = 10,
    	bottomTimedFullUpButton = 11,
    	climbingButton = 7,
    	rightIntakeInButton = 2,
    	rightIntakeOutButton = 3,
    	clearEncButton = 2,
    	regDriveButton = 3,
    	sineDriveButton = 2;
    
    //Pneumatics
    public static int
    	PCMPort = 33,
    	PTOForward = 0,
    	PTOReverse = 1,
    	BrakeForward = 2,
    	BrakeReverse = 3;
}

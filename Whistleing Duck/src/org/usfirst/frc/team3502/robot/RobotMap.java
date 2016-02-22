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
    	opJoyPort = 1,
    	rightJoyPort = 2,
    	leftJoyPort = 3;
    
    //Op Buttons
    public static int
		dontDuckButton = 2,
    	bothDuckButton = 1,
    	topDuckButton = 5,
    	bottomDuckButton = 3,
    	topDuckThrottleButton = 6,
    	bottomDuckThrottleButton = 4,
    	
    	bottomFullUpButton = 10,
    	bottomTimedFullUpButton = 11,
    	clearEncButton = 12,
		manualBrakeOnButton = 9,
		manualBrakeOffButton = 10;
    
    //Right Buttons
    public static int
    	climbingButton = 7,
    	rightIntakeInButton = 2,
    	rightIntakeOutButton = 3;
    
    //Left Buttons
    public static int
    	regDriveButton = 3,
    	sineDriveButton = 2,
    	driveShiftButton = 4,
    	climbShiftButton = 5;
    
    //Pneumatics
    public static int
    	PCMPort = 33,
    	PTOForward = 2,
    	PTOReverse = 3,
    	BrakeForward = 0,
    	BrakeReverse = 1;
}

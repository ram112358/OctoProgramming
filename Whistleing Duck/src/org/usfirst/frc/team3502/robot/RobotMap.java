package org.usfirst.frc.team3502.robot;

import edu.wpi.first.wpilibj.AnalogGyro;

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
    	
    	bottomFullUpButton = 7,
    	bottomTimedFullUpButton = 11,
    	clearEncButton = 12,
		manualBrakeOnButton = 9,
		manualBrakeOffButton = 10;
    
    //Right Buttons
    public static int
    	climbingButton = 8,
    	rightIntakeInButton = 2,
    	rightIntakeOutButton = 3,
    	gyroDriveStraightButton = 1,
    	driveShiftButton = 4,
    	climbShiftButton = 5;
    
    //Left Buttons
    public static int
    	regDriveDuckEndButton = 3,
    	regDriveAttackEndButton = 2,
    	sineDriveDuckEndButton = 5,
    	sineDriveAttackEndButton = 4,
    	turn180Button = 6,
    	turn360Button = 7,
    	resetGyroButton = 8;
    
    //Pneumatics
    public static int
    	PCMPort = 33,
    	PTOForward = 0,
    	PTOReverse = 1,
    	BrakeForward = 3,
    	BrakeReverse = 2;
    
    // Misc
    public static int
    	HookerReleaseServo = 2;
    
    // Gyro
	public static final AnalogGyro gyro = new AnalogGyro(1);
	
	//Brownout Values
	public static double
		brownLimit = 8.0,
		brownScale = 0.5;
	
	// Misc Values
	public static int
		squeezeFactor = 0;
}

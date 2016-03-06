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
}

package org.usfirst.frc.team3502.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static int
    	topDuckPort = 0,
    	bottomDuckPort = 1,
    	intakePort = 2;
    
    public static int
    	bothDuckJoyPort = 0,
    	intakeJoyPort = 1;
    
    public static int
    	bothDuckButtonPort = 1,
    	bottomDuckButtonPort = 2,
    	topDuckButtonPort = 3;
}

package org.usfirst.frc.team3502.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final CANTalon
		leftFront = new CANTalon(0),//3
		leftCenter = new CANTalon(4),
		leftRear = new CANTalon(1),//2
		rightRear = new CANTalon(2),//1
		rightCenter = new CANTalon(5),
		rightFront = new CANTalon(3);//0
	public static final DoubleSolenoid
		driveMode = new DoubleSolenoid(33,0,1);		
	public static final Encoder
		leftEncoder = new Encoder(5,4),
		rightEncoder = new Encoder(2,3);
	
	public static final CANTalon
		liftMotor = new CANTalon(6),
		leftCrabMotor = new CANTalon(7),
		rightCrabMotor = new CANTalon(8);
	public static final Encoder
		liftEncoder = new Encoder(6,7);
	public static final DigitalInput
		toteStopper = new DigitalInput(1),
		liftBottomStopper = new DigitalInput(8),
		liftTopStopper = new DigitalInput(9);
	public static final DoubleSolenoid
		brake = new DoubleSolenoid(33,4,5),
		crab = new DoubleSolenoid(33,2,3);
	
	private static CANTalon[] motors = {leftFront, leftCenter, leftRear, rightRear, rightCenter, rightFront, liftMotor};
	public static double getVoltage(int index){
		return motors[index].getOutputVoltage();
	}
	public static double getCurrent(int index){
		return motors[index].getOutputCurrent();
	}
	
	
}

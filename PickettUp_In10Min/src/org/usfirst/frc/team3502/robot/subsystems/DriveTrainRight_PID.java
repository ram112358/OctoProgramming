package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.manualDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainRight_PID extends PIDSubsystem {
	
	private static final CANTalon 
		rightRear = RobotMap.rightRear,
		rightFront = RobotMap.rightFront;
	private static final Encoder rightEncoder = RobotMap.rightEncoder;
	private static final double 
		rawPer50cm = 1204, //1204 raw value when traveled 6 inches 
		voltageRampRate = -1.1;
	private static double 
		kP = 1, 
		kI = 0, 
		kD = 5,//5
		targetDistance;
	public static final double tolerance = .15;
		

    // Initialize your subsystem here
    public DriveTrainRight_PID() {
    	super("DriveTrainRight_PID", kP, kI, kD);
    	setAbsoluteTolerance(tolerance);
    	getPIDController().setContinuous(false);
    	//getPIDController().setInputRange(MIN, MAX);
    	targetDistance = 0;
    	setSetpoint(targetDistance);
    	LiveWindow.addActuator("RightDriveTrain_PID", "Right PID Controller", getPIDController());
    	//enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new manualDrive());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//return rightEncoder.getDistance();
    	return getDistance();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
    	output /= 2;
    	//SmartDashboard.putNumber("OUTPUT", output);
    	
    	setRightMotors(-output);
    }
    
    public void setRightMotors(double straightValue){
    	rightFront.set(straightValue);
    	rightRear.set(straightValue);
    }
    
    public double getDistance(){ //given in half feet
    	return (rightEncoder.getRaw() / rawPer50cm);
    }
    
    public void setVoltageRamp(){
    	rightFront.setVoltageRampRate(voltageRampRate);
    	rightRear.setVoltageRampRate(voltageRampRate);
    }
    
    public int getRaw(){
    	return rightEncoder.getRaw(); //The raw value is the actual count unscaled by the 1x, 2x, or 4x scale factor
    }
    
    public double get(){ //current rate of encoder
		return rightEncoder.get(); //Units are distance per second as scaled by the value from setDistancePerPulse()
    }
    
    public void resetEncoder(){
    	rightEncoder.reset();
    }
}

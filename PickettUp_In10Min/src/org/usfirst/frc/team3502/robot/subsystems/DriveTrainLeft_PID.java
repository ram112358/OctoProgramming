package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.manualDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainLeft_PID extends PIDSubsystem {
	
	private static final CANTalon 
		leftFront = RobotMap.leftFront,
		leftRear = RobotMap.leftRear;
	private static final Encoder leftEncoder = RobotMap.leftEncoder;
	private static final double 
		rawPerHalfFt = 1182, //1182 raw value when traveled 6 inches 
		voltageRampRate = -1.1;
	public static final double tolerance = .15;
	private static double 
		kP = 1, 
		kI = 0, 
		kD = 3,//5
		targetDistance;

    // Initialize your subsystem here
    public DriveTrainLeft_PID() {
    	super("DriveTrainLeft_PID", kP, kI, kD);
    	setAbsoluteTolerance(tolerance);
    	getPIDController().setContinuous(false);
    	//getPIDController().setInputRange(MIN, MAX);
    	targetDistance = 0;
    	setSetpoint(targetDistance);
    	LiveWindow.addActuator("LeftDriveTrain_PID", "Left PID Controller", getPIDController());
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
    	//return leftEncoder.getDistance();
    	return getDistance();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
    	output /= 2;
    	//SmartDashboard.putNumber("OUTPUT", output);
    	
    	setLeftMotors(-output);
    }
    
    public void setLeftMotors(double outputValue){
    	leftFront.set(-outputValue);
    	leftRear.set(-outputValue);
    }
    
    public double getDistance(){ //given in half feet
    	return (leftEncoder.getRaw() / rawPerHalfFt);
    }
    
    public void setVoltageRamp(){
    	leftFront.setVoltageRampRate(voltageRampRate);
    	leftRear.setVoltageRampRate(voltageRampRate);
    }
    
    public int getRaw(){
    	return leftEncoder.getRaw(); //The raw value is the actual count unscaled by the 1x, 2x, or 4x scale factor
    }
    
    public double get(){ //current rate of encoder
		return leftEncoder.get(); //Units are distance per second as scaled by the value from setDistancePerPulse()
    }
    
    public void resetEncoder(){
    	leftEncoder.reset();
    }
}

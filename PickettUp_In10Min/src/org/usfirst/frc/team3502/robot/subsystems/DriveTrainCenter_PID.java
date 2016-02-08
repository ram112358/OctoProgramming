package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.manualDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrainCenter_PID extends PIDSubsystem {
	
	private static final CANTalon 
		leftCenter = RobotMap.leftCenter,
		rightCenter = RobotMap.rightCenter;
	private static final double 
		distancePerPulse = .01, 
		voltageRampRate = -1.1,
		tolerance = .05;
	private static double 
		kP = .25, 
		kI = 2, 
		kD = 0,
		targetDistance;

    // Initialize your subsystem here
    public DriveTrainCenter_PID() {
    	super("DriveTrainCenter_PID", kP, kI, kD);
    	setAbsoluteTolerance(tolerance);
    	getPIDController().setContinuous(false);
    	//getPIDController().setInputRange(MIN, MAX);
    	targetDistance = 0;
    	setSetpoint(targetDistance);
    	LiveWindow.addActuator("CenterDriveTrain_PID", "Center PID Controller", getPIDController());
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
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    
    public void setCenterMotors(double outputValue){
    	rightCenter.set(-outputValue);
    	leftCenter.set(-outputValue);
    }
}

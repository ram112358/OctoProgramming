package org.usfirst.frc.team3502.robot.commands;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class manualDrive extends Command {
	
	private double rightOutputValue, rightDistance, rightEncoderValue,
					leftOutputValue, leftDistance, leftEncoderValue,
					centerOutputValue;
	private boolean switchDriveButton, switchDrivePrev, driveType, defaultDriveButton, defaultDrivePrev, defaultDrive;

    public manualDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveLeft);
        requires(Robot.driveCenter);
        requires(Robot.driveRight);
        requires(Robot.dPneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveLeft.disable();
    	Robot.driveCenter.disable();
    	Robot.driveRight.disable();
    	defaultDrive = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	drive();
    	switchDrive();
    	encoders();    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void switchDrive(){
    	switchDriveButton = Robot.oi.getRightTrigger();
    	if ((switchDriveButton != switchDrivePrev) && (switchDriveButton)){
    		driveType = !driveType;
    		Robot.dPneumatics.switchDriveMode(driveType);
    	}
    	switchDrivePrev = switchDriveButton;
    }
    
    public void switchDriveDirection(){
    	if(defaultDrive){
    		rightOutputValue = Robot.oi.getRightY();
    		centerOutputValue = Robot.oi.getStraiff();
    		leftOutputValue = Robot.oi.getLeftY();    		
    	}
    	
    	else{
    		rightOutputValue = -Robot.oi.getLeftY();
    		centerOutputValue = -Robot.oi.getStraiff();
    		leftOutputValue = -Robot.oi.getRightY();
    	}
    	SmartDashboard.putBoolean("Default Drive", defaultDrive);
    }
    
    public void drive(){
    	switchDriveDirection();
    	
    	defaultDriveButton = Robot.oi.getDefaultDrive();
    	SmartDashboard.putBoolean("Default Drive Button", defaultDriveButton);
    	if ((defaultDriveButton != defaultDrivePrev) && (defaultDriveButton)){
    		defaultDrive = !defaultDrive;
    	}
    	defaultDrivePrev = defaultDriveButton;
    	
    	Robot.driveRight.setRightMotors(rightOutputValue);
    	Robot.driveLeft.setLeftMotors(leftOutputValue);    	
    	Robot.driveCenter.setCenterMotors(centerOutputValue);
    }
    
    public void encoders(){
    	leftEncoderValue = Robot.driveLeft.getPosition();
    	SmartDashboard.putNumber("Left Encoder", leftEncoderValue);
    	
    	rightEncoderValue = Robot.driveRight.getPosition();
    	SmartDashboard.putNumber("Right Encoder", rightEncoderValue);
    	
    	SmartDashboard.putNumber("Left Count", Robot.driveLeft.get());
    	SmartDashboard.putNumber("Right Count", Robot.driveRight.get());
    	
    
    	if(Robot.oi.getResetEncoder()){
    		Robot.driveLeft.resetEncoder();
    		Robot.driveRight.resetEncoder();
    	}
    }
}

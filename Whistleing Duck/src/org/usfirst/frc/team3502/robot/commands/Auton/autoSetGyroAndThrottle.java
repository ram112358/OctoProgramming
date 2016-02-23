package org.usfirst.frc.team3502.robot.commands.Auton;

import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autoSetGyroAndThrottle extends Command {
	
	private double setpoint, throttle, error, rightValue, leftValue;
	private double kP = 0.03; //.095 works when joystick is 0

    public autoSetGyroAndThrottle(double targetHeading, double desiredSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftDrive);
    	requires(Robot.rightDrive);
    	this.setpoint = targetHeading;
    	this.throttle = desiredSpeed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putNumber("Gyro", RobotMap.gyro.getAngle());

    	//CCW (right side forward) makes gyro change in negative direction
    	//CW (left side forward) makes gyro change in positive direction
    	
    	error = setpoint - RobotMap.gyro.getAngle();
    	leftValue = -throttle - error * kP;    	
    	rightValue = -throttle + error * kP;
    	Robot.leftDrive.setBrown(leftValue);
    	Robot.rightDrive.setBrown(rightValue);
    }

    protected boolean isFinished() {
    	//Ram: Have this return true when the encoder distance reaches a certain number (we want the robot to drive 10 feet)
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimbMode extends Command {
	
	private boolean done;
	private double leftValue, rightValue, battVolt, error;
	private double kP = 10.0;
	private double accelZSetpoint;
	
    public ClimbMode() {
    	requires(Robot.shifting);
    	requires(Robot.leftDrive);
    	requires(Robot.rightDrive);
    	requires(Robot.hooker);
    }

    protected void initialize() {
    	done = false;
    	
    	Robot.shifting.setClimbMode();
    	
    	Robot.rightDrive.setVoltageMode();
    	Robot.leftDrive.setVoltageMode();
    	
    	Robot.rightDrive.setVCRampRate(12);
    	Robot.leftDrive.setVCRampRate(12);
    	
    	accelZSetpoint = Robot.shifting.getAccelZ();
		Robot.hooker.setServoPosition(0);
    }

    protected void execute() {
    	SmartDashboard.putNumber("Accelerometer X axis", Robot.shifting.getAccelX());
    	SmartDashboard.putNumber("Accelerometer Y axis", Robot.shifting.getAccelY());
    	SmartDashboard.putNumber("Accelerometer Z axis", Robot.shifting.getAccelZ());
    	
    	battVolt = DriverStation.getInstance().getBatteryVoltage();
    	error = -Robot.shifting.getAccelX();
    	leftValue = - Math.abs(- Robot.oi.getRightY() * battVolt - error * kP);
    	rightValue = - Math.abs(- Robot.oi.getRightY() * battVolt + error * kP);
    	Robot.leftDrive.setBrown(leftValue);
    	Robot.rightDrive.setBrown(rightValue);
    	
    	if (Robot.shifting.getAccelZ() < 0.7) {
    		done = true;
    	}
    }

    protected boolean isFinished() {
    	// return done;
    	return false;
    }

    protected void end() {
    	Robot.leftDrive.setThrottleMode();
    	Robot.rightDrive.setThrottleMode();
    	Robot.leftDrive.setVCRampRate(24);
    	Robot.rightDrive.setVCRampRate(24);
    	Robot.leftDrive.setBrown(0);
    	Robot.rightDrive.setBrown(0);
    }

    protected void interrupted() {
    	Robot.leftDrive.setThrottleMode();
    	Robot.rightDrive.setThrottleMode();
    	Robot.leftDrive.setVCRampRate(24);
    	Robot.rightDrive.setVCRampRate(24);
    	Robot.leftDrive.setBrown(0);
    	Robot.rightDrive.setBrown(0);
    	
    }
}

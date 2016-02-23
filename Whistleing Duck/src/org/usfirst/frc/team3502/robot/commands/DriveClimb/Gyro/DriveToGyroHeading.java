package org.usfirst.frc.team3502.robot.commands.DriveClimb.Gyro;

import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToGyroHeading extends Command {
	
	double setpoint, error, rightValue, leftValue, battVolt;
	private double kP = 1.0; //retune for new robot

    public DriveToGyroHeading(double targetHeading) {
    	requires(Robot.leftDrive);
    	requires(Robot.rightDrive);
    	this.setpoint = targetHeading;
    	System.out.println("yellow");
    }

    protected void initialize() {
    	Robot.leftDrive.setVoltageMode();
    	Robot.rightDrive.setVoltageMode();
    }

    protected void execute() {
    	NetworkTable.getTable("Preferences").getNumber("kP", 0);
    	System.out.println("gyro target is" + setpoint);
    	//CCW (right side forward) makes gyro change in negative direction
    	//CW (left side forward) makes gyro change in positive direction
    	
    	error = setpoint - RobotMap.gyro.getAngle();
    	
    	battVolt = DriverStation.getInstance().getBatteryVoltage();
    	leftValue = Robot.oi.getRightY() * battVolt - error * kP;    	
    	rightValue = Robot.oi.getRightY() * battVolt + error * kP;
    	Robot.leftDrive.set(leftValue);
    	Robot.rightDrive.set(rightValue);
    }

    protected boolean isFinished() {
        return Robot.oi.getOpPOV() == 0;
    }

    protected void end() {
    	Robot.leftDrive.setThrottleMode();
    	Robot.rightDrive.setThrottleMode();
    	System.out.println("end");
    }

    protected void interrupted() {
    	Robot.leftDrive.setThrottleMode();
    	Robot.rightDrive.setThrottleMode();
    	System.out.println("interupt");
    	
    }
}

package org.usfirst.frc.team3502.robot.commands.DriveClimb;

import org.usfirst.frc.team3502.robot.Constants;
import org.usfirst.frc.team3502.robot.Robot;
import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToGyroHeading extends Command {
	
	private double setpoint, error, rightValue, leftValue, battVolt, throttle;
	private double kP, kD, currentAngle, prevAngle, speed; //retune for new robot
	private boolean autoMode;
	
    public DriveToGyroHeading(boolean autoMode, double throttle) {
    	requires(Robot.leftDrive);
    	requires(Robot.rightDrive);
    	//System.out.println("Gyro Drive Debug Mark");
    	this.autoMode = autoMode;
    	this.throttle = throttle;
    }

    protected void initialize() {
    	if (autoMode || Robot.oi.getGyroDriveStraightButton()) {
    		setpoint = RobotMap.gyro.getAngle();
    		kP = Constants.kStraightP;
    		kD = Constants.kStraightD;
    	}
    	else if (Robot.oi.getTurn180Button()) {
    		setpoint = RobotMap.gyro.getAngle() + 180;
    		kP = Constants.kTurnP;
    		kD = Constants.kTurnD;
    	}

    	Robot.leftDrive.setVoltageMode();
    	Robot.rightDrive.setVoltageMode();
    	
    	currentAngle = RobotMap.gyro.getAngle();
    	System.out.println("gyro target is" + setpoint);
    	
    }

    protected void execute() {
    	//Uncomment these 2 lines to enable PD tuning from the SmartDashboard
    	//kP = NetworkTable.getTable("Preferences").getNumber("kP", 0);
    	//kD = NetworkTable.getTable("Preferences").getNumber("kD", 0);
    	
    	//CCW (right side forward) makes gyro change in negative direction
    	//CW (left side forward) makes gyro change in positive direction
    	
    	prevAngle = currentAngle;
    	currentAngle = RobotMap.gyro.getAngle();
    	error = setpoint - RobotMap.gyro.getAngle();
    	speed = prevAngle - currentAngle;
    	battVolt = DriverStation.getInstance().getBatteryVoltage();
    	
    	if (!autoMode)
    		throttle = Robot.oi.getRightY();
    	
    	leftValue = throttle * battVolt - error * kP + speed * kD;    	
    	rightValue = throttle * battVolt + error * kP - speed * kD;
    	Robot.leftDrive.setBrown(leftValue);
    	Robot.rightDrive.setBrown(rightValue);
    }

    protected boolean isFinished() {
    	if (autoMode && throttle == 0.0)
    		return true;
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

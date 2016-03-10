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
	
	double setpoint, error, rightValue, leftValue, battVolt;
	private double kP; //retune for new robot

    public DriveToGyroHeading() {
    	requires(Robot.leftDrive);
    	requires(Robot.rightDrive);
    }

    protected void initialize() {
    	if (Robot.oi.getGyroDriveStraightButton()) {
    		setpoint = RobotMap.gyro.getAngle();
    		kP = Constants.kStraightP;
    	}
    	else if (Robot.oi.getTurn180Button()) {
    		setpoint = RobotMap.gyro.getAngle() + 180;
    		kP = Constants.kTurnP;
    	}
    	else if (Robot.oi.getTurn360Button()) {
    		setpoint = RobotMap.gyro.getAngle() + 360;
    		kP = Constants.kTurnP;
    	}

    	Robot.leftDrive.setVoltageMode();
    	Robot.rightDrive.setVoltageMode();
    	
    }

    protected void execute() {
    	kP = NetworkTable.getTable("Preferences").getNumber("kP", 0);
    	System.out.println("gyro target is" + setpoint);
    	//CCW (right side forward) makes gyro change in negative direction
    	//CW (left side forward) makes gyro change in positive direction
    	
    	error = setpoint - RobotMap.gyro.getAngle();
    	
    	battVolt = DriverStation.getInstance().getBatteryVoltage();
    	leftValue = Robot.oi.getRightY() * battVolt - error * kP;    	
    	rightValue = Robot.oi.getRightY() * battVolt + error * kP;
    	Robot.leftDrive.setBrown(leftValue);
    	Robot.rightDrive.setBrown(rightValue);
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

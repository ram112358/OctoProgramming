package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveRecord extends Subsystem {
	
	private static final CANTalon driveMotor = new CANTalon(RobotMap.driveMotorPort, 1, 1);
	private static final CANTalon driveMotorFollow = new CANTalon(RobotMap.driveMotorFollowPort, 1, 1);
	
	public final int encCPR = 360;
	
	private static final double
		p = 0.0,
		i = 0.0,
		d = 0.0,
		f = 0.0018,
		closeLoopRampRate = 0.0;
	private static final int
		izone = 0,
		profile = 0;
	
	public DriveRecord(){
		driveMotor.changeControlMode(TalonControlMode.PercentVbus);
		driveMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		driveMotor.configEncoderCodesPerRev(encCPR);
		driveMotor.reverseOutput(false);
		
		driveMotorFollow.changeControlMode(TalonControlMode.Follower);
		driveMotorFollow.set(RobotMap.driveMotorPort);
		
		driveMotor.setEncPosition(0);
		
		driveMotor.setPID(p, i, d, f, izone, closeLoopRampRate, profile);
	}
	
    public void initDefaultCommand() {
    }

    public void set(double outputValue){
    	driveMotor.set(outputValue);
    }
    
    public int getPosition(){
    	return driveMotor.getEncPosition();
    }
    
    public void setThrottleMode(){
    	driveMotor.changeControlMode(TalonControlMode.PercentVbus);
    	driveMotor.set(0.0);
    }
    
    public void setPositionMode(){
    	driveMotor.changeControlMode(TalonControlMode.Position);
    }
    
    public void setVelocityMode() {
    	driveMotor.changeControlMode(TalonControlMode.Speed);
    }
    
    public void clearEnc() {
    	driveMotor.setEncPosition(0);
    }
    
    public double getSetpoint() {
    	return driveMotor.getSetpoint();
    }
    
    public int getVelocity() {
    	return driveMotor.getEncVelocity();
    }
    
    public int getClosedLoopError() {
    	return driveMotor.getClosedLoopError();
    }
}
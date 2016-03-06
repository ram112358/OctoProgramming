package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveAttackEnd;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveDuckEnd;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RightDrive extends Subsystem {

	private static final CANTalon rightTalon = new CANTalon(RobotMap.rightPort);
	private static final CANTalon rightAuxTalon = new CANTalon(RobotMap.rightAuxPort);
	
	private double 
		batteryVoltage,
		rampRate = 24;
	
	public RightDrive() {
		rightTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightTalon.enableBrakeMode(true);
		
		rightTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon.configEncoderCodesPerRev(512);
		
		rightAuxTalon.changeControlMode(TalonControlMode.Follower);
		rightAuxTalon.set(RobotMap.rightPort);
		
		rightTalon.enable();
		rightAuxTalon.enable();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RegDriveAttackEnd());
    }

    public void set(double outputValue) {
    	rightTalon.set(outputValue);
    }

    public void setBrown(double outputValue) {
    	rightTalon.set(brownOutWatch(outputValue));
    }
    
    public void setSineScaling(double outputValue) {
    	if (outputValue > 0.0)
    		rightTalon.set((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5);
    	else if (outputValue < 0.0)
    		rightTalon.set(-((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5));
    	else
    		rightTalon.set(0.0);
    }
    
    public double brownOutWatch(double outputValue) {
    	/*batteryVoltage = DriverStation.getInstance().getBatteryVoltage();
    	outputValue *= batteryVoltage;
    	if (batteryVoltage < RobotMap.brownLimit) {
    		setVCRampRate(12.0);
    		setVoltageMode();
        	outputValue *= RobotMap.brownScale;
    	}
    	else {
    		setVCRampRate(24.0);
    		setThrottleMode();
    	}*/
    	return outputValue;
    }
    
    public int getEncPosition() {
    	return rightTalon.getEncPosition();
    }
    
    public void setEncPosition(int newPosition) {
    	rightTalon.setEncPosition(newPosition);
    }
    
    public void setPositionMode() {
    	rightTalon.changeControlMode(TalonControlMode.Position);
    	rightTalon.set(getEncPosition());
    }
    
    public void setThrottleMode() {
    	rightTalon.changeControlMode(TalonControlMode.PercentVbus);
    	rightTalon.set(0.0);
    
    }
    
    public void setVoltageMode() {
    	rightTalon.changeControlMode(TalonControlMode.Voltage);
    	rightTalon.set(0.0);
    }
    
    public int getClosedLoopError() {
    	return rightTalon.getClosedLoopError();
    }
    
    public void setVCRampRate(double rampRate) { //Volts per second
    	if (rampRate != this.rampRate) {
    		this.rampRate = rampRate;
    		rightTalon.setVoltageCompensationRampRate(this.rampRate);
    	}
    }
    
    public double getSetpoint() {
    	return rightTalon.getSetpoint();
    }
}
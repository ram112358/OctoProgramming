package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RightDrive extends Subsystem {

	private static final CANTalon rightTalon = new CANTalon(RobotMap.rightPort);
	private static final CANTalon rightAuxTalon = new CANTalon(RobotMap.rightAuxPort);
	
	public RightDrive(){
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
    }
    
    public void set(double outputValue) {
    	rightTalon.set(outputValue);
    }
    
    public void setSineScaling(double outputValue){
    	if (outputValue > 0.0)
    		rightTalon.set((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5);
    	else if (outputValue < 0.0)
    		rightTalon.set(-((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5));
    	else
    		rightTalon.set(0.0);
    }
    
    public int getEncPosition(){
    	return rightTalon.getEncPosition();
    }
    
    public void setEncPosition(int newPosition){
    	rightTalon.setEncPosition(newPosition);
    }
    
    public void setPositionMode(){
    	rightTalon.changeControlMode(TalonControlMode.Position);
    	rightTalon.set(getEncPosition());
    }
    
    public void setThrottleMode(){
    	rightTalon.changeControlMode(TalonControlMode.PercentVbus);
    	rightTalon.set(0.0);
    
    }public void setVoltageMode(){
    	rightTalon.changeControlMode(TalonControlMode.Voltage);
    	rightTalon.set(0.0);
    }
    
    public int getClosedLoopError(){
    	return rightTalon.getClosedLoopError();
    }
    
    
}
package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveAttackEnd;
import org.usfirst.frc.team3502.robot.commands.DriveClimb.RegDriveDuckEnd;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LeftDrive extends Subsystem {

	private static final CANTalon leftTalon = new CANTalon(RobotMap.leftPort);
	private static final CANTalon leftAuxTalon = new CANTalon(RobotMap.leftAuxPort);
	
	public LeftDrive(){
		leftTalon.changeControlMode(TalonControlMode.PercentVbus);
		leftTalon.enableBrakeMode(true);
		
		leftTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon.configEncoderCodesPerRev(512);
		
		leftAuxTalon.changeControlMode(TalonControlMode.Follower);
		leftAuxTalon.set(RobotMap.leftPort);
		
		leftTalon.enable();
		leftAuxTalon.enable();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RegDriveDuckEnd());
    }
    
    public void set(double outputValue) {
    	leftTalon.set(- outputValue);
    }
    
    public void setSineScaling(double outputValue){
    	if (outputValue < 0.0)
    		leftTalon.set((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5);
    	else if (outputValue > 0.0)
    		leftTalon.set(- ((Math.sin((outputValue * Math.PI) - (Math.PI / 2)) / 2) + .5));
    	else
    		leftTalon.set(0.0);
    }
    
    public int getEncPosition(){
    	return leftTalon.getEncPosition();
    }
    
    public void setEncPosition(int newPosition){
    	leftTalon.setEncPosition(newPosition);
    }
    
    public void setPositionMode(){
    	leftTalon.changeControlMode(TalonControlMode.Position);
    	leftTalon.set(getEncPosition());
    }
    
    public void setThrottleMode(){
    	leftTalon.changeControlMode(TalonControlMode.PercentVbus);
    	leftTalon.set(0.0);
    
    }public void setVoltageMode(){
    	leftTalon.changeControlMode(TalonControlMode.Voltage);
    	leftTalon.set(0.0);
    }
    
    public int getClosedLoopError(){
    	return leftTalon.getClosedLoopError();
    }
    
    public void setVCRampRate(double rampRate){ //Volts per second
    	leftTalon.setVoltageCompensationRampRate(rampRate);
    }
}
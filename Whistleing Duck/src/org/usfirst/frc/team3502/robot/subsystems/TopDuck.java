package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TopDuck extends Subsystem {
	
	public double
		setpoint = 0.0;
	
	private final double
		p = 0.43,
		i = 0.0,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	
	private final int
		izone = 0;

	private static final CANTalon topTalon = new CANTalon(RobotMap.topDuckOnePort);
	private static final CANTalon topAuxTalon = new CANTalon(RobotMap.topDuckTwoPort);
	
	public TopDuck(){
		topTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		topTalon.enableLimitSwitch(false, false);
		topTalon.changeControlMode(TalonControlMode.PercentVbus);
		topTalon.enableBrakeMode(true);
    	topTalon.setPID(p, i, d, f, izone, closeLoopRampRate, 0);
    	
    	topAuxTalon.changeControlMode(TalonControlMode.Follower);
    	topAuxTalon.set(RobotMap.topDuckOnePort);
	}
	
    public void initDefaultCommand() {
    	
    }

    public void set(double outputValue){
    	topTalon.set(outputValue);
    }

    public void setSlow(double outputValue){
    	topTalon.set(outputValue * 0.4);
    }

    public void setpointDrive(double joystickValue){
    	setpoint += joystickValue/4096*100;
    	topTalon.set(setpoint);
    }

    public void setSetpoint(double setpoint){
    	this.setpoint = setpoint;
    }
    
    public double getSetpoint(){
    	return setpoint;
    }

    public double getSpeed(){
    	return topTalon.getSpeed();
    }
    
    public int getEncPosition(){
    	return topTalon.getEncPosition();
    }
    
    public void setEncPosition(int pos){
    	topTalon.setEncPosition(pos);
    }
    
    public void setPositionMode(){
    	setSetpoint(0.0);
    	topTalon.changeControlMode(TalonControlMode.Position);
    }
    
    public void setThrottleMode(){
    	topTalon.changeControlMode(TalonControlMode.PercentVbus);
    	topTalon.set(0);
    }
    
    public int getClosedLoopError(){
    	return topTalon.getClosedLoopError();
    }
}
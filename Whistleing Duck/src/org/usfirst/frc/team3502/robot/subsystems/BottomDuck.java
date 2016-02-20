package org.usfirst.frc.team3502.robot.subsystems;

import org.usfirst.frc.team3502.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BottomDuck extends Subsystem {
	
	public double
		JoySet = 0.0;
	
	private final double
		p = 0.09,
		i = 0.0002,
		d = 0.0,
		f = 0.0,
		closeLoopRampRate = 0.0;
	
	private final int
		izone = 0;

	private static final CANTalon bottomTalon = new CANTalon(RobotMap.bottomDuckPort);
	private static final CANTalon bottomAuxTalon = new CANTalon(RobotMap.bottomDuckAuxPort);
	
	public BottomDuck(){
		bottomTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		bottomTalon.enableLimitSwitch(true, true);
		bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
		bottomTalon.enableBrakeMode(true);
    	bottomTalon.setPID(p, i, d, f, izone, closeLoopRampRate, 0);
    	
    	bottomAuxTalon.changeControlMode(TalonControlMode.Follower);
    	bottomAuxTalon.set(RobotMap.bottomDuckPort);
	}
	
    public void initDefaultCommand() {
    }

    public void set(double outputValue){
    	bottomTalon.set(- outputValue);
    }

    public void setSlow(double outputValue){
    	bottomTalon.set(- outputValue * 0.3);
    }

    public void JoySetDrive(double joystickValue){
    	if (!bottomTalon.isFwdLimitSwitchClosed() && !bottomTalon.isRevLimitSwitchClosed())
    		JoySet += joystickValue/4096*100;
    	bottomTalon.set(- JoySet);
    }

    public void setJoySet(double JoySet){
    	this.JoySet = JoySet;
    }
    
    public double getJoySet(){
    	return JoySet;
    }

    public double getSpeed(){
    	return bottomTalon.getSpeed();
    }

    public int getEncPosition(){
    	return bottomTalon.getEncPosition();
    }
    
    public void setEncPosition(int pos){
    	bottomTalon.setEncPosition(pos);
    }
    
    public void setPositionMode(){
    	bottomTalon.changeControlMode(TalonControlMode.Position);
    	set(getEncPosition());
    }
    
    public void setThrottleMode(){
    	bottomTalon.changeControlMode(TalonControlMode.PercentVbus);
    	bottomTalon.set(0);
    }
    
    public int getClosedLoopError(){
    	return bottomTalon.getClosedLoopError();
    }
}